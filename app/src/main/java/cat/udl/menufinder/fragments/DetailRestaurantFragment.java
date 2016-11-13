package cat.udl.menufinder.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cat.udl.menufinder.R;
import cat.udl.menufinder.application.MasterFragment;
import cat.udl.menufinder.models.Restaurant;

public class DetailRestaurantFragment extends MasterFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail_restaurant, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public void update(Restaurant restaurant) {
        ((TextView) getView().findViewById(R.id.name)).setText(restaurant.getName());
        ((TextView) getView().findViewById(R.id.address)).setText(restaurant.getAddress());
    }
}
