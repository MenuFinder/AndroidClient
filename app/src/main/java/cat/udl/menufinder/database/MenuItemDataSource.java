package cat.udl.menufinder.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cat.udl.menufinder.application.MasterApplication;
import cat.udl.menufinder.models.Item;
import cat.udl.menufinder.models.MenuItem;


public class MenuItemDataSource extends DataSource {

    private String[] allColumns =
            {
                    MenuItemContract.MenuItemTable.MENU,
                    MenuItemContract.MenuItemTable.ITEM,
                    MenuItemContract.MenuItemTable.ITEMCATEGORY
            };

    public MenuItemDataSource() {
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
        Map<Long, List<Item>> map = new HashMap<>();
        Cursor cursor = database.query(
                MenuItemContract.MenuItemTable.TABLE_NAME,
                allColumns,
                MenuItemContract.MenuItemTable.MENU + " = ?",
                new String[]{String.valueOf(menuId)},
                null, null, null);

        while (cursor.moveToNext()) {
            MenuItem menuItem = cuToMenuItem(cursor);
            if (!map.containsKey(menuItem.getItemCategory()))
                map.put(menuItem.getItemCategory(), new ArrayList<Item>());
            Item item = MasterApplication.getContext().getDbManager().getItemById(menuItem.getItem());
            map.get(menuItem.getItemCategory()).add(item);
        }
        cursor.close();
        return map;
    }

    private MenuItem cuToMenuItem(Cursor cursor) {
        MenuItem menuItem = new MenuItem();
        menuItem.setMenu(cursor.getLong(0));
        menuItem.setItem(cursor.getLong(1));
        menuItem.setItemCategory(cursor.getLong(2));
        return menuItem;
    }

    private ContentValues toContentValues(MenuItem menuItem) {
        ContentValues values = new ContentValues();
        values.put(MenuItemContract.MenuItemTable.MENU, menuItem.getMenu());
        values.put(MenuItemContract.MenuItemTable.ITEM, menuItem.getItem());
        values.put(MenuItemContract.MenuItemTable.ITEMCATEGORY, menuItem.getItemCategory());
        return values;
    }
}
