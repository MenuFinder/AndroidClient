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
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
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
    private int restaurantsDistance;
    private TextView header;

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
        restaurantsDistance = maxDistance;
    }

    protected void configHeader() {
        header = (TextView) getView().findViewById(R.id.header);
        header.setVisibility(VISIBLE);
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
                        String name = ((AutoCompleteTextView) dialogView.findViewById(R.id.restaurant_name)).getText().toString().trim();
                        if (!TextUtils.isEmpty(name)) scb.setRestaurantName(name);
                        String city = ((AutoCompleteTextView) dialogView.findViewById(R.id.city)).getText().toString().trim();
                        if (!TextUtils.isEmpty(city)) scb.setCity(city);
                        String distance = ((EditText) dialogView.findViewById(R.id.distance)).getText().toString().trim();
                        if (!TextUtils.isEmpty(distance))
                            restaurantsDistance = Integer.parseInt(distance);
                        filterRestaurants(scb.build());
                    }
                })
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                })
                .setNeutralButton(R.string.clear_filter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        filterRestaurants(new SearchCriteriaBuilder().build());
                    }
                })
                .create();
        ((AutoCompleteTextView) dialogView.findViewById(R.id.city)).setAdapter(new ArrayAdapter<>
                (getActivity(), android.R.layout.simple_list_item_1, getDbManager().getAllDifferentCities()));
        ((AutoCompleteTextView) dialogView.findViewById(R.id.restaurant_name)).setAdapter(new ArrayAdapter<>
                (getActivity(), android.R.layout.simple_list_item_1, getDbManager().getAllRestaurantNames()));
        alertDialog.show();
    }

    private void filterRestaurants(SearchCriteria searchCriteria) {
        new FilterRestaurantsTask(searchCriteria).execute();
    }

    @Override
    public List<Restaurant> getRestaurants() {
        return getNearbyRestaurants(getDbManager().getRestaurants());
    }

    private List<Restaurant> getNearbyRestaurants(List<Restaurant> restaurantList) {
        header.setText(String.format(getString(R.string.showing_restaurants_distance_header), restaurantsDistance));
        List<Restaurant> restaurants = new ArrayList<>();
        // create class object
        GPSTracker gps = new GPSTracker(getActivity());

        // check if GPS enabled
        if (gps.canGetLocation()) {

            Location userLocation = gps.getLocation();
            if (userLocation != null) {
                LatLng latLngUser = new LatLng(userLocation.getLatitude(), userLocation.getLongitude());
                for (Restaurant restaurant : restaurantList) {
                    if (Utils.isNetworkAvailable(this.getActivity())) {
                        LatLng latLngRestaurant = Utils.getLatLngOfRestaurant(restaurant, getActivity());
                        double distance = restaurantsDistance;
                        if (latLngRestaurant != null) {
                            distance = SphericalUtil.computeDistanceBetween(latLngUser, latLngRestaurant);
                        }
                        if (distance < restaurantsDistance) {
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
        if (restaurants.isEmpty()) {
            showToast(getString(R.string.not_results_founds));
            restaurantsDistance = maxDistance;
            return getNearbyRestaurants(getDbManager().getRestaurants());
        }
        return restaurants;
    }

    private class FilterRestaurantsTask extends AsyncTask<String, Void, List<Restaurant>> {
        private final String where;

        FilterRestaurantsTask(SearchCriteria searchCriteria) {
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
            adapter.setRestaurants(getNearbyRestaurants(restaurantList));
        }
    }
}
