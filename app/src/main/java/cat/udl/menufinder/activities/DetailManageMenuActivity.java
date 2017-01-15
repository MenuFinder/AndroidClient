package cat.udl.menufinder.activities;

import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Spinner;

import java.util.List;
import java.util.Map;

import cat.udl.menufinder.R;
import cat.udl.menufinder.adapters.ItemCategoriesNameAdapter;
import cat.udl.menufinder.adapters.ItemsNameAdapter;
import cat.udl.menufinder.adapters.MenuPagerAdapter;
import cat.udl.menufinder.fragments.DetailManageMenuFragment;
import cat.udl.menufinder.fragments.DetailMenuFragment;
import cat.udl.menufinder.models.Item;
import cat.udl.menufinder.models.ItemCategory;
import cat.udl.menufinder.models.MenuItem;

public class DetailManageMenuActivity extends DetailMenuActivity {

    private List<ItemCategory> itemCategories;
    private List<Item> restaurantItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        itemCategories = getDbManager().getItemCategories();
        restaurantItems = getDbManager().getRestaurantItems(menu.getRestaurant());
    }

    protected void configFAB() {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.add_item_button);
        fab.setImageResource(R.drawable.ic_add_black);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddDialog();
            }
        });
    }

    private void showAddDialog() {
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.view_add_item, null);
        final Spinner spinnerCategory = (Spinner) dialogView.findViewById(R.id.category);
        final Spinner spinnerItem = (Spinner) dialogView.findViewById(R.id.item);
        spinnerCategory.setAdapter(new ItemCategoriesNameAdapter(DetailManageMenuActivity.this, android.R.layout.simple_list_item_1, itemCategories));
        spinnerItem.setAdapter(new ItemsNameAdapter(DetailManageMenuActivity.this, android.R.layout.simple_list_item_1, restaurantItems));

        final AlertDialog alertDialog = new AlertDialog.Builder(DetailManageMenuActivity.this)
                .setTitle(R.string.add_item)
                .setIcon(R.drawable.menu_finder_logo)
                .setView(dialogView)
                .setPositiveButton(R.string.save, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .create();

        alertDialog.show();
        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedCategoryPosition = spinnerCategory.getSelectedItemPosition();
                int selectedItemPosition = spinnerItem.getSelectedItemPosition();
                saveToDB(itemCategories.get(selectedCategoryPosition), restaurantItems.get(selectedItemPosition));
                alertDialog.dismiss();

            }
        });
    }

    private void saveToDB(ItemCategory itemCategory, Item item) {
        MenuItem menuItem = new MenuItem(menu.getId(), item.getId(), itemCategory.getId());
        getDbManager().addMenuItem(menuItem);
        MenuPagerAdapter adapter = (MenuPagerAdapter) mViewPager.getAdapter();
        DetailMenuFragment menuFragment = (DetailMenuFragment) adapter.getItemFromTitle(itemCategory.getName());
        if (menuFragment != null) {
            menuFragment.listItems.add(item);
            menuFragment.update();
        } else {
            Map<Long, List<Item>> menuItemsByCategory = getDbManager().getMenuItemsByCategory(menu.getId());
            List<Item> items = menuItemsByCategory.get(itemCategory.getId());
            adapter.addFragment(getFragment(items, itemCategory.getId()), itemCategory.getName());
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    protected Fragment getFragment(List<Item> items, long itemCategoryId) {
        return DetailManageMenuFragment.newInstance(menu.getId(), itemCategoryId, items);
    }

    @Override
    protected void noResults() {
        super.noResults();
        showAddDialog();
    }
}
