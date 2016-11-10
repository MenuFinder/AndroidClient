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

public class LoginActivity extends MasterActivity {

    private UserLoginTask authTask = null;

    private EditText usernameView;
    private EditText passwordView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
                showToast("Go to Register");
            }
        });

        Button signInWithouUserName = (Button) findViewById(R.id.sign_in_without_username_button);
        signInWithouUserName.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                showToast("Sign in without username");
                loginSuccess();
            }
        });
    }

    private void attemptLogin() {
        if (authTask != null) {
            return;
        }

        usernameView.setError(null);
        passwordView.setError(null);

        String email = usernameView.getText().toString();
        String password = passwordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        if (!TextUtils.isEmpty(password) && !isInvalidPassword(password)) {
            passwordView.setError(getString(R.string.error_invalid_password));
            focusView = passwordView;
            cancel = true;
        }

        if (TextUtils.isEmpty(email)) {
            usernameView.setError(getString(R.string.error_field_required));
            focusView = usernameView;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            authTask = new UserLoginTask(email, password);
            authTask.execute((Void) null);
        }
    }

    private boolean isInvalidPassword(String password) {
        return password.length() > 4;
    }

    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        private final String mEmail;
        private final String mPassword;

        UserLoginTask(String email, String password) {
            mEmail = email;
            mPassword = password;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            return mEmail.equals("admin") && mPassword.equals("admin");
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            authTask = null;

            if (success) {
                loginSuccess();
            } else {
                passwordView.setError(getString(R.string.error_incorrect_password));
                passwordView.requestFocus();
            }
        }

        @Override
        protected void onCancelled() {
            authTask = null;
        }
    }

    private void loginSuccess() {
        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);

        startActivity(intent);
    }
}

