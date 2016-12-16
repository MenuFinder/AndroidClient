package cat.udl.menufinder.database;

import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import cat.udl.menufinder.database.ItemCategoryContract.ItemCategoryTable;
import cat.udl.menufinder.models.ItemCategory;

public abstract class ItemCategoryDataSource implements DBManager {

    private SQLiteDatabase database;
    private ManagerDbHelper dbHelper;
    private String[] allColumns =
            {
                    ItemCategoryTable.ID,
                    ItemCategoryTable.DESCRIPTION,
                    ItemCategoryTable.NAME
            };

    public ItemCategoryDataSource() {
    }

    public ItemCategoryDataSource(Context context) {
        dbHelper = new ManagerDbHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public ItemCategory getItemCategoryById(Long id) {

        Cursor cursor = database.query
                (ItemCategoryTable.TABLE_NAME,
                        allColumns, ItemCategoryTable.ID + " =" +
                                id, null, null, null, null
                );
        cursor.moveToFirst();
        ItemCategory itemCategory = cuToItemCategory(cursor);
        cursor.close();
        return itemCategory;
    }

    public List<ItemCategory> getItemCategories() {
        List<ItemCategory> itemCategories = new ArrayList<ItemCategory>();
        Cursor cursor = database.query(
                ItemCategoryTable.TABLE_NAME,
                allColumns,
                null, null, null, null, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            ItemCategory itemCategory = cuToItemCategory(cursor);
            itemCategories.add(itemCategory);
            cursor.moveToNext();
        }
        // Make sure to close the cursor
        cursor.close();
        return itemCategories;
    }

    public boolean updateItemCategory(ItemCategory itemCategory, Long id) {
        try {
            long ItemId = database.update(
                    ItemCategoryTable.TABLE_NAME,
                    toContentValues(itemCategory),
                    ItemCategoryTable.ID + " =" + id,
                    null
            );
        } catch (Exception exp) {
            throw (exp);
        }
        return true;
    }


    public boolean deleteItemCategory(Long id) {
        try {
            long ItemId = database.delete(
                    ItemCategoryTable.TABLE_NAME,
                    ItemCategoryTable.ID + " =" + id,
                    null);
        } catch (Exception exp) {
            throw (exp);
        }
        return true;
    }

    public boolean addItemCategory(ItemCategory itemCategory) {
        try {
            database.insert(
                    ItemCategoryTable.TABLE_NAME,
                    null,
                    toContentValues(itemCategory));
        } catch (Exception exp) {
            throw (exp);
        }
        return true;
    }

    private ItemCategory cuToItemCategory(Cursor cursor) {
        ItemCategory itemCategory = new ItemCategory();
        itemCategory.setId(cursor.getLong(0));
        itemCategory.setName(cursor.getString(1));
        itemCategory.setDescription(cursor.getString(2));
        return itemCategory;
    }


    public ContentValues toContentValues(ItemCategory itemcategory) {
        ContentValues values = new ContentValues();
        values.put(ItemCategoryTable.ID, itemcategory.getId());
        values.put(ItemCategoryTable.NAME, itemcategory.getName());
        values.put(ItemCategoryTable.DESCRIPTION, itemcategory.getDescription());
        return values;
    }

}
