package cat.udl.menufinder.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import cat.udl.menufinder.R;
import cat.udl.menufinder.application.MasterActivity;
import cat.udl.menufinder.fragments.ManageItemsFragment;
import cat.udl.menufinder.fragments.ManageMenusFragment;
import cat.udl.menufinder.fragments.PreferencesFragment;
import cat.udl.menufinder.fragments.RestaurantsFragment;

import static cat.udl.menufinder.enums.UserType.CLIENT;
import static cat.udl.menufinder.enums.UserType.GUEST;
import static cat.udl.menufinder.enums.UserType.RESTAURANT;

public class HomeActivity extends MasterActivity
        implements NavigationView.OnNavigationItemSelectedListener,OnMapReadyCallback {
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        navigate(item.getItemId());
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void navigate(int id) {
        int itemId = R.id.content;
        if (id == R.id.manage_items) {
            toolbar.setTitle(R.string.action_manage_items);
            loadFragment(itemId, new ManageItemsFragment());
        } else if (id == R.id.manage_menus) {
            toolbar.setTitle(R.string.action_manage_menus);
            loadFragment(itemId, new ManageMenusFragment());
        } else if (id == R.id.view_restaurants) {
            toolbar.setTitle(R.string.action_view_restaurants);
            loadFragment(itemId, new RestaurantsFragment());
        } else if (id == R.id.view_map) {
            toolbar.setTitle(R.string.view_on_map);
            MapFragment mapFragment = MapFragment.newInstance();
            mapFragment.getMapAsync(this);
            loadFragment(itemId, mapFragment);
        } else if (id == R.id.settings) {
            toolbar.setTitle(R.string.action_settings);
            loadFragment(itemId, new PreferencesFragment());
        } else if (id == R.id.logout) {
            getMasterApplication().logout();
            startActivity(new Intent(HomeActivity.this, SplashActivity.class));
            finish();
        } else if (id == R.id.login) {
            startActivity(new Intent(HomeActivity.this, LoginActivity.class));
            finish();
        } else if (id == R.id.register) {
            startActivity(new Intent(HomeActivity.this, RegisterActivity.class));
            finish();
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng lleida = new LatLng(41.6175899, 0.6200145999999904);
        googleMap.addMarker(new MarkerOptions().position(lleida).title("Marker in Lleida"));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(lleida, 15));
    }
}
