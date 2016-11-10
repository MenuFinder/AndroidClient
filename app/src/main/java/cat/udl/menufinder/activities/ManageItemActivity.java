package cat.udl.menufinder.activities;

import android.support.design.widget.FloatingActionButton;
import android.os.Bundle;
import android.view.View;

import cat.udl.menufinder.R;
import cat.udl.menufinder.application.MasterActivity;

public class ManageItemActivity extends MasterActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_item);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.save_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showToast(R.string.item_saved);
            }
        });
    }
}
