package cat.udl.menufinder.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
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
import cat.udl.menufinder.activities.DetailRestaurantActivity;
import cat.udl.menufinder.builders.SearchCriteriaBuilder;
import cat.udl.menufinder.models.Restaurant;
import cat.udl.menufinder.utils.Constants;
import cat.udl.menufinder.utils.GPSTracker;
import cat.udl.menufinder.utils.SearchCriteria;
import cat.udl.menufinder.utils.Utils;

import static android.view.View.VISIBLE;
import static cat.udl.menufinder.utils.Constants.KEY_RESTAURANT;

public class RestaurantsFragment extends SubscriptionsFragment {
    public static final String TAG = RestaurantsFragment.class.getSimpleName();
    private int maxDistance; // Km
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
        maxDistance = Integer.parseInt(PreferenceManager.getDefaultSharedPreferences(getActivity()).getString(Constants.PREFERENCES_DISTANCE, "2"));

        restaurantsDistance = maxDistance;
        Restaurant restaurant = (Restaurant) getActivity().getIntent().getSerializableExtra(KEY_RESTAURANT);
        if (restaurant != null) {
            Intent intent = new Intent(getActivity(), DetailRestaurantActivity.class);
            intent.putExtra(KEY_RESTAURANT, restaurant);
            startActivity(intent);
        }
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
                        if (!TextUtils.isEmpty(distance)) {
                            restaurantsDistance = Integer.parseInt(distance);
                            scb.setDistance(true);
                        } else {
                            scb.setDistance(false);
                        }

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
                        SearchCriteriaBuilder builder = new SearchCriteriaBuilder();
                        builder.setDistance(true);
                        maxDistance = Integer.parseInt(PreferenceManager.getDefaultSharedPreferences(getActivity()).getString(Constants.PREFERENCES_DISTANCE, "2"));
                        filterRestaurants(builder.build());
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
                            distance = SphericalUtil.computeDistanceBetween(latLngUser, latLngRestaurant) / 1000;
                        }
                        if (distance < restaurantsDistance) {
                            restaurants.add(restaurant);
                        }
                    }
                }
            }
        } else {
            gps.showSettingsAlert();
        }
        if (restaurants.isEmpty()) {
            showToast(getString(R.string.not_results_founds));
            header.setText(R.string.showing_all);
            restaurantsDistance = maxDistance;
            return getDbManager().getRestaurants();
        }
        return restaurants;
    }

    private class FilterRestaurantsTask extends AsyncTask<String, Void, List<Restaurant>> {
        private final String where;
        private final SearchCriteria searchCriteria;

        FilterRestaurantsTask(SearchCriteria searchCriteria) {
            where = searchCriteria.getWhere();
            this.searchCriteria = searchCriteria;
        }

        @Override
        protected List<Restaurant> doInBackground(String... strings) {
            Log.d(TAG, where);
            return getDbManager().getFilteredRestaurants(where);
        }

        @Override
        protected void onPostExecute(List<Restaurant> restaurantList) {
            super.onPostExecute(restaurantList);
            if (searchCriteria.getDistance() || restaurantList.isEmpty()) {
                adapter.setRestaurants(getNearbyRestaurants(restaurantList));
            } else {
                header.setText(R.string.custom_restaurants);
                adapter.setRestaurants(restaurantList);
            }
        }
    }
}
