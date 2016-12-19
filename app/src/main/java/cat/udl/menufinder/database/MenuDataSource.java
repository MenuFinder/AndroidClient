package cat.udl.menufinder.database;

import android.content.ContentValues;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;

import java.util.ArrayList;
import java.util.List;

import cat.udl.menufinder.database.MenuContract.MenuTable;
import cat.udl.menufinder.models.Menu;

public class MenuDataSource extends DataSource {

    private final String[] allColumns =
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
        super();
    }

    public boolean addMenu(Menu menu) {
        try {
            database.insertOrThrow(
                    MenuTable.TABLE_NAME,
                    null,
                    toContentValues(menu));
        } catch (SQLiteConstraintException e) {
            return updateMenu(menu);
        }
        return true;
    }

    public List<Menu> getMenusByRestaurantId(long restaurantId) {
        List<Menu> allMenus = new ArrayList<>();
        Cursor cursor = database.query(
                MenuTable.TABLE_NAME,
                allColumns,
                MenuTable.RESTAURANT + " = ?" + " and " + MenuTable.VISIBLE + " = ?",
                new String[]{String.valueOf(restaurantId), String.valueOf(1)},
                null, null, null
        );

        while (cursor.moveToNext()) {
            Menu menu = cuToMenu(cursor);
            allMenus.add(menu);
        }
        cursor.close();
        return allMenus;
    }

    public Menu getMenuById(long menuId) {
        Menu menu;
        Cursor cursor = database.query(
                MenuTable.TABLE_NAME,
                allColumns,
                MenuTable.ID + " = ?",
                new String[]{String.valueOf(menuId)},
                null, null, null
        );

        if (cursor.moveToNext()) menu = cuToMenu(cursor);
        else throw new Resources.NotFoundException("Menu with ID " + menuId + " not found");

        cursor.close();
        return menu;
    }

    public List<Menu> getMenus(){
        List<Menu> Allmenus = new ArrayList<>();
        Cursor cursor = database.query(
                MenuTable.TABLE_NAME,
                allColumns,
                null, null, null, null, null);

        while (cursor.moveToNext()) {
            Menu itemCategory = cuToMenu(cursor);
            Allmenus.add(itemCategory);
        }
        cursor.close();
        return Allmenus;
    }

    public boolean updateMenu(Menu menu) {
        database.update(
                MenuTable.TABLE_NAME,
                toContentValues(menu),
                MenuTable.ID + " = ?",
                new String[]{String.valueOf(menu.getId())}
        );
        return true;
    }

    public boolean deleteMenu(long menuId) {
        database.delete(
                MenuTable.TABLE_NAME,
                MenuTable.ID + " = ?",
                new String[]{String.valueOf(menuId)}
        );
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
        menu.setVisible(cursor.getInt(6));
        return menu;
    }

    private ContentValues toContentValues(Menu menu) {
        ContentValues values = new ContentValues();
        values.put(MenuTable.ID, menu.getId());
        values.put(MenuTable.RESTAURANT, menu.getRestaurant());
        values.put(MenuTable.NAME, menu.getName());
        values.put(MenuTable.DESCRIPTION, menu.getDescription());
        values.put(MenuTable.PRICE, menu.getPrice());
        values.put(MenuTable.SCORE, menu.getScore());
        values.put(MenuTable.VISIBLE, menu.getVisible());
        return values;
    }
}
