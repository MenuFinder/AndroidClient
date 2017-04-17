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
import android.widget.TextView;

import java.util.List;

import cat.udl.menufinder.R;
import cat.udl.menufinder.adapters.ReviewAdapter;
import cat.udl.menufinder.application.MasterActivity;
import cat.udl.menufinder.models.Menu;
import cat.udl.menufinder.models.Review;

import static cat.udl.menufinder.utils.Constants.KEY_MENU;
import static cat.udl.menufinder.utils.Utils.checkIfNotGuest;

public class ReviewMenuActivity extends MasterActivity {

    ReviewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        changeOrientationIfIsPhone();
        setDisplayHomeAsUpEnable();
        setContentView(R.layout.activity_review);
        Menu menu = (Menu) getIntent().getSerializableExtra(KEY_MENU);
        ((TextView) findViewById(R.id.name)).setText(menu.getName());
        ((TextView) findViewById(R.id.description)).setText(menu.getDescription());
        configList(getDbManager().getReviewsOfMenu(menu.getId()));
        configFAB(menu);
    }

    private void configList(List<Review> reviews) {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(
                ReviewMenuActivity.this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);
        adapter = new ReviewAdapter(ReviewMenuActivity.this, reviews);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(ReviewMenuActivity.this));
    }

    private void configFAB(final Menu menu) {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.add_item_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkIfNotGuest()) showAddDialog(menu);
            }
        });
    }

    private void showAddDialog(final Menu menu) {
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.view_menu_review, null);
        final AlertDialog alertDialog = new AlertDialog.Builder(ReviewMenuActivity.this)
                .setTitle(menu.getName())
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
                    saveToDB(new Review(review, "menu", menu.getId(), getMasterApplication().getUsername()));

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
