package cat.udl.menufinder.database;

import android.content.ContentValues;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;

import java.util.ArrayList;
import java.util.List;

import cat.udl.menufinder.database.ItemCategoryContract.ItemCategoryTable;
import cat.udl.menufinder.models.ItemCategory;

public class ItemCategoryDataSource extends DataSource {

    private final String[] allColumns =
            {
                    ItemCategoryTable.ID,
                    ItemCategoryTable.DESCRIPTION,
                    ItemCategoryTable.NAME
            };

    public ItemCategoryDataSource() {
        super();
    }

    public boolean addItemCategory(ItemCategory itemCategory) {
        try {
            database.insertOrThrow(
                    ItemCategoryTable.TABLE_NAME,
                    null,
                    toContentValues(itemCategory));
        } catch (SQLiteConstraintException e) {
            return false;
        }
        return true;
    }

    public ItemCategory getItemCategoryById(long itemCategoryId) {
        ItemCategory itemCategory;
        Cursor cursor = database.query(
                ItemCategoryTable.TABLE_NAME,
                allColumns, ItemCategoryTable.ID + " = ?",
                new String[]{String.valueOf(itemCategoryId)},
                null, null, null
        );

        if (cursor.moveToNext()) itemCategory = cuToItemCategory(cursor);
        else
            throw new Resources.NotFoundException("Item Category with ID " + itemCategoryId + " not found");

        cursor.close();
        return itemCategory;
    }

    public List<ItemCategory> getItemCategories() {
        List<ItemCategory> itemCategories = new ArrayList<>();
        Cursor cursor = database.query(
                ItemCategoryTable.TABLE_NAME,
                allColumns,
                null, null, null, null, null);

        while (cursor.moveToNext()) {
            ItemCategory itemCategory = cuToItemCategory(cursor);
            itemCategories.add(itemCategory);
        }
        cursor.close();
        return itemCategories;
    }

    public boolean updateItemCategory(ItemCategory itemCategory, long itemCategoryId) {
        database.update(
                ItemCategoryTable.TABLE_NAME,
                toContentValues(itemCategory),
                ItemCategoryTable.ID + " = ?",
                new String[]{String.valueOf(itemCategoryId)}
        );
        return true;
    }

    public boolean deleteItemCategory(long itemCategoryId) {
        database.delete(
                ItemCategoryTable.TABLE_NAME,
                ItemCategoryTable.ID + " = ?",
                new String[]{String.valueOf(itemCategoryId)}
        );
        return true;
    }

    private ItemCategory cuToItemCategory(Cursor cursor) {
        ItemCategory itemCategory = new ItemCategory();
        itemCategory.setId(cursor.getLong(0));
        itemCategory.setName(cursor.getString(1));
        itemCategory.setDescription(cursor.getString(2));
        return itemCategory;
    }

    private ContentValues toContentValues(ItemCategory itemcategory) {
        ContentValues values = new ContentValues();
        values.put(ItemCategoryTable.ID, itemcategory.getId());
        values.put(ItemCategoryTable.NAME, itemcategory.getName());
        values.put(ItemCategoryTable.DESCRIPTION, itemcategory.getDescription());
        return values;
    }

}
