package cat.udl.menufinder.database;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import cat.udl.menufinder.application.MasterApplication;
import cat.udl.menufinder.models.AccountSubscription;
import cat.udl.menufinder.models.Restaurant;

public class SubscriptionDataSource extends DataSource {

    private String[] allColumns =
            {
                    SubscriptionContract.SubscriptionTable.ACCOUNT,
                    SubscriptionContract.SubscriptionTable.RESTAURANT
            };

    public SubscriptionDataSource() {
        super();
    }

    public boolean addSubscription(AccountSubscription subscription) {
        Log.d("Subscription", subscription.toString());
        try {
            database.insertOrThrow(
                    SubscriptionContract.SubscriptionTable.TABLE_NAME,
                    null,
                    toContentValues(subscription));
        } catch (SQLiteConstraintException e) {
            return false;
        }
        return true;
    }

    public List<Restaurant> getSubscribedRestaurants(String accountId) {
        List<Restaurant> allRestaurants = new ArrayList<>();
        Cursor cursor = database.query(
                SubscriptionContract.SubscriptionTable.TABLE_NAME,
                allColumns,
                SubscriptionContract.SubscriptionTable.ACCOUNT + " = ?",
                new String[]{accountId},
                null, null, null);

        DBManager dbManager = MasterApplication.getContext().getDbManager();
        while (cursor.moveToNext()) {
            AccountSubscription subscription = cuToSubscription(cursor);
            allRestaurants.add(dbManager.getRestaurantById(subscription.getRestaurant()));
        }
        cursor.close();
        return allRestaurants;
    }

    private AccountSubscription cuToSubscription(Cursor cursor) {
        AccountSubscription subscription = new AccountSubscription();
        subscription.setAccount(cursor.getString(0));
        subscription.setRestaurant(cursor.getLong(1));
        return subscription;
    }

    public boolean deleteSubscription(AccountSubscription subscription) {
        database.delete(
                SubscriptionContract.SubscriptionTable.TABLE_NAME,
                SubscriptionContract.SubscriptionTable.ACCOUNT + " = ?" +
                        " AND " + SubscriptionContract.SubscriptionTable.RESTAURANT + " = ?",
                new String[]{subscription.getAccount(), String.valueOf(subscription.getRestaurant())}
        );
        return true;
    }

    private ContentValues toContentValues(AccountSubscription subscription) {
        ContentValues values = new ContentValues();
        values.put(SubscriptionContract.SubscriptionTable.ACCOUNT, subscription.getAccount());
        values.put(SubscriptionContract.SubscriptionTable.RESTAURANT, subscription.getRestaurant());
        return values;
    }
}
