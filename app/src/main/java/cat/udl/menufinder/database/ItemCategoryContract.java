package cat.udl.menufinder.database;

import android.provider.BaseColumns;

/**
 * Created by MEUSBURGGER on 14/12/2016.
 */

public class ItemCategoryContract {
    public static abstract class ItemCategoryTable implements BaseColumns {
        public static final String TABLE_NAME = "itemcategory";
        public static final String ID = "id";
        public static final String NAME = "name";
        public static final String DESCRIPTION = "description ";
    }
}
