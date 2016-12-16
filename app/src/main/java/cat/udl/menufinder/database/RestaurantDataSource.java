package cat.udl.menufinder.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import cat.udl.menufinder.models.Menu;

/**
 * Created by MEUSBURGGER on 16/12/2016.
 */

public abstract class RestaurantDataSource implements DBManager {
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

    public RestaurantDataSource() {
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

    public List<Menu> getMenusByRestaurantId(long restaurantId)
    {
        List<Menu> allMenus = new ArrayList<Menu>();
        Cursor cursor = dbHelper.getReadableDatabase().query(
                MenuContract.MenuTable.TABLE_NAME,
                allColumns,
                MenuContract.MenuTable.RESTAURANT + " =" +
                        restaurantId, null, null, null, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            Menu menu = cuToMenu(cursor);
            allMenus.add(menu);
            cursor.moveToNext();
        }
        // Make sure to close the cursor
        cursor.close();
        return allMenus;

    }

    public Menu getMenuById(Long menuId) {
        Cursor cursor = dbHelper.getReadableDatabase().query
                (MenuContract.MenuTable.TABLE_NAME,
                        allColumns, MenuContract.MenuTable.ID + " =" +
                                menuId, null, null, null, null
                );
        cursor.moveToFirst();
        Menu menu = cuToMenu(cursor);
        cursor.close();
        return menu;
    }

    public List<Menu> getMenus() {
        List<Menu> allMenus = new ArrayList<Menu>();
        Cursor cursor = dbHelper.getReadableDatabase().query(
                MenuContract.MenuTable.TABLE_NAME,
                allColumns,
                null, null, null, null, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            Menu menu = cuToMenu(cursor);
            allMenus.add(menu);
            cursor.moveToNext();
        }
        // Make sure to close the cursor
        cursor.close();
        return allMenus;
    }

    public  boolean updateMenu(Menu menu, Long id) {
        try {
            long MeuId = dbHelper.getWritableDatabase().update(
                    MenuContract.MenuTable.TABLE_NAME,
                    toContentValues(menu),
                    MenuContract.MenuTable.ID + " =" + id,
                    null
            );
        } catch (Exception exp) {
            throw (exp);
        }
        return true;
    }

    public boolean deleteMenu(long menuId) {
        try {
            long ItemId = dbHelper.getWritableDatabase().delete(
                    MenuContract.MenuTable.TABLE_NAME,
                    MenuContract.MenuTable.ID + " =" + menuId,
                    null);
        } catch (Exception exp) {
            throw (exp);
        }
        return true;
    }

    public boolean addMenu(Menu menu){
        try {
            dbHelper.getWritableDatabase().insert(
                    MenuContract.MenuTable.TABLE_NAME,
                    null,
                    toContentValues(menu));
        } catch (Exception exp) {
            throw (exp);
        }
        return true;
    }

    private Menu cuToMenu(Cursor cursor) {
        Menu menu = new Menu();
        menu.setId(cursor.getLong(0));
        menu.setRestaurant(cursor.getLong(1));
        menu.setName(cursor.getString(2));
        menu.setDescription(cursor.getString(3));
        menu.setPrice(cursor.getLong(4));
        menu.setScore(cursor.getLong(5));
        menu.setVisible(cursor.getInt(5));
        return menu;
    }

    public ContentValues toContentValues(Menu menu) {
        ContentValues values = new ContentValues();
        values.put(MenuContract.MenuTable.ID, menu.getId());
        values.put(MenuContract.MenuTable.RESTAURANT, menu.getRestaurant());
        values.put(MenuContract.MenuTable.NAME, menu.getName());
        values.put(MenuContract.MenuTable.DESCRIPTION, menu.getDescription());
        values.put(MenuContract.MenuTable.PRICE, menu.getScore());
        values.put(MenuContract.MenuTable.VISIBLE, menu.isVisible());
        return values;
    }

}
