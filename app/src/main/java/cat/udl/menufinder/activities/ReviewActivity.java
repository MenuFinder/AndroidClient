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
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

import cat.udl.menufinder.R;
import cat.udl.menufinder.adapters.ReviewAdapter;
import cat.udl.menufinder.application.MasterActivity;
import cat.udl.menufinder.models.Item;
import cat.udl.menufinder.models.ItemRating;
import cat.udl.menufinder.models.Review;

import static cat.udl.menufinder.utils.Constants.KEY_ITEM;

public class ReviewActivity extends MasterActivity {

    ReviewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        changeOrientationIfIsPhone();
        setContentView(R.layout.activity_review);
        Item item = (Item) getIntent().getSerializableExtra(KEY_ITEM);
        ((TextView) findViewById(R.id.name)).setText(item.getName());
        ((TextView) findViewById(R.id.description)).setText(item.getDescription());
        configList(getDbManager().getReviewsOfItem(item.getId()));
        configFAB(item);
    }

    private void configList(List<Review> reviews) {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(
                ReviewActivity.this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);
        adapter = new ReviewAdapter(ReviewActivity.this, reviews);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(ReviewActivity.this));
    }

    private void configFAB(final Item item) {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.add_item_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddDialog(item);
            }
        });
    }

    private void showAddDialog(final Item item) {
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.view_review, null);
        final AlertDialog alertDialog = new AlertDialog.Builder(ReviewActivity.this)
                .setTitle(item.getName())
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
                float rating = ((RatingBar) dialogView.findViewById(R.id.score)).getRating();
                if (TextUtils.isEmpty(review)) {
                    closeDialog = false;
                }

                if (closeDialog) {
                    alertDialog.dismiss();
                    //TODO ENUM REVIEW_PARENT {item, restaurant, menu}
                    saveToDB(new Review(review, "item", item.getId(), getMasterApplication().getUsername()));
                    saveRatingToDB(new ItemRating(rating, item.getId(), getMasterApplication().getUsername()));

                }
            }
        });
    }

    private void saveToDB(Review review) {
        getDbManager().addReview(review);
        adapter.addReview(review);
        showToast(getString(R.string.review_added));
    }

    private void saveRatingToDB(ItemRating itemRating) {
        getDbManager().addItemRating(itemRating);
    }


}
