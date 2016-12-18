package cat.udl.menufinder.database;


import android.content.ContentValues;
import android.database.sqlite.SQLiteConstraintException;

import java.util.List;
import java.util.Map;

import cat.udl.menufinder.models.Item;
import cat.udl.menufinder.models.MenuItem;

public class SubscriptionDataSource extends DataSource {

    private String[] allColumns =
            {
                    SubscriptionContract.SuscriptionTable.ACCOUNT,
                    SubscriptionContract.SuscriptionTable.RESTAURANT
            };

    public SubscriptionDataSource() {
        super();
    }

    public boolean addMenuItem(MenuItem menuItem) {
        try {
            database.insertOrThrow(
                    MenuItemContract.MenuItemTable.TABLE_NAME,
                    null,
                    toContentValues(menuItem));
        } catch (SQLiteConstraintException e) {
            return false;
        }
        return true;
    }

    public boolean deleteMenuItem(MenuItem menuItem) {
        database.delete(
                MenuItemContract.MenuItemTable.TABLE_NAME,
                MenuItemContract.MenuItemTable.MENU + " = ?",
                new String[]{String.valueOf(menuItem.getItem())}
        );
        return true;
    }

    public Map<Long, List<Item>> getMenuItemsByCategory(long menuId) {
        return null;
    }

    private ContentValues toContentValues(MenuItem menuItem) {
        ContentValues values = new ContentValues();
        values.put(MenuItemContract.MenuItemTable.MENU, menuItem.getMenu());
        values.put(MenuItemContract.MenuItemTable.ITEM, menuItem.getItem());
        values.put(MenuItemContract.MenuItemTable.ITEMCATEGORY, menuItem.getItemCategory());

        return values;
    }
}
