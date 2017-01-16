package cat.udl.menufinder.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import cat.udl.menufinder.R;
import cat.udl.menufinder.activities.ReviewActivity;
import cat.udl.menufinder.adapters.ItemsAdapter;
import cat.udl.menufinder.application.MasterActivity;
import cat.udl.menufinder.application.MasterApplication;
import cat.udl.menufinder.application.MasterFragment;
import cat.udl.menufinder.models.Item;
import cat.udl.menufinder.models.Menu;

import static cat.udl.menufinder.utils.Constants.KEY_ITEM;

public class DetailMenuFragment extends MasterFragment {
    public List<Item> listItems = new ArrayList<>();
    protected long menuId;
    protected long itemCategoryId;

    public DetailMenuFragment() {
    }

    public static DetailMenuFragment newInstance(long menuId, long itemCategoryId, List<Item> items) {
        DetailMenuFragment fragment = new DetailMenuFragment();
        fragment.menuId = menuId;
        fragment.itemCategoryId = itemCategoryId;
        fragment.listItems = items;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail_menu, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Menu menu = getDbManager().getMenuById(menuId);
        ((MasterActivity) getActivity()).getSupportActionBar().setTitle(getString(R.string.detail_menu_title, menu.getName(), String.valueOf(menu.getPrice())));
        update();
    }

    public MasterApplication getMasterApplication() {
        return ((MasterActivity) getActivity()).getMasterApplication();
    }

    public void update() {
        update(listItems);
    }

    public void update(List<Item> items) {
        if (getView() != null) {
            RecyclerView recyclerView = (RecyclerView) getView().findViewById(R.id.list);

            RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(
                    getActivity(), DividerItemDecoration.VERTICAL);
            recyclerView.addItemDecoration(itemDecoration);

            ItemsAdapter adapter = new ItemsAdapter(getActivity(), items, new ManageItemsFragment.OnItemClick() {
                @Override
                public void onItem(Item item, int adapterPosition) {
                    onItemClick(item);
                }
            });
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        }
    }

    protected void onItemClick(Item item) {
        Intent intent = new Intent(getActivity(), ReviewActivity.class);
        intent.putExtra(KEY_ITEM, item);
        startActivity(intent);
    }
}
