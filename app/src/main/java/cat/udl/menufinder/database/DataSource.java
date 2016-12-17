package cat.udl.menufinder.database;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import cat.udl.menufinder.application.MasterApplication;

abstract class DataSource {
    protected Context context;
    protected SQLiteDatabase database;
    private ManagerDbHelper dbHelper;

    DataSource() {
        this.context = MasterApplication.getContext();
        dbHelper = new ManagerDbHelper(context);
        open();
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }
}
