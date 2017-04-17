package cat.udl.menufinder.activities;

import android.content.Intent;
import android.os.Bundle;

import cat.udl.menufinder.R;
import cat.udl.menufinder.application.MasterActivity;
import cat.udl.menufinder.fragments.DetailRestaurantFragment;
import cat.udl.menufinder.models.Restaurant;

import static cat.udl.menufinder.utils.Constants.KEY_RESTAURANT;

public class DetailRestaurantActivity extends MasterActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        changeOrientationIfIsPhone();
        setDisplayHomeAsUpEnable();
        setContentView(R.layout.activity_detail_restaurant);
        Intent intent = getIntent();
        Restaurant restaurant = (Restaurant) intent.getSerializableExtra(KEY_RESTAURANT);
        DetailRestaurantFragment fragment = (DetailRestaurantFragment) getFragmentManager().findFragmentById(R.id.detail_fragment);
        fragment.update(restaurant);
    }
}
