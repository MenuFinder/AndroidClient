package cat.udl.menufinder.activities;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        changeOrientationIfIsPhone();
        setContentView(R.layout.activity_detail_menu);
        Menu menu = (Menu) getIntent().getSerializableExtra(KEY_MENU);
        configFAB(menu);

        ViewPager mViewPager = (ViewPager) findViewById(R.id.pager);
        setupViewPager(mViewPager, menu);
        // Preparar las pestañas
        TabLayout tabs = (TabLayout) findViewById(R.id.tabs);
        tabs.setupWithViewPager(mViewPager);
    }

    private void configFAB(final Menu menu) {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.add_item_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ReviewMenuActivity.class);
                intent.putExtra(KEY_MENU, menu);
                startActivity(intent);
            }
        });
    }

    private void setupViewPager(ViewPager viewPager, Menu menu) {
        MenuPagerAdapter adapter = new MenuPagerAdapter(getFragmentManager());
        for (Map.Entry<Long, List<Item>> entry : getDbManager().getMenuItemsByCategory(menu.getId()).entrySet()) {
            ItemCategory itemCategory = getDbManager().getItemCategoryById(entry.getKey());
            if (!entry.getValue().isEmpty())
                adapter.addFragment(DetailMenuFragment.newInstance(menu.getId(), entry.getValue()), itemCategory.getName());
        }
        if (adapter.getCount() == 0) {
            showToast(getString(R.string.not_results_founds));
        }
        viewPager.setAdapter(adapter);
    }
}
