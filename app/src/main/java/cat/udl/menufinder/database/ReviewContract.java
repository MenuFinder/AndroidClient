package cat.udl.menufinder.database;

import android.provider.BaseColumns;

public class ReviewContract {
    public static abstract class ReviewTable implements BaseColumns {
        public static final String TABLE_NAME = "review";
        public static final String ID = "id";
        public static final String REVIEW = "review";
        public static final String PARENT_TYPE = "parent_type";
        public static final String PARENT_ID = "parent_id";
        public static final String ACCOUNT = "account";
    }
}
