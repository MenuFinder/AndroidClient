package cat.udl.menufinder.database;
import android.provider.BaseColumns;

/**
 * Created by MEUSBURGGER on 14/12/2016.
 */

public class MenuItemContract {
    public static abstract class MenuItemTable implements BaseColumns {
        public static final String TABLE_NAME ="menuitem";
        public static final String MENU = "menu";
        public static final String ITEM = "item";
        public static final String ITEMCATEGORY = "itemcategory";
    }
}
