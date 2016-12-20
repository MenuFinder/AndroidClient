package cat.udl.menufinder.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import cat.udl.menufinder.R;
import cat.udl.menufinder.application.MasterActivity;
import cat.udl.menufinder.enums.UserType;
import cat.udl.menufinder.models.Account;
import cat.udl.menufinder.ws.WebServiceImpl;

public class LoginActivity extends MasterActivity {

    private static final String TAG = LoginActivity.class.getSimpleName();
    private UserLoginTask authTask = null;

    private EditText usernameView;
    private EditText passwordView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        changeOrientationIfIsPhone();
        setContentView(R.layout.activity_login);
        usernameView = (EditText) findViewById(R.id.username);

        passwordView = (EditText) findViewById(R.id.password);
        passwordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        Button signInButton = (Button) findViewById(R.id.sign_in_button);
        signInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        Button registerButton = (Button) findViewById(R.id.register_button);
        registerButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        Button signInWithouUserName = (Button) findViewById(R.id.sign_in_without_username_button);
        signInWithouUserName.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Account account = new Account();
                account.setType(UserType.GUEST);
                account.setId(UserType.GUEST.toString());
                loginSuccess(account);
            }
        });
    }

    private void attemptLogin() {
        if (authTask != null) {
            return;
        }

        usernameView.setError(null);
        passwordView.setError(null);

        String username = usernameView.getText().toString().trim();
        String password = passwordView.getText().toString().trim();

        boolean cancel = false;
        View focusView = null;

        if (TextUtils.isEmpty(password)) {
            passwordView.setError(getString(R.string.error_field_required));
            focusView = passwordView;
            cancel = true;
        }

        if (TextUtils.isEmpty(username)) {
            usernameView.setError(getString(R.string.error_field_required));
            focusView = usernameView;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            authTask = new UserLoginTask(username, password);
            authTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }
    }

    private void loginSuccess(Account account) {
        getMasterApplication().login(account);
        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    public class UserLoginTask extends AsyncTask<Void, Void, Account> {

        private final String username;
        private final String password;

        UserLoginTask(String username, String password) {
            this.username = username;
            this.password = password;
        }

        @Override
        protected Account doInBackground(Void... voids) {
            return new WebServiceImpl().getValidLogin(username, password);
        }

        @Override
        protected void onPostExecute(Account account) {
            authTask = null;
            if (account == null) {
                showToast(getString(R.string.error_no_connection_server));
                return;
            }
            if (account.getType() == null) {
                passwordView.setError(getString(R.string.error_incorrect_password));
                passwordView.requestFocus();
            } else {
                loginSuccess(account);
            }
        }

        @Override
        protected void onCancelled() {
            authTask = null;
        }
    }
}

