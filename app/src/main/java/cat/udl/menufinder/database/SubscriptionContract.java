package cat.udl.menufinder.database;

import android.provider.BaseColumns;

public class SubscriptionContract {
    public static abstract class SuscriptionTable implements BaseColumns {
        public static final String TABLE_NAME = "subscription";
        public static final String ACCOUNT = "account";
        public static final String RESTAURANT = "restaurant";
    }
}
