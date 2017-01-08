package cat.udl.menufinder.database;

import android.content.ContentValues;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;

import java.util.ArrayList;
import java.util.List;

import cat.udl.menufinder.models.Restaurant;

public class RestaurantDataSource extends DataSource {

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

    public RestaurantDataSource() {
        super();
    }

    public List<Restaurant> getRestaurants() {
        Cursor cursor = database.query(
                RestaurantContract.RestaurantTable.TABLE_NAME,
                allColumns,
                null, null, null, null, null);

        return getRestaurantFromCursor(cursor);
    }

    public List<Restaurant> getRestaurantsOfAccount(String accountId) {
        Cursor cursor = database.query(
                RestaurantContract.RestaurantTable.TABLE_NAME,
                allColumns,
                RestaurantContract.RestaurantTable.ACCOUNT + " = ?",
                new String[]{accountId},
                null, null, null);

        return getRestaurantFromCursor(cursor);
    }

    public boolean addRestaurant(Restaurant restaurant) {
        try {
            database.insertOrThrow(
                    RestaurantContract.RestaurantTable.TABLE_NAME,
                    null,
                    toContentValues(restaurant));
        } catch (SQLiteConstraintException e) {
            return false;
        }
        return true;
    }

    public Restaurant getRestaurantById(long restaurantId) {
        Restaurant restaurant;
        Cursor cursor = database.query(
                RestaurantContract.RestaurantTable.TABLE_NAME,
                allColumns,
                MenuContract.MenuTable.ID + " = ?",
                new String[]{String.valueOf(restaurantId)},
                null, null, null
        );

        if (cursor.moveToNext()) restaurant = cuToRestaurant(cursor);
        else
            throw new Resources.NotFoundException("Restaurant with ID " + restaurantId + " not found");

        cursor.close();
        return restaurant;
    }

    public List<Restaurant> getFilteredRestaurants(String where) {
        Cursor cursor = database.query(
                RestaurantContract.RestaurantTable.TABLE_NAME,
                allColumns,
                where,
                null, null, null, null);
        return getRestaurantFromCursor(cursor);
    }

    private List<Restaurant> getRestaurantFromCursor(Cursor cursor) {
        List<Restaurant> allRestaurants = new ArrayList<>();

        while (cursor.moveToNext()) {
            Restaurant restaurant = cuToRestaurant(cursor);
            allRestaurants.add(restaurant);
        }
        cursor.close();
        return allRestaurants;
    }

    public boolean updateRestaurant(Restaurant restaurant) {
        database.update(
                RestaurantContract.RestaurantTable.TABLE_NAME,
                toContentValues(restaurant),
                RestaurantContract.RestaurantTable.ID + " = ?",
                new String[]{String.valueOf(restaurant.getId())}
        );
        return true;
    }

    public boolean deleteRestaurant(long restaurantId) {
        database.delete(
                RestaurantContract.RestaurantTable.TABLE_NAME,
                RestaurantContract.RestaurantTable.ID + " = ?",
                new String[]{String.valueOf(restaurantId)}
        );
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
        restaurant.setState(cursor.getString(6));
        restaurant.setCountry(cursor.getString(7));
        restaurant.setEmail(cursor.getString(8));
        restaurant.setPhone(cursor.getString(9));
        restaurant.setAccount(cursor.getString(10));
        restaurant.setScore(cursor.getLong(11));
        return restaurant;
    }

    private ContentValues toContentValues(Restaurant restaurant) {
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

    public void deleteRestaurants() {
        database.delete(RestaurantContract.RestaurantTable.TABLE_NAME, null, null);
    }
}
