package cat.udl.menufinder.database;

import android.provider.BaseColumns;

/**
 * Created by MEUSBURGGER on 14/12/2016.
 */

public class MenuContract {
    public static abstract class MenuTable implements BaseColumns {
        public static final String TABLE_NAME = "menu";
        public static final String ID = "id";
        public static final String RESTAURANT = "restaurant";
        public static final String NAME = "name";
        public static final String DESCRIPTION = "description";
        public static final String PRICE = "price";
        public static final String SCORE = "score";
        public static final String VISIBLE = "visible";
    }
}
