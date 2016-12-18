package cat.udl.menufinder.fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import cat.udl.menufinder.R;
import cat.udl.menufinder.builders.SearchCriteriaBuilder;
import cat.udl.menufinder.models.Restaurant;
import cat.udl.menufinder.utils.SearchCriteria;

public class RestaurantsFragment extends SubscriptionsFragment {

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
                "Plaça Major", "Talladell", "25301", "Spaña", "Lleida", "", "973 973 973",
                getMasterApplication().getUsername());
        restaurants.add(filteredRestaurant);
        adapter.notifyDataSetChanged();
    }

    public interface OnRestaurantClickListener {
        void onRestaurantClick(Restaurant restaurant, View view);

        void onShareClick(Restaurant restaurant);

        void onViewMapClick(Restaurant restaurant);

        void onFavouriteClick(Restaurant restaurant);
    }

}
