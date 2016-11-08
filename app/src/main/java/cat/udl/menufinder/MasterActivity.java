package cat.udl.menufinder;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class MasterActivity extends AppCompatActivity {

    void showToast(int text_id) {
        Toast.makeText(MasterActivity.this, text_id, Toast.LENGTH_SHORT).show();
    }

    void showToast(String text) {
        Toast.makeText(MasterActivity.this, text, Toast.LENGTH_SHORT).show();
    }

}
