package cat.udl.menufinder.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.LinearLayout;

import cat.udl.menufinder.R;
import cat.udl.menufinder.application.MasterActivity;
import cat.udl.menufinder.enums.UserType;
import cat.udl.menufinder.models.Account;
import cat.udl.menufinder.models.Restaurant;

import static cat.udl.menufinder.R.id.address;
import static cat.udl.menufinder.enums.UserType.CLIENT;
import static cat.udl.menufinder.enums.UserType.RESTAURANT;

public class RegisterActivity extends MasterActivity {

    private CheckedTextView checkedTextView;
    private UserRegisterTask authTask = null;
    private View focusView;
    private boolean cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        changeOrientationIfIsPhone();
        setContentView(R.layout.activity_register);
        final LinearLayout linearLayout = (LinearLayout) findViewById(R.id.restaurant_linear_layout);
        checkedTextView = (CheckedTextView) findViewById(R.id.is_restaurant);
        checkedTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkedTextView.setChecked(!checkedTextView.isChecked());
                if (checkedTextView.isChecked()) linearLayout.setVisibility(View.VISIBLE);
                else linearLayout.setVisibility(View.GONE);
            }
        });
        Button register = (Button) findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkFields();
            }
        });
    }

    private void checkFields() {
        if (authTask != null) {
            return;
        }
        EditText usernameView = (EditText) findViewById(R.id.username);
        EditText passwordView = (EditText) findViewById(R.id.password);
        EditText emailUserView = (EditText) findViewById(R.id.email_user);
        EditText restaurantNameView = (EditText) findViewById(R.id.restaurant_name);
        EditText cifView = (EditText) findViewById(R.id.cif);
        EditText addressView = (EditText) findViewById(address);
        EditText cityView = (EditText) findViewById(R.id.city);
        EditText postalCodeView = (EditText) findViewById(R.id.postal_code);
        EditText stateView = (EditText) findViewById(R.id.state);
        EditText countryView = (EditText) findViewById(R.id.country);
        EditText emailRestaurantView = (EditText) findViewById(R.id.restaurant_email);
        EditText phoneView = (EditText) findViewById(R.id.phone);

        usernameView.setError(null);
        passwordView.setError(null);
        restaurantNameView.setError(null);
        emailRestaurantView.setError(null);
        cifView.setError(null);
        addressView.setError(null);
        cityView.setError(null);
        postalCodeView.setError(null);
        stateView.setError(null);
        countryView.setError(null);
        emailUserView.setError(null);
        phoneView.setError(null);

        cancel = false;
        focusView = null;

        String username = usernameView.getText().toString().trim();
        checkView(username, usernameView);

        String password = passwordView.getText().toString().trim();
        checkView(password, passwordView);

        String emailUser = emailUserView.getText().toString().trim();
        checkView(emailUser, emailUserView);
        checkEmail(emailUser, emailUserView);

        UserType userType = (checkedTextView.isChecked()) ? RESTAURANT : CLIENT;
        Account account = new Account(username, password, userType.getText(), emailUser);

        if (checkedTextView.isChecked()) {
            String restaurantName = restaurantNameView.getText().toString().trim();
            checkView(restaurantName, emailRestaurantView);

            String cif = cifView.getText().toString().trim();
            checkView(cif, cifView);

            String address = addressView.getText().toString().trim();
            checkView(address, addressView);

            String city = cityView.getText().toString().trim();
            checkView(city, cityView);

            String postalCode = postalCodeView.getText().toString().trim();
            checkView(postalCode, postalCodeView);

            String state = stateView.getText().toString().trim();
            checkView(state, stateView);

            String country = stateView.getText().toString().trim();
            checkView(country, countryView);

            String emailRestaurant = emailRestaurantView.getText().toString().trim();
            checkView(emailRestaurant, emailRestaurantView);
            checkEmail(emailRestaurant, emailRestaurantView);

            String phone = phoneView.getText().toString().trim();
            checkView(phone, phoneView);
            checkMobilPhone(phone, phoneView);

            Restaurant restaurant = new Restaurant(restaurantName, cif, address, city, postalCode,
                    state, country, emailRestaurant, phone, username);

            getDbManager().addRestaurant(restaurant);
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            authTask = new UserRegisterTask(account);
            authTask.execute((Void) null);
        }
    }

    private void checkEmail(String emailUser, EditText view) {
        if (!Patterns.EMAIL_ADDRESS.matcher(emailUser).matches()) {
            view.setError(getString(R.string.error_email_format));
            focusView = view;
            cancel = true;
        }
    }

    private void checkMobilPhone(String phone, EditText view) {
        if (!Patterns.PHONE.matcher(phone).matches()) {
            view.setError(getString(R.string.error_phone_format));
            focusView = view;
            cancel = true;
        }
    }


    private void checkView(String text, EditText view) {
        if (TextUtils.isEmpty(text)) {
            view.setError(getString(R.string.error_field_required));
            focusView = view;
            cancel = true;
        }
    }

    public class UserRegisterTask extends AsyncTask<Void, Void, Boolean> {


        private final Account account;

        UserRegisterTask(Account account) {
            this.account = account;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            return getDbManager().addAccount(account);
        }

        @Override
        protected void onPostExecute(final Boolean ok) {
            authTask = null;
            if (ok) {
                getMasterApplication().login(account);
                startActivity(new Intent(RegisterActivity.this, HomeActivity.class));
            }
        }

        @Override
        protected void onCancelled() {
            authTask = null;
        }
    }
}
