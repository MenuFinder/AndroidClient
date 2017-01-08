package cat.udl.menufinder.fragments;

import android.content.DialogInterface;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.SphericalUtil;

import java.util.ArrayList;
import java.util.List;

import cat.udl.menufinder.R;
import cat.udl.menufinder.builders.SearchCriteriaBuilder;
import cat.udl.menufinder.models.Restaurant;
import cat.udl.menufinder.utils.GPSTracker;
import cat.udl.menufinder.utils.SearchCriteria;
import cat.udl.menufinder.utils.Utils;

import static android.view.View.VISIBLE;

public class RestaurantsFragment extends SubscriptionsFragment {
    public static final String TAG = RestaurantsFragment.class.getSimpleName();
    private final int maxDistance = 2000; // Meters

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.fragment_restaurants_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.search_button) {
            showFilterDialog();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    protected void configHeader() {
        TextView header = (TextView) getView().findViewById(R.id.header);
        header.setVisibility(VISIBLE);
        header.setText(String.format(getString(R.string.showing_restaurants_distance_header), maxDistance));
    }

    private void showFilterDialog() {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.view_criteria, null);
        final AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                .setTitle(R.string.search)
                .setIcon(R.drawable.menu_finder_logo)
                .setView(dialogView)
                .setPositiveButton(R.string.search, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        SearchCriteriaBuilder scb = new SearchCriteriaBuilder();
                        String name = ((EditText) dialogView.findViewById(R.id.restaurant_name)).getText().toString().trim();
                        if (!TextUtils.isEmpty(name)) scb.setRestaurantName(name);
                        String city = ((EditText) dialogView.findViewById(R.id.city)).getText().toString().trim();
                        if (!TextUtils.isEmpty(city)) scb.setCity(city);
                        filterRestaurants(scb.build());
                    }
                })
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                })
                .create();
        alertDialog.show();
    }

    private void filterRestaurants(SearchCriteria searchCriteria) {
        new FilterRestaurantsTask(searchCriteria).execute();
    }

    @Override
    public List<Restaurant> getRestaurants() {
        List<Restaurant> restaurants = getNearbyRestaurants();
        if (restaurants.isEmpty()) {
            Log.d(TAG, "Getting restaurants from DB");
            restaurants = getDbManager().getRestaurants();
        }
        return restaurants;
    }

    private List<Restaurant> getNearbyRestaurants() {
        List<Restaurant> restaurants = new ArrayList<>();
        // create class object
        GPSTracker gps = new GPSTracker(getActivity());

        // check if GPS enabled
        if (gps.canGetLocation()) {

            Location userLocation = gps.getLocation();
            if (userLocation != null) {
                LatLng latLngUser = new LatLng(userLocation.getLatitude(), userLocation.getLongitude());
                for (Restaurant restaurant : getDbManager().getRestaurants()) {
                    if (Utils.isNetworkAvailable(this.getActivity())) {
                        LatLng latLngRestaurant = Utils.getLatLngOfRestaurant(restaurant, getActivity());
                        double distance = maxDistance;
                        if (latLngRestaurant != null) {
                            distance = SphericalUtil.computeDistanceBetween(latLngUser, latLngRestaurant);
                        }
                        if (distance < maxDistance) {
                            restaurants.add(restaurant);
                        }
                    }
                }
            }
        } else {
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            gps.showSettingsAlert();
        }
        return restaurants;
    }

    private class FilterRestaurantsTask extends AsyncTask<String, Void, List<Restaurant>> {
        private final String where;

        public FilterRestaurantsTask(SearchCriteria searchCriteria) {
            where = searchCriteria.getWhere();
        }

        @Override
        protected List<Restaurant> doInBackground(String... strings) {
            Log.d(TAG, where);
            return getDbManager().getFilteredRestaurants(where);
        }

        @Override
        protected void onPostExecute(List<Restaurant> restaurantList) {
            super.onPostExecute(restaurantList);
            adapter.setRestaurants(restaurantList);
        }
    }
}
