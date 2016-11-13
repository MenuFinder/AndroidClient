package cat.udl.menufinder.database;

/**
 * Created by zuluku on 13/11/16.
 */
public class FakeDB {
    private static FakeDB ourInstance = new FakeDB();

    public static FakeDB getInstance() {
        return ourInstance;
    }

    private FakeDB() {
    }
}
