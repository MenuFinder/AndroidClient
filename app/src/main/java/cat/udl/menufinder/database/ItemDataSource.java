package cat.udl.menufinder.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;
import java.util.ArrayList;

import cat.udl.menufinder.models.Item;
import cat.udl.menufinder.database.ItemContract.ItemTable;

public abstract class ItemDataSource implements DBManager {

    private SQLiteDatabase database;
    private ManagerDbHelper dbHelper;
    private String[] allColumns =
            {
                    ItemContract.ItemTable.ID,
                    ItemContract.ItemTable.NAME,
                    ItemContract.ItemTable.DESCRIPTION,
                    ItemContract.ItemTable.PRICE,
                    ItemContract.ItemTable.SCORE,
                    ItemContract.ItemTable.RESTAURANT
            };

    public ItemDataSource() {
    }

    public ItemDataSource(Context context) {
        dbHelper = new ManagerDbHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Item getItemById(Long id) {

        Cursor cursor = dbHelper.getReadableDatabase().query
                (ItemTable.TABLE_NAME,
                        allColumns, ItemTable.ID + " =" +
                                id, null, null, null, null
                );
        cursor.moveToFirst();
        Item item = cuToItem(cursor);
        cursor.close();
        return item;
    }

    public List<Item> getRestaurantItems(long restaurantId) {
        List<Item> items = new ArrayList<Item>();
        Cursor cursor = dbHelper.getReadableDatabase().query(
                ItemTable.TABLE_NAME,
                allColumns,
                ItemTable.RESTAURANT + " =" + restaurantId, null, null, null, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            Item itemCategory = cuToItem(cursor);
            items.add(itemCategory);
            cursor.moveToNext();
        }
        // Make sure to close the cursor
        cursor.close();
        return items;
    }

    public Boolean updateItem(Item item, Long id) {
        try {
            long ItemId = dbHelper.getWritableDatabase().update(
                    ItemTable.TABLE_NAME,
                    toContentValues(item),
                    ItemTable.ID + " =" + id,
                    null
            );
        } catch (Exception exp) {
            throw (exp);
        }
        return true;
    }


    public Boolean deleteItem(Long id) {
        try {
            long ItemId = dbHelper.getWritableDatabase().delete(
                    ItemTable.TABLE_NAME,
                    ItemTable.ID + " =" + id,
                    null);
        } catch (Exception exp) {
            throw (exp);
        }
        return true;
    }

    public boolean addItem(Item item) {
        try {
            dbHelper.getWritableDatabase().insert(
                    ItemTable.TABLE_NAME,
                    null,
                    toContentValues(item));
        } catch (Exception exp) {
            throw (exp);
        }
        return true;
    }

    private Item cuToItem(Cursor cursor) {
        Item item = new Item();
        item.setId(cursor.getLong(0));
        item.setName(cursor.getString(1));
        item.setDescription(cursor.getString(2));
        item.setPrice(cursor.getLong(3));
        item.setScore(cursor.getLong(4));
        item.setRestaurant(cursor.getLong(5));
        return item;
    }

    public ContentValues toContentValues(Item item) {
        ContentValues values = new ContentValues();
        values.put(ItemContract.ItemTable.ID, item.getId());
        values.put(ItemContract.ItemTable.NAME, item.getName());
        values.put(ItemContract.ItemTable.DESCRIPTION, item.getDescription());
        values.put(ItemContract.ItemTable.PRICE, item.getPrice());
        values.put(ItemContract.ItemTable.SCORE, item.getScore());
        values.put(ItemContract.ItemTable.RESTAURANT, item.getRestaurant());
        return values;
    }
}
