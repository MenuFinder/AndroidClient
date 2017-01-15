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
    private static final String ARG_SECTION_NUMBER = "section_number";
    private List<Item> lstitems = null;

    public DetailMenuFragment() {
    }

    public static DetailMenuFragment newInstance(long sectionNumber, List<Item> item) {
        DetailMenuFragment fragment = new DetailMenuFragment();
        Bundle args = new Bundle();
        args.putLong(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setListItem(item);
        fragment.setArguments(args);
        return fragment;
    }

    public void setListItem(List<Item> items) {
        this.lstitems = items;

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail_menu, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        update(lstitems);
    }

    public MasterApplication getMasterApplication() {
        return ((MasterActivity) getActivity()).getMasterApplication();
    }

    public void update(List<Item> items) {

        long menu_Id = getArguments().getLong(ARG_SECTION_NUMBER);
        Menu menu = getDbManager().getMenuById(menu_Id);
        ((MasterActivity) getActivity()).getSupportActionBar().setTitle(menu.getName() + " ( " + String.valueOf(menu.getPrice() + "€") + " )");
        RecyclerView recyclerView = (RecyclerView) getView().findViewById(R.id.list);

        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(
                getActivity(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);

        ItemsAdapter adapter = new ItemsAdapter(getActivity(), items, new ManageItemsFragment.OnItemClick() {
            @Override
            public void onItem(Item item, int adapterPosition) {
                Intent intent = new Intent(getActivity(), ReviewActivity.class);
                intent.putExtra(KEY_ITEM, item);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

}
