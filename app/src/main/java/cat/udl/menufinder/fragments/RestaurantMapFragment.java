package cat.udl.menufinder.fragments;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

import cat.udl.menufinder.R;
import cat.udl.menufinder.application.MasterFragment;
import cat.udl.menufinder.models.Restaurant;

public class RestaurantMapFragment extends MasterFragment implements OnMapReadyCallback {
    private static final String TAG = RestaurantMapFragment.class.getName();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_restaurant_map, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MapFragment mapFragment = (MapFragment) getChildFragmentManager().findFragmentById(R.id.fragment);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng lleida = new LatLng(41.6175899, 0.6200145999999904);
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(lleida, 13));
        putRestaurantsInMap(googleMap);
    }

    private void putRestaurantsInMap(GoogleMap googleMap) {
        Geocoder geocoder = new Geocoder(getActivity());
        List<Restaurant> restaurants = getDbManager().getRestaurants();
        for (Restaurant restaurant : restaurants) {
            try {
                List<Address> addresses = geocoder.getFromLocationName(restaurant.getAddressWithCity(), 1);
                if (addresses.size() > 0) {
                    double latitude = addresses.get(0).getLatitude();
                    double longitude = addresses.get(0).getLongitude();
                    googleMap.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude))
                            .title(getString(R.string.marker_title, restaurant.getName())));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
