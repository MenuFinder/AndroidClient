package cat.udl.menufinder.activities;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import cat.udl.menufinder.R;
import cat.udl.menufinder.adapters.ReviewAdapter;
import cat.udl.menufinder.application.MasterActivity;
import cat.udl.menufinder.models.Restaurant;
import cat.udl.menufinder.models.Review;

import static cat.udl.menufinder.utils.Constants.KEY_RESTAURANT;
import static cat.udl.menufinder.utils.Utils.checkIfNotGuest;

public class ReviewRestaurantActivity extends MasterActivity {

    ReviewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        changeOrientationIfIsPhone();
        setContentView(R.layout.activity_review);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Restaurant restaurant = (Restaurant) getIntent().getSerializableExtra(KEY_RESTAURANT);
        ((TextView) findViewById(R.id.name)).setText(restaurant.getName());
        ((TextView) findViewById(R.id.description)).setText(restaurant.getAddress());
        configList(getDbManager().getReviewsOfRestaurant(restaurant.getId()));
        configFAB(restaurant);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return false;
    }

    private void configList(List<Review> reviews) {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(
                ReviewRestaurantActivity.this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);
        adapter = new ReviewAdapter(ReviewRestaurantActivity.this, reviews);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(ReviewRestaurantActivity.this));
    }

    private void configFAB(final Restaurant restaurant) {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.add_item_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkIfNotGuest()) showAddDialog(restaurant);
            }
        });
    }

    private void showAddDialog(final Restaurant restaurant) {
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.view_restaurant_view, null);
        final AlertDialog alertDialog = new AlertDialog.Builder(ReviewRestaurantActivity.this)
                .setTitle(restaurant.getName())
                .setIcon(R.drawable.menu_finder_logo)
                .setView(dialogView)
                .setPositiveButton(R.string.save, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                })
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                })
                .create();

        alertDialog.show();
        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean closeDialog = true;
                String review = ((EditText) dialogView.findViewById(R.id.review)).getText().toString().trim();
                if (TextUtils.isEmpty(review)) {
                    closeDialog = false;
                }

                if (closeDialog) {
                    alertDialog.dismiss();
                    //TODO ENUM REVIEW_PARENT {item, restaurant, menu}
                    saveToDB(new Review(review, "restaurant", restaurant.getId(), getMasterApplication().getUsername()));

                }
            }
        });
    }

    private void saveToDB(Review review) {
        getDbManager().addReview(review);
        adapter.addReview(review);
        showToast(getString(R.string.review_added));
    }

}
