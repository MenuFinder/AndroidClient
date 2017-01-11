package cat.udl.menufinder.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.List;

import cat.udl.menufinder.R;
import cat.udl.menufinder.application.MasterActivity;
import cat.udl.menufinder.fragments.ManageItemsFragment;
import cat.udl.menufinder.fragments.ManageMenusFragment;
import cat.udl.menufinder.fragments.ManageRestaurantsFragment;
import cat.udl.menufinder.fragments.PreferencesFragment;
import cat.udl.menufinder.fragments.RestaurantMapFragment;
import cat.udl.menufinder.fragments.RestaurantsFragment;
import cat.udl.menufinder.fragments.SubscriptionsFragment;
import cat.udl.menufinder.models.Account;
import cat.udl.menufinder.models.Restaurant;

import static cat.udl.menufinder.enums.UserType.CLIENT;
import static cat.udl.menufinder.enums.UserType.GUEST;
import static cat.udl.menufinder.enums.UserType.RESTAURANT;
import static cat.udl.menufinder.utils.Utils.checkIfNotGuest;

public class HomeActivity extends MasterActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        changeOrientationIfIsPhone();
        setContentView(R.layout.activity_home);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (getMasterApplication().getUserType() == CLIENT)
            navigationView.inflateMenu(R.menu.activity_home_drawer_client);
        else if (getMasterApplication().getUserType() == RESTAURANT)
            navigationView.inflateMenu(R.menu.activity_home_drawer_restaurant);
        else if (getMasterApplication().getUserType() == GUEST)
            navigationView.inflateMenu(R.menu.activity_home_drawer_guest);
        navigationView.setNavigationItemSelectedListener(this);

        if (getMasterApplication().getUserType() != GUEST) {
            TextView view = (TextView) navigationView.getHeaderView(0).findViewById(R.id.nav_username);
            view.setText(getMasterApplication().getUsername());
        }

        navigate(navigationView.getMenu().getItem(0).getItemId());
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return navigate(item.getItemId());
    }

    private boolean navigate(int id) {
        boolean navigate = true;
        int itemId = R.id.content;
        if (id == R.id.manage_restaurants) {
            toolbar.setTitle(R.string.action_manage_restaurants);
            loadFragment(itemId, new ManageRestaurantsFragment());
        } else if (id == R.id.manage_items) {
            if (checkAvailableRestaurants()) {
                toolbar.setTitle(R.string.action_manage_items);
                loadFragment(itemId, new ManageItemsFragment());
            } else navigate = false;
        } else if (id == R.id.manage_menus) {
            if (checkAvailableRestaurants()) {
                toolbar.setTitle(R.string.action_manage_menus);
                loadFragment(itemId, new ManageMenusFragment());
            } else navigate = false;
        } else if (id == R.id.view_restaurants) {
            toolbar.setTitle(R.string.action_view_restaurants);
            loadFragment(itemId, new RestaurantsFragment());
        } else if (id == R.id.view_subscriptions) {
            if (checkIfNotGuest()) {
                toolbar.setTitle(R.string.action_view_subscriptions);
                loadFragment(itemId, new SubscriptionsFragment());
            } else navigate = false;
        } else if (id == R.id.view_map) {
            toolbar.setTitle(R.string.view_on_map);
            loadFragment(itemId, RestaurantMapFragment.newInstance());
        } else if (id == R.id.settings) {
            toolbar.setTitle(R.string.action_settings);
            loadFragment(itemId, new PreferencesFragment());
        } else if (id == R.id.logout) {
            new UserLogoutTask().execute();
        } else if (id == R.id.login) {
            login();
        } else if (id == R.id.register) {
            register();
        }
        return navigate;
    }

    private boolean checkAvailableRestaurants() {
        List<Restaurant> restaurantsOfAccount = getDbManager().getRestaurantsOfAccount(
                getMasterApplication().getUsername());
        if (restaurantsOfAccount.isEmpty()) {
            showToast(R.string.must_create_restaurant);
            return false;
        }
        return true;
    }

    private void login() {
        startActivity(new Intent(HomeActivity.this, LoginActivity.class));
        finish();
    }

    private void register() {
        startActivity(new Intent(HomeActivity.this, RegisterActivity.class));
        finish();
    }

    private void logout() {
        getMasterApplication().logout();
        startActivity(new Intent(HomeActivity.this, SplashActivity.class));
        finish();
    }

    public class UserLogoutTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            Account account = new Account(getMasterApplication().getUsername(), null,
                    getMasterApplication().getUserType().getText(), null);
            account.setToken("");
            getDbManager().updateAccountToken(account);
            return null;
        }

        @Override
        protected void onPostExecute(Void v) {
            logout();
        }
    }
}
