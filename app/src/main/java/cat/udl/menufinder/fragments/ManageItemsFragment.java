package cat.udl.menufinder.fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cat.udl.menufinder.R;
import cat.udl.menufinder.adapters.ItemsAdapter;
import cat.udl.menufinder.application.MasterFragment;
import cat.udl.menufinder.models.Item;

public class ManageItemsFragment extends MasterFragment {
    private List<Item> items;
    private ItemsAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_manage_items, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        configFAB();
        configList();
    }

    private void configFAB() {
        FloatingActionButton fab = (FloatingActionButton) getView().findViewById(R.id.add_item_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddDialog();
            }
        });
    }

    private void configList() {
        RecyclerView recyclerView = (RecyclerView) getView().findViewById(R.id.list);
        RecyclerView.ItemDecoration itemDecoration = new
                DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);

        DefaultItemAnimator animator = new DefaultItemAnimator();
        animator.setAddDuration(1000);
        recyclerView.setItemAnimator(animator);

        items = new ArrayList<>();
        Item testItem = new Item("Test", "", 25);
        testItem.setScore(4.2);
        items.add(testItem);
        adapter = new ItemsAdapter(getActivity(), items, new OnItemClick() {
            @Override
            public void onItem(Item item, int position) {
                showEditDialog(item, position);
            }
        });
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void showDeleteConfirmation(final int position) {
        Item item = adapter.getItem(position);
        new AlertDialog.Builder(getActivity())
                .setMessage(String.format(getString(R.string.deleteConfirmation), item.getName()))
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                })
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        removeOfDB(position);
                    }
                }).show();
    }

    private void showEditDialog(Item item, final int position) {
        LayoutInflater inflater = getActivity().getLayoutInflater();

        final View dialogView = inflater.inflate(R.layout.view_manage_item, null);
        ((TextView) dialogView.findViewById(R.id.name)).setText(item.getName());
        ((TextView) dialogView.findViewById(R.id.price)).setText(String.valueOf(item.getPrice()));
        ((TextView) dialogView.findViewById(R.id.description)).setText(item.getDescription());

        final AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                .setTitle(R.string.manage_item)
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
                .setNeutralButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        showDeleteConfirmation(position);
                    }
                })
                .create();

        alertDialog.show();
        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Item item1 = items.get(position);
                boolean closeDialog = true;
                String name = ((EditText) dialogView.findViewById(R.id.name)).getText().toString();
                if (TextUtils.isEmpty(name)) closeDialog = false;
                else item1.setName(name);


                String price = ((EditText) dialogView.findViewById(R.id.price)).getText().toString();
                if (TextUtils.isEmpty(price)) closeDialog = false;
                else item1.setPrice(Double.valueOf(price));

                String description = ((EditText) dialogView.findViewById(R.id.description)).getText().toString();
                item1.setDescription(description);

                if (closeDialog) {
                    alertDialog.dismiss();
                    editToDB(item1);
                }
            }
        });
    }

    private void editToDB(Item item) {
        adapter.notifyDataSetChanged();
    }

    private void showAddDialog() {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.view_manage_item, null);
        final AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                .setTitle(R.string.manage_item)
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
                boolean closeDialog = true;
                String name = ((EditText) dialogView.findViewById(R.id.name)).getText().toString();
                if (TextUtils.isEmpty(name)) {
                    closeDialog = false;
                }

                String price = ((EditText) dialogView.findViewById(R.id.price)).getText().toString();
                if (TextUtils.isEmpty(price)) {
                    closeDialog = false;
                }

                String description = ((EditText) dialogView.findViewById(R.id.description)).getText().toString();


                if (closeDialog) {
                    alertDialog.dismiss();
                    saveToDB(new Item(name, description, Double.valueOf(price)));
                }
            }
        });
    }

    private void saveToDB(Item item) {
        showToast(R.string.item_saved);
        adapter.addItem(item);
    }

    private void removeOfDB(int position) {
        showToast(R.string.item_saved);
        adapter.removeItem(position);
    }

    public interface OnItemClick {
        void onItem(Item item, int adapterPosition);
    }
}


