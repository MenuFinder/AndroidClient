package cat.udl.menufinder.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import cat.udl.menufinder.R;
import cat.udl.menufinder.fragments.DetailRestaurantFragment;
import cat.udl.menufinder.models.Restaurant;

public class DetailRestaurantActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_restaurant);
        Intent intent = getIntent();
        Restaurant restaurant = (Restaurant) intent.getSerializableExtra("restaurant");
        DetailRestaurantFragment fragment = (DetailRestaurantFragment) getFragmentManager().findFragmentById(R.id.detail_fragment);
        fragment.update(restaurant);
    }
}
