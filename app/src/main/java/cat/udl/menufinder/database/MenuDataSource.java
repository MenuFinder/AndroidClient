package cat.udl.menufinder.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import cat.udl.menufinder.models.Menu;
import cat.udl.menufinder.database.MenuContract.MenuTable;

public abstract class MenuDataSource implements DBManager {

    private SQLiteDatabase database;
    private ManagerDbHelper dbHelper;
    private String[] allColumns =
            {
                    MenuContract.MenuTable.ID,
                    MenuContract.MenuTable.RESTAURANT,
                    MenuContract.MenuTable.NAME,
                    MenuContract.MenuTable.DESCRIPTION,
                    MenuContract.MenuTable.PRICE,
                    MenuContract.MenuTable.SCORE,
                    MenuContract.MenuTable.VISIBLE
            };

    public MenuDataSource() {
    }

    public MenuDataSource(Context context) {
        dbHelper = new ManagerDbHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public List<Menu> getMenusByRestaurantId(long restaurantId) {
        List<Menu> allMenus = new ArrayList<Menu>();
        Cursor cursor = database.query(
                MenuTable.TABLE_NAME,
                allColumns,
                MenuTable.RESTAURANT + " =" +
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
        Cursor cursor = database.query
                (MenuTable.TABLE_NAME,
                        allColumns, MenuTable.ID + " =" +
                                menuId, null, null, null, null
                );
        cursor.moveToFirst();
        Menu menu = cuToMenu(cursor);
        cursor.close();
        return menu;
    }

    public List<Menu> getMenus() {
        List<Menu> allMenus = new ArrayList<Menu>();
        Cursor cursor = database.query(
                MenuTable.TABLE_NAME,
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

    public boolean updateMenu(Menu menu, Long id) {
        try {
            long MeuId = database.update(
                    MenuTable.TABLE_NAME,
                    toContentValues(menu),
                    MenuTable.ID + " =" + id,
                    null
            );
        } catch (Exception exp) {
            throw (exp);
        }
        return true;
    }

    public boolean deleteMenu(long menuId) {
        try {
            long ItemId = database.delete(
                    MenuTable.TABLE_NAME,
                    MenuTable.ID + " =" + menuId,
                    null);
        } catch (Exception exp) {
            throw (exp);
        }
        return true;
    }

    public boolean addMenu(Menu menu) {
        try {
            database.insert(
                    MenuTable.TABLE_NAME,
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
        values.put(MenuTable.ID, menu.getId());
        values.put(MenuTable.RESTAURANT, menu.getRestaurant());
        values.put(MenuTable.NAME, menu.getName());
        values.put(MenuTable.DESCRIPTION, menu.getDescription());
        values.put(MenuTable.PRICE, menu.getScore());
        values.put(MenuTable.VISIBLE, menu.isVisible());
        return values;
    }
}
