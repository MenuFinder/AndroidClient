package cat.udl.menufinder.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import cat.udl.menufinder.R;
import cat.udl.menufinder.activities.DetailMenuActivity;
import cat.udl.menufinder.adapters.MenusAdapter;
import cat.udl.menufinder.application.MasterActivity;
import cat.udl.menufinder.application.MasterFragment;
import cat.udl.menufinder.models.Menu;
import cat.udl.menufinder.models.Restaurant;

import static cat.udl.menufinder.utils.Constants.KEY_MENU;

public class DetailRestaurantFragment extends MasterFragment {

    private CollapsingToolbarLayout collapsingToolbarLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail_restaurant, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        collapsingToolbarLayout = (CollapsingToolbarLayout) view.findViewById(R.id.collapsing_toolbar);
        ((MasterActivity) getActivity()).setSupportActionBar((Toolbar) view.findViewById(R.id.toolbar));
        ((MasterActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void update(Restaurant restaurant) {
        collapsingToolbarLayout.setTitle(restaurant.getName());
        collapsingToolbarLayout.setExpandedTitleColor(ContextCompat.getColor(getContext(), android.R.color.transparent));

        configGeneralInformation(restaurant);
        configList(restaurant);
    }

    private void configGeneralInformation(Restaurant restaurant) {
        ((TextView) getView().findViewById(R.id.name)).setText(restaurant.getName());
        ((TextView) getView().findViewById(R.id.address)).setText(restaurant.getAddress());
        ((TextView) getView().findViewById(R.id.score)).setText(String.valueOf(restaurant.getScore()));
        ((TextView) getView().findViewById(R.id.phone)).setText(restaurant.getPhone());
        ((TextView) getView().findViewById(R.id.email)).setText(restaurant.getEmail());
    }

    private void configList(Restaurant restaurant) {
        RecyclerView recyclerView = (RecyclerView) getView().findViewById(R.id.list);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(
                getActivity(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);

        List<Menu> menus = restaurant.getVisibleMenus();

        MenusAdapter adapter = new MenusAdapter(getActivity(), menus, new ManageMenusFragment.OnMenuClickListener() {
            @Override
            public void onMenuClick(Menu menu, int adapterPosition) {
                Intent intent = new Intent(getActivity(), DetailMenuActivity.class);
                intent.putExtra(KEY_MENU, menu);
                startActivity(intent);
            }
        }, getMasterApplication().getUserType());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }
}
