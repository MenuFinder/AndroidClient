package cat.udl.menufinder.database;

import android.content.ContentValues;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;

import java.util.ArrayList;
import java.util.List;

import cat.udl.menufinder.database.ItemContract.ItemTable;
import cat.udl.menufinder.models.Item;

public class ItemDataSource extends DataSource {

    private final String[] allColumns =
            {
                    ItemContract.ItemTable.ID,
                    ItemContract.ItemTable.NAME,
                    ItemContract.ItemTable.DESCRIPTION,
                    ItemContract.ItemTable.PRICE,
                    ItemContract.ItemTable.SCORE,
                    ItemContract.ItemTable.RESTAURANT
            };

    public ItemDataSource() {
        super();
    }

    public boolean addItem(Item item) {
        try {
            database.insertOrThrow(
                    ItemTable.TABLE_NAME,
                    null,
                    toContentValues(item));
        } catch (SQLiteConstraintException e) {
            return false;
        }
        return true;
    }

    public Item getItemById(long itemId) {
        Item item;
        Cursor cursor = database.query(
                ItemTable.TABLE_NAME,
                allColumns,
                ItemTable.ID + " = ?",
                new String[]{String.valueOf(itemId)},
                null, null, null, null
        );

        if (cursor.moveToNext()) item = cuToItem(cursor);
        else throw new Resources.NotFoundException("Item with ID " + itemId + " not found");

        cursor.close();
        return item;
    }

    public List<Item> getItemsByRestaurantId(long restaurantId) {
        List<Item> items = new ArrayList<>();
        Cursor cursor = database.query(
                ItemTable.TABLE_NAME,
                allColumns,
                ItemTable.RESTAURANT + " = ?",
                new String[]{String.valueOf(restaurantId)}, null, null, null);

        while (cursor.moveToNext()) {
            Item itemCategory = cuToItem(cursor);
            items.add(itemCategory);
        }
        cursor.close();
        return items;
    }

    public Boolean updateItem(Item item) {
        database.update(
                ItemTable.TABLE_NAME,
                toContentValues(item),
                ItemTable.ID + " = ?",
                new String[]{String.valueOf(item.getId())}
        );
        return true;
    }

    public Boolean deleteItem(long itemId) {
        database.delete(
                ItemTable.TABLE_NAME,
                ItemTable.ID + " = ?",
                new String[]{String.valueOf(itemId)}
        );
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

    private ContentValues toContentValues(Item item) {
        ContentValues values = new ContentValues();
        values.put(ItemContract.ItemTable.ID, item.getId());
        values.put(ItemContract.ItemTable.NAME, item.getName());
        values.put(ItemContract.ItemTable.DESCRIPTION, item.getDescription());
        values.put(ItemContract.ItemTable.PRICE, item.getPrice());
        values.put(ItemContract.ItemTable.SCORE, item.getScore());
        values.put(ItemContract.ItemTable.RESTAURANT, item.getRestaurant());
        return values;
    }

    public void deleteItems() {
        database.delete(ItemContract.ItemTable.TABLE_NAME, null, null);
    }
}
