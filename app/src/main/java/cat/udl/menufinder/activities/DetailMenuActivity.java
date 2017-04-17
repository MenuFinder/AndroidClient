package cat.udl.menufinder.activities;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.List;
import java.util.Map;

import cat.udl.menufinder.R;
import cat.udl.menufinder.adapters.MenuPagerAdapter;
import cat.udl.menufinder.application.MasterActivity;
import cat.udl.menufinder.fragments.DetailMenuFragment;
import cat.udl.menufinder.models.Item;
import cat.udl.menufinder.models.ItemCategory;
import cat.udl.menufinder.models.Menu;

import static cat.udl.menufinder.utils.Constants.KEY_MENU;

public class DetailMenuActivity extends MasterActivity {

    protected Menu menu;
    protected ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        changeOrientationIfIsPhone();
        setDisplayHomeAsUpEnable();
        setContentView(R.layout.activity_detail_menu);
        menu = (Menu) getIntent().getSerializableExtra(KEY_MENU);
        configFAB();
        mViewPager = (ViewPager) findViewById(R.id.pager);
        TabLayout tabs = (TabLayout) findViewById(R.id.tabs);
        tabs.setupWithViewPager(mViewPager);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setupViewPager();
    }

    protected void configFAB() {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.add_item_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reviewMenu();
            }
        });
    }

    private void reviewMenu() {
        Intent intent = new Intent(DetailMenuActivity.this, ReviewMenuActivity.class);
        intent.putExtra(KEY_MENU, menu);
        startActivity(intent);
    }

    protected void setupViewPager() {
        MenuPagerAdapter adapter = new MenuPagerAdapter(getFragmentManager());
        for (Map.Entry<Long, List<Item>> entry : getDbManager().getMenuItemsByCategory(menu.getId()).entrySet()) {
            ItemCategory itemCategory = getDbManager().getItemCategoryById(entry.getKey());
            if (!entry.getValue().isEmpty())
                adapter.addFragment(getFragment(entry.getValue(), itemCategory.getId()), itemCategory.getName());
        }

        if (adapter.getCount() == 0) noResults();

        mViewPager.setAdapter(adapter);
    }

    protected void noResults() {
        showToast(getString(R.string.not_results_founds));
    }

    protected Fragment getFragment(List<Item> items, long itemCategoryId) {
        return DetailMenuFragment.newInstance(menu.getId(), itemCategoryId, items);
    }
}
