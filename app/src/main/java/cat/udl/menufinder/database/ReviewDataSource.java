package cat.udl.menufinder.database;

import android.content.ContentValues;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;

import java.util.ArrayList;
import java.util.List;

import cat.udl.menufinder.models.Review;

public class ReviewDataSource extends DataSource {
    private String[] allColumns =
            {
                    ReviewContract.ReviewTable.ID,
                    ReviewContract.ReviewTable.REVIEW,
                    ReviewContract.ReviewTable.PARENT_TYPE,
                    ReviewContract.ReviewTable.PARENT_ID,
                    ReviewContract.ReviewTable.ACCOUNT
            };

    public ReviewDataSource() {
        super();
    }

    public boolean addReview(Review review) {
        try {
            database.insertOrThrow(
                    ReviewContract.ReviewTable.TABLE_NAME,
                    null,
                    toContentValues(review));
        } catch (SQLiteConstraintException e) {
            return false;
        }
        return true;
    }

    public Review getReviewById(long reviewId) {
        Review review;
        Cursor cursor = database.query(
                ReviewContract.ReviewTable.TABLE_NAME,
                allColumns,
                ReviewContract.ReviewTable.ID + " = ?",
                new String[]{String.valueOf(reviewId)},
                null, null, null
        );

        if (cursor.moveToNext()) review = cuToReview(cursor);
        else throw new Resources.NotFoundException("Restaurant with ID " + reviewId + " not found");

        cursor.close();
        return review;
    }

    public boolean updateReview(Review review) {
        database.update(
                RestaurantContract.RestaurantTable.TABLE_NAME,
                toContentValues(review),
                RestaurantContract.RestaurantTable.ID + " = ?",
                new String[]{String.valueOf(review.getId())}
        );
        return true;
    }

    public boolean deleteReview(long reviewId) {
        database.delete(
                ReviewContract.ReviewTable.TABLE_NAME,
                ReviewContract.ReviewTable.ID + " = ?",
                new String[]{String.valueOf(reviewId)}
        );
        return true;
    }

    List<Review> getReviewsOfItem(long itemId) {
        return getReviews(itemId, "item");
    }

    List<Review> getReviewsOfMenu(long menuId) {
        return getReviews(menuId, "menu");
    }

    List<Review> getReviewsOfRestaurant(long restaurantId) {
        return getReviews(restaurantId, "restaurant");
    }

    List<Review> getReviews(long id, String reviewType) {
        List<Review> restaurantReviews = new ArrayList<>();
        Cursor cursor = database.query(
                ReviewContract.ReviewTable.TABLE_NAME,
                allColumns,
                ReviewContract.ReviewTable.PARENT_TYPE + " = ?" + " AND " +
                        ReviewContract.ReviewTable.PARENT_ID + " = ?",
                new String[]{reviewType, String.valueOf(id)},
                null, null, null);

        while (cursor.moveToNext()) {
            Review review = cuToReview(cursor);
            restaurantReviews.add(review);
        }
        cursor.close();
        return restaurantReviews;
    }


    private Review cuToReview(Cursor cursor) {
        Review review = new Review();
        review.setId(cursor.getLong(0));
        review.setReview(cursor.getString(1));
        review.setParentType(cursor.getString(2));
        review.setParentId(cursor.getLong(3));
        review.setAccount(cursor.getString(4));
        return review;
    }

    private ContentValues toContentValues(Review review) {
        ContentValues values = new ContentValues();
        values.put(ReviewContract.ReviewTable.ID, review.getId());
        values.put(ReviewContract.ReviewTable.REVIEW, review.getReview());
        values.put(ReviewContract.ReviewTable.PARENT_TYPE, review.getParentType());
        values.put(ReviewContract.ReviewTable.PARENT_ID, review.getParentId());
        values.put(ReviewContract.ReviewTable.ACCOUNT, review.getReview());
        return values;
    }

    public void deleteReviews() {
        database.delete(ReviewContract.ReviewTable.TABLE_NAME, null, null);
    }
}
