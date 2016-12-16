package cat.udl.menufinder.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import cat.udl.menufinder.models.Restaurant;

public abstract class RestaurantDataSource implements DBManager {

    private SQLiteDatabase database;
    private ManagerDbHelper dbHelper;
    private String[] allColumns =
            {
                    RestaurantContract.RestaurantTable.ID,
                    RestaurantContract.RestaurantTable.NAME,
                    RestaurantContract.RestaurantTable.CIF,
                    RestaurantContract.RestaurantTable.ADDRESS,
                    RestaurantContract.RestaurantTable.CITY,
                    RestaurantContract.RestaurantTable.POSTALCODE,
                    RestaurantContract.RestaurantTable.STATE,
                    RestaurantContract.RestaurantTable.COUNTRY,
                    RestaurantContract.RestaurantTable.EMAIL,
                    RestaurantContract.RestaurantTable.PHONE,
                    RestaurantContract.RestaurantTable.ACCOUNT,
                    RestaurantContract.RestaurantTable.SCORE

            };

    public RestaurantDataSource(){
    }

    public RestaurantDataSource(Context context) {
        dbHelper = new ManagerDbHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Restaurant getRestaurantById(long restaurantId){
        Cursor cursor = database.query
                (RestaurantContract.RestaurantTable.TABLE_NAME,
                        allColumns, MenuContract.MenuTable.ID + " =" +
                                restaurantId, null, null, null, null
                );
        cursor.moveToFirst();
        Restaurant restaurant = cuToRestaurant(cursor);
        cursor.close();
        return restaurant;
    }

    public List<Restaurant> getRestaurants() {
        List<Restaurant> allRestaurant = new ArrayList<Restaurant>();
        Cursor cursor = database.query(
                RestaurantContract.RestaurantTable.TABLE_NAME,
                allColumns,
                null, null, null, null, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            Restaurant restaurant = cuToRestaurant(cursor);
            allRestaurant.add(restaurant);
            cursor.moveToNext();
        }
        // Make sure to close the cursor
        cursor.close();
        return allRestaurant;
    }

    public boolean updateRestaurant(Restaurant restaurant, long id) {
        try {
            long RestaurantId = database.update(
                    RestaurantContract.RestaurantTable.TABLE_NAME,
                    toContentValues(restaurant),
                    RestaurantContract.RestaurantTable.ID + " =" + id,
                    null
            );
        } catch (Exception exp) {
            throw (exp);
        }
        return true;
    }

    public boolean deleteRestaurant(long restaurantId) {
        try {
            long RestaurantId = database.delete(
                    RestaurantContract.RestaurantTable.TABLE_NAME,
                    RestaurantContract.RestaurantTable.ID + " =" + restaurantId,
                    null);
        } catch (Exception exp) {
            throw (exp);
        }
        return true;
    }

    public boolean addRestaurant(Restaurant restaurant) {
        try {
            database.insert(
                    RestaurantContract.RestaurantTable.TABLE_NAME,
                    null,
                    toContentValues(restaurant));
        } catch (Exception exp) {
            throw (exp);
        }
        return true;
    }

    private Restaurant cuToRestaurant(Cursor cursor) {
        Restaurant restaurant = new Restaurant();
        restaurant.setId(cursor.getLong(0));
        restaurant.setName(cursor.getString(1));
        restaurant.setCif(cursor.getString(2));
        restaurant.setAddress(cursor.getString(3));
        restaurant.setCity(cursor.getString(4));
        restaurant.setPostalCode(cursor.getString(5));
        restaurant.setCountry(cursor.getString(6));
        restaurant.setEmail(cursor.getString(7));
        restaurant.setPhone(cursor.getString(8));
        restaurant.setAccount(cursor.getString(9));
        restaurant.setScore(cursor.getLong(10));
        return restaurant;
    }

    public ContentValues toContentValues(Restaurant restaurant) {
        ContentValues values = new ContentValues();
        values.put(RestaurantContract.RestaurantTable.ID, restaurant.getId());
        values.put(RestaurantContract.RestaurantTable.NAME, restaurant.getName());
        values.put(RestaurantContract.RestaurantTable.CIF, restaurant.getCif());
        values.put(RestaurantContract.RestaurantTable.ADDRESS, restaurant.getAddress());
        values.put(RestaurantContract.RestaurantTable.CITY, restaurant.getCity());
        values.put(RestaurantContract.RestaurantTable.POSTALCODE, restaurant.getPostalCode());
        values.put(RestaurantContract.RestaurantTable.STATE, restaurant.getState());
        values.put(RestaurantContract.RestaurantTable.COUNTRY, restaurant.getCountry());
        values.put(RestaurantContract.RestaurantTable.EMAIL, restaurant.getEmail());
        values.put(RestaurantContract.RestaurantTable.PHONE, restaurant.getPhone());
        values.put(RestaurantContract.RestaurantTable.ACCOUNT, restaurant.getAccount());
        values.put(RestaurantContract.RestaurantTable.SCORE, restaurant.getScore());
        return values;
    }

}
