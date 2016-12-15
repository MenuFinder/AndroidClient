package cat.udl.menufinder.database;

/**
 * Created by MEUSBURGGER on 15/12/2016.
 */

import android.database.Cursor;
import java.util.List;
import java.util.ArrayList;
import cat.udl.menufinder.models.ItemCategory;

public abstract class ItemCategoryManager implements DBManager {

    public ItemCategory getItemCategoryById(Long id) {

        ItemCategoryDataSource dataSource = new ItemCategoryDataSource();
        Cursor cursor = dataSource.getItemCategoryById(id);
        cursor.moveToFirst();
        ItemCategory itemCategory = cuToItemCategory(cursor);
        cursor.close();
        return itemCategory;
    }

    public List<ItemCategory> getItemCategories()
    {
        List<ItemCategory> itemCategories = new ArrayList<ItemCategory>();
        ItemCategoryDataSource dataSource = new ItemCategoryDataSource();
        Cursor cursor = dataSource.getItemCategory();
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

    public Boolean updateItemCategory(ItemCategory itemCategory, Long id) {
        try {
            ItemCategoryDataSource dataSource = new ItemCategoryDataSource();
            long ItemId= dataSource.updateItemCategory(itemCategory, id);
        } catch (Exception exp) {
            throw (exp);
        }
        return true;
    }

    public Boolean deleteItemCategory(Long id) {
        try {
            ItemCategoryDataSource dataSource = new ItemCategoryDataSource();
            long ItemId =  dataSource.deleteItemCategory(id);
        } catch (Exception exp) {
            throw (exp);
        }
        return true;
    }

    public Boolean addItemCategory(ItemCategory itemCategory){
        try {
            ItemCategoryDataSource dataSource = new ItemCategoryDataSource();
            dataSource.saveItemCategory(itemCategory);
        }
        catch(Exception exp){
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
}
