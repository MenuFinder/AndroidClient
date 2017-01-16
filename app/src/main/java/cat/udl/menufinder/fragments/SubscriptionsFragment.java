package cat.udl.menufinder.fragments;

import android.content.Intent;
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

import java.util.List;

import cat.udl.menufinder.R;
import cat.udl.menufinder.activities.DetailRestaurantActivity;
import cat.udl.menufinder.activities.HomeActivity;
import cat.udl.menufinder.activities.ReviewRestaurantActivity;
import cat.udl.menufinder.adapters.RestaurantsAdapter;
import cat.udl.menufinder.application.MasterFragment;
import cat.udl.menufinder.models.AccountSubscription;
import cat.udl.menufinder.models.Restaurant;
import cat.udl.menufinder.utils.Utils;

import static cat.udl.menufinder.utils.Constants.KEY_RESTAURANT;

public class SubscriptionsFragment extends MasterFragment {

    protected RestaurantsAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_restaurants, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        configHeader();
        configList();
    }

    protected void configHeader() {
    }

    protected void configList() {
        RecyclerView recyclerView = (RecyclerView) getView().findViewById(R.id.list);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(
                getActivity(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);

        DefaultItemAnimator animator = new DefaultItemAnimator();
        animator.setAddDuration(1000);
        recyclerView.setItemAnimator(animator);
        List<Restaurant> restaurants = getRestaurants();
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
            public void onReViewClick(Restaurant restaurant) {
                Intent intent = new Intent(getActivity(), ReviewRestaurantActivity.class);
                intent.putExtra(KEY_RESTAURANT, restaurant);
                startActivity(intent);
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


    public interface OnRestaurantClickListener {
        void onRestaurantClick(Restaurant restaurant, View view);

        void onShareClick(Restaurant restaurant);

        void onViewMapClick(Restaurant restaurant);

        void onReViewClick(Restaurant restaurant);

        void onFavouriteClick(Restaurant restaurant, boolean checked);
    }
}
