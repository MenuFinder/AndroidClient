package cat.udl.menufinder.database;
import android.provider.BaseColumns;

/**
 * Created by MEUSBURGGER on 14/12/2016.
 */

public class RestaurantContract {
    public static abstract class RestaurantTable implements BaseColumns {
        public static final String TABLE_NAME ="restaurant";
        public static final String ID = "id";
        public static final String NAME = "name";
        public static final String CIF = "cif";
        public static final String ADDRESS ="address";
        public static final String CITY = "city";
        public static final String POSTALCODE = "postalcode";
        public static final String STATE = "state";
        public static final String COUNTRY = "country";
        public static final String EMAIL ="email";
        public static final String PHONE = "phone";
        public static final String ACCOUNT = "account";
    }
}
