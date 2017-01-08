package cat.udl.menufinder.fragments;

import android.content.Intent;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.SphericalUtil;

import java.util.ArrayList;
import java.util.List;

import cat.udl.menufinder.R;
import cat.udl.menufinder.activities.DetailRestaurantActivity;
import cat.udl.menufinder.activities.HomeActivity;
import cat.udl.menufinder.adapters.RestaurantsAdapter;
import cat.udl.menufinder.application.MasterFragment;
import cat.udl.menufinder.models.AccountSubscription;
import cat.udl.menufinder.models.Restaurant;
import cat.udl.menufinder.utils.GPSTracker;
import cat.udl.menufinder.utils.Utils;

import static cat.udl.menufinder.utils.Constants.KEY_RESTAURANT;

public class SubscriptionsFragment extends MasterFragment {

    protected List<Restaurant> restaurants;
    protected RestaurantsAdapter adapter;
    private GPSTracker gps;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_restaurants, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        configList();
    }

    protected void configList() {
        RecyclerView recyclerView = (RecyclerView) getView().findViewById(R.id.list);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(
                getActivity(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);

        DefaultItemAnimator animator = new DefaultItemAnimator();
        animator.setAddDuration(1000);
        recyclerView.setItemAnimator(animator);
        getNearbyRestaurants();
        if (restaurants == null || restaurants.isEmpty())
            restaurants = getRestaurants();
        adapter = new RestaurantsAdapter(getActivity(), restaurants, new RestaurantsFragment.OnRestaurantClickListener() {
            @Override
            public void onRestaurantClick(Restaurant restaurant, View view) {
                DetailRestaurantFragment fragment = (DetailRestaurantFragment) getFragmentManager()
                        .findFragmentById(R.id.detail_fragment);
                if (fragment != null && fragment.isInLayout()) {
                    fragment.update(restaurant);
                } else {
                    Intent intent = new Intent(getActivity(), DetailRestaurantActivity.class);
                    intent.putExtra(KEY_RESTAURANT, restaurant);
                    if (Build.VERSION.SDK_INT >= 21) {
                        View image = view.findViewById(R.id.image);
                        ActivityOptionsCompat activityOptionsCompat =
                                ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(),
                                        image, getString(R.string.image_restaurant));
                        startActivity(intent, activityOptionsCompat.toBundle());
                    } else {
                        startActivity(intent);
                    }
                }
            }

            @Override
            public void onShareClick(Restaurant restaurant) {
                startActivity(Utils.getShareIntent(getString(R.string.share_text, restaurant.getName())));
            }

            @Override
            public void onViewMapClick(Restaurant restaurant) {
                HomeActivity activity = (HomeActivity) getActivity();
                activity.loadFragment(R.id.content, RestaurantMapFragment.newInstance(restaurant));
            }

            @Override
            public void onFavouriteClick(Restaurant restaurant, boolean checked) {
                AccountSubscription subscription = new AccountSubscription(getMasterApplication().getUsername(), restaurant.getId());
                if (checked) getDbManager().addAccountSubscription(subscription);
                else getDbManager().deleteAccountSubscription(subscription);
            }
        });
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    public List<Restaurant> getRestaurants() {
        return getDbManager().getSubscribedRestaurantsOfAccount(getMasterApplication().getUsername());
    }


    //function to get nearbyRestaurants
    private void getNearbyRestaurants() {
        restaurants = new ArrayList<Restaurant>();
        // create class object
        gps = new GPSTracker(getActivity());

        // check if GPS enabled
        if (gps.canGetLocation()) {

            Location userLocation = gps.getLocation();

            LatLng latLngUser = new LatLng(userLocation.getLatitude(), userLocation.getLongitude());
            for (Restaurant restaurant : getRestaurants()) {

                if (Utils.isNetworkAvailable(this.getActivity())) {
                    LatLng latLngRestaurant = Utils.getLatLngOfRestaurant(restaurant, getActivity());
                    double distance = 2001;
                    if (latLngRestaurant != null) {
                        distance = SphericalUtil.computeDistanceBetween(latLngUser, latLngRestaurant);
                    }
                    if (distance < 2000) {
                        restaurants.add(restaurant);
                    }
                }
            }
            // adapter.notifyDataSetChanged();
        } else {
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            gps.showSettingsAlert();
        }
    }

    private String formatNumber(double distance) {
        String unit = "m";
        if (distance > 1000) {
            distance /= 1000;
            unit = "km";
        }

        return String.format("%4.3f%s", distance, unit);
    }


    public interface OnRestaurantClickListener {
        void onRestaurantClick(Restaurant restaurant, View view);

        void onShareClick(Restaurant restaurant);

        void onViewMapClick(Restaurant restaurant);

        void onFavouriteClick(Restaurant restaurant, boolean checked);
    }
}
