package cat.udl.menufinder.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import cat.udl.menufinder.R;
import cat.udl.menufinder.adapters.RestaurantsAdapter;
import cat.udl.menufinder.application.MasterFragment;
import cat.udl.menufinder.models.Restaurant;

public class RestaurantsFragment extends MasterFragment {

    private List<Restaurant> restaurants;
    private RestaurantsAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_restaurants, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        configList();
    }

    private void configList() {
        RecyclerView recyclerView = (RecyclerView) getView().findViewById(R.id.list);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(
                getActivity(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);

        DefaultItemAnimator animator = new DefaultItemAnimator();
        animator.setAddDuration(1000);
        recyclerView.setItemAnimator(animator);

        restaurants = new ArrayList<Restaurant>();
        Restaurant testRestaurant = new Restaurant("Mesillas Restaurant", "123456789X",
                "Av. Balmes 54", "Lleida", "25003", "Spaña", "Lleida", "", "973 973 973");
        Restaurant testRestaurant1 = new Restaurant("Calmao Restaurant", "123456781X",
                "Av. Madrid 1", "Lleida", "25002", "Spaña", "Lleida", "", "973 973 970");
        Restaurant testRestaurant2 = new Restaurant("Messon 2004", "123416781X",
                "C\\Sant Pelegrí 25", "Tàrrega", "25300", "Spaña", "Lleida", "", "973 310 367");
        restaurants.add(testRestaurant);
        restaurants.add(testRestaurant1);
        restaurants.add(testRestaurant2);
        adapter = new RestaurantsAdapter(getActivity(), restaurants, new OnRestaurantClickListener() {
            @Override
            public void onRestaurantClick(Restaurant restaurant) {
                DetailRestaurantFragment fragment = (DetailRestaurantFragment) getFragmentManager()
                        .findFragmentById(R.id.detail_fragment);
                if (fragment != null && fragment.isInLayout()) {
                    fragment.update(restaurant);
                } else {
                    Intent intent = new Intent(getActivity(), DetailRestaurantActivity.class);
                    intent.putExtra("restaurant", restaurant);
                    startActivity(intent);
                }
            }

            @Override
            public void onShareClick(Restaurant restaurant) {
                showToast("SHARE");
            }

            @Override
            public void onViewMapClick(Restaurant restaurant) {
                showToast("MAP");
            }
        });
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    public interface OnRestaurantClickListener {
        void onRestaurantClick(Restaurant restaurant);

        void onShareClick(Restaurant restaurant);

        void onViewMapClick(Restaurant restaurant);
    }

}
