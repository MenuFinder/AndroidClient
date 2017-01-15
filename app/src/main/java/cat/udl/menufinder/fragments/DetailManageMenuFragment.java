package cat.udl.menufinder.fragments;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import java.util.List;

import cat.udl.menufinder.R;
import cat.udl.menufinder.models.Item;
import cat.udl.menufinder.models.MenuItem;

public class DetailManageMenuFragment extends DetailMenuFragment {
    public static DetailMenuFragment newInstance(long menuId, long itemCategoryId, List<Item> items) {
        DetailManageMenuFragment fragment = new DetailManageMenuFragment();
        fragment.menuId = menuId;
        fragment.itemCategoryId = itemCategoryId;
        fragment.listItems = items;
        return fragment;
    }

    protected void onItemClick(Item item) {
        showDeleteConfirmation(item);
    }

    private void showDeleteConfirmation(final Item item) {
        new AlertDialog.Builder(getActivity())
                .setMessage(getString(R.string.deleteConfirmationMenu, item.getName()))
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                })
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        showToast(item.getName());
                        getDbManager().deleteMenuItem(new MenuItem(menuId, item.getId(), itemCategoryId));
                        listItems.remove(item);
                        update();
                    }
                }).show();
    }
}
