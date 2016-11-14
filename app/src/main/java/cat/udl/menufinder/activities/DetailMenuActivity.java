package cat.udl.menufinder.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import cat.udl.menufinder.R;
import cat.udl.menufinder.application.MasterActivity;
import cat.udl.menufinder.fragments.DetailMenuFragment;
import cat.udl.menufinder.models.Menu;

import static cat.udl.menufinder.utils.Constants.KEY_MENU;

public class DetailMenuActivity extends MasterActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        Menu menu = (Menu) intent.getSerializableExtra(KEY_MENU);
        DetailMenuFragment fragment = (DetailMenuFragment) getSupportFragmentManager().findFragmentById(R.id.detail_fragment);
        fragment.update(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return false;
    }
}
