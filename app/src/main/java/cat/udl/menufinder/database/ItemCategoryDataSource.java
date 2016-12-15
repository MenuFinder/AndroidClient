package cat.udl.menufinder.database;
/**
 * Created by MEUSBURGGER on 15/12/2016.
 */

import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import cat.udl.menufinder.database.ItemCategoryContract.ItemCategoryTable;
import cat.udl.menufinder.models.ItemCategory;

public class ItemCategoryDataSource {

    private SQLiteDatabase database;
    private ManagerDbHelper dbHelper;
    private String id;
    private String name;
    private String description;
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

    public Cursor getItemCategoryById(long ItemCategoryId) {
        Cursor cursor = dbHelper.getReadableDatabase().query
                (ItemCategoryTable.TABLE_NAME,
                        allColumns, ItemCategoryTable.ID + " =" +
                                ItemCategoryId, null, null, null, null
                );
        cursor.moveToFirst();
        cursor.close();
        return cursor;
    }

    public long updateItemCategory(ItemCategory itemcategory, Long ItemCategoryId) {
        return dbHelper.getWritableDatabase().update(
                ItemCategoryTable.TABLE_NAME,
                toContentValues(itemcategory),
                ItemCategoryTable.ID + " ="+ItemCategoryId,
                null
        );
    }

    public long saveItemCategory(ItemCategory itemcategory) {
        return dbHelper.getWritableDatabase().insert(
                ItemCategoryTable.TABLE_NAME,
                null,
                toContentValues(itemcategory));
    }

    public int deleteItemCategory(Long ItemCategoryId) {
        return dbHelper.getWritableDatabase().delete(
                ItemCategoryTable.TABLE_NAME,
                ItemCategoryTable.ID + " ="+ItemCategoryId,
                null);
    }

    public Cursor getItemCategory() {
        return dbHelper.getReadableDatabase().query(
                ItemCategoryTable.TABLE_NAME,
                allColumns,
                null, null, null, null, null);
    }

    public ContentValues toContentValues(ItemCategory itemcategory) {
        ContentValues values = new ContentValues();
        values.put(ItemCategoryTable.ID, itemcategory.getId());
        values.put(ItemCategoryTable.NAME, itemcategory.getName());
        values.put(ItemCategoryTable.DESCRIPTION, itemcategory.getDescription());
        return values;
    }

}
