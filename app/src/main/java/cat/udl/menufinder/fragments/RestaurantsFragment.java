package cat.udl.menufinder.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import cat.udl.menufinder.R;
import cat.udl.menufinder.activities.DetailRestaurantActivity;
import cat.udl.menufinder.adapters.RestaurantsAdapter;
import cat.udl.menufinder.application.MasterFragment;
import cat.udl.menufinder.builders.SearchCriteriaBuilder;
import cat.udl.menufinder.models.Restaurant;
import cat.udl.menufinder.utils.SearchCriteria;

import static cat.udl.menufinder.utils.Constants.KEY_RESTAURANT;

public class RestaurantsFragment extends MasterFragment {

    private List<Restaurant> restaurants;
    private RestaurantsAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_restaurants, container, false);
    }

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
                    intent.putExtra(KEY_RESTAURANT, restaurant);
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
                        String price = ((EditText) dialogView.findViewById(R.id.price)).getText().toString().trim();
                        if (!TextUtils.isEmpty(price)) scb.setPrice(Double.valueOf(price));
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
        restaurants.clear();
        Restaurant filteredRestaurant = new Restaurant("Filtered Restaurant", "123456789X",
                "Plaça Major", "Talladell", "25301", "Spaña", "Lleida", "", "973 973 973");
        restaurants.add(filteredRestaurant);
        adapter.notifyDataSetChanged();
    }

}
