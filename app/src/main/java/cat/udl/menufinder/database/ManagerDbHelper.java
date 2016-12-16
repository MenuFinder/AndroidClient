package cat.udl.menufinder.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import cat.udl.menufinder.database.ItemCategoryContract.ItemCategoryTable;
import cat.udl.menufinder.database.ItemContract.ItemTable;
import cat.udl.menufinder.database.MenuContract.MenuTable;
import cat.udl.menufinder.database.MenuItemContract.MenuItemTable;
import cat.udl.menufinder.database.RestaurantContract.RestaurantTable;
import cat.udl.menufinder.database.SuscriptionContract.SuscriptionTable;

/**
 * Created by MEUSBURGGER on 14/12/2016.
 */

public class ManagerDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "MenuFinder.db";

    public ManagerDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Create DataBase
        db.execSQL("CREATE DATABASE " + ManagerDbHelper.DATABASE_NAME);
        //Create Tables
        db.execSQL("CREATE TABLE " + ItemCategoryTable.TABLE_NAME + " ("
                + ItemCategoryTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + ItemCategoryTable.ID + " INTEGER NOT NULL,"
                + ItemCategoryTable.NAME + " TEXT NOT NULL,"
                + ItemCategoryTable.DESCRIPTION + " TEXT NOT NULL,"
                + "UNIQUE (" + ItemCategoryTable.ID + "))");

        db.execSQL("CREATE TABLE " + ItemTable.TABLE_NAME + " ("
                + ItemTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + ItemTable.ID + " INTEGER NOT NULL,"
                + ItemTable.NAME + " TEXT NOT NULL,"
                + ItemTable.DESCRIPTION + " TEXT,"
                + ItemTable.PRICE + " DOUBLE,"
                + ItemTable.SCORE + " DOUBLE,"
                + ItemTable.RESTAURANT + " INTEGER NOT NULL,"
                + "UNIQUE (" + ItemTable.ID + "))");

        db.execSQL("CREATE TABLE " + MenuTable.TABLE_NAME + " ("
                + MenuTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + MenuTable.ID + " INTEGER NOT NULL,"
                + MenuTable.RESTAURANT + " TEXT NOT NULL,"
                + MenuTable.NAME + " TEXT NOT NULL,"
                + MenuTable.DESCRIPTION + " TEXT,"
                + MenuTable.PRICE + " TEXT,"
                + MenuTable.SCORE + " TEXT,"
                + MenuTable.VISIBLE + " CHAR,"
                + "UNIQUE (" + MenuTable.ID + "))");

        db.execSQL("CREATE TABLE " + MenuItemTable.TABLE_NAME + " ("
                + MenuItemTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + MenuItemTable.MENU + " INTEGER NOT NULL,"
                + MenuItemTable.ITEM + " INTEGER NOT NULL,"
                + MenuItemTable.ITEMCATEGORY + " INTEGER NOT NULL," + "))");

        db.execSQL("CREATE TABLE " + RestaurantTable.TABLE_NAME + " ("
                + RestaurantTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + RestaurantTable.ID + " INTEGER NOT NULL,"
                + RestaurantTable.NAME + " TEXT NOT NULL,"
                + RestaurantTable.CIF + " TEXT,"
                + RestaurantTable.ADDRESS + " TEXT,"
                + RestaurantTable.CITY + " TEXT,"
                + RestaurantTable.POSTALCODE + " TEXT,"
                + RestaurantTable.STATE + " TEXT,"
                + RestaurantTable.COUNTRY + " TEXT,"
                + RestaurantTable.EMAIL + " TEXT,"
                + RestaurantTable.PHONE + " TEXT,"
                + RestaurantTable.ACCOUNT + " TEXT NOT NULL,"
                + "UNIQUE (" + RestaurantTable.ID + "))");

        db.execSQL("CREATE TABLE " + SuscriptionTable.TABLE_NAME + " ("
                + SuscriptionTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + SuscriptionTable.ACCOUNT + " INTEGER NOT NULL,"
                + SuscriptionTable.RESTAURANT + " TEXT NOT NULL," + "))");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(ManagerDbHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + ItemCategoryTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + ItemTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + MenuTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + MenuItemTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + RestaurantTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + SuscriptionTable.TABLE_NAME);

        onCreate(db);
    }


}
