package cat.udl.menufinder.database;

import android.provider.BaseColumns;


public class ItemContract {
    public static abstract class ItemTable implements BaseColumns {
        public static final String TABLE_NAME = "item";
        public static final String ID = "id";
        public static final String NAME = "name";
        public static final String DESCRIPTION = "description ";
        public static final String PRICE = "price";
        public static final String SCORE = "score";
        public static final String RESTAURANT = "restaurant ";
    }
}
