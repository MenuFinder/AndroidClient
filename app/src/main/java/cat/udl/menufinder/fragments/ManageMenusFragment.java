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
import cat.udl.menufinder.adapters.MenusAdapter;
import cat.udl.menufinder.application.MasterFragment;
import cat.udl.menufinder.models.Menu;

public class ManageMenusFragment extends MasterFragment {

    private List<Menu> menus;
    private MenusAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
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
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(
                getActivity(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);

        DefaultItemAnimator animator = new DefaultItemAnimator();
        animator.setAddDuration(1000);
        recyclerView.setItemAnimator(animator);

        menus = new ArrayList<Menu>();
        Menu testMenu = new Menu("Mesillas", "The best menu of Lleida city", 172.16, true);
        menus.add(testMenu);
        adapter = new MenusAdapter(getActivity(), menus, new OnMenuClickListener() {
            @Override
            public void onMenuClick(Menu menu, int adapterPosition) {
                showEditDialog(menu, adapterPosition);
            }
        }, getMasterApplication().getUserType());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void showDeleteConfirmation(final int position) {
        Menu menu = adapter.getMenu(position);
        new AlertDialog.Builder(getActivity())
                .setMessage(String.format(getString(R.string.deleteConfirmation), menu.getName()))
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Nothing to do
                    }
                })
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        removeOfDB(position);
                    }
                }).show();
    }

    private void showEditDialog(Menu menu, final int position) {
        LayoutInflater inflater = getActivity().getLayoutInflater();

        final View dialogView = inflater.inflate(R.layout.view_manage_item, null);
        ((TextView) dialogView.findViewById(R.id.name)).setText(menu.getName());
        ((TextView) dialogView.findViewById(R.id.price)).setText(String.valueOf(menu.getPrice()));
        ((TextView) dialogView.findViewById(R.id.description)).setText(menu.getDescription());

        final AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                .setTitle(R.string.manage_menu)
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
                .setNeutralButton(R.string.delete, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        showDeleteConfirmation(position);
                    }
                }).create();

        alertDialog.show();
        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Menu m = menus.get(position);
                boolean closeDialog = true;
                String name = ((EditText) dialogView.findViewById(R.id.name)).getText().toString().trim();
                if (TextUtils.isEmpty(name)) closeDialog = false;
                else m.setName(name);
                String price = ((EditText) dialogView.findViewById(R.id.price)).getText().toString().trim();
                if (TextUtils.isEmpty(price)) closeDialog = false;
                else m.setPrice(Double.valueOf(price));
                String description = ((EditText) dialogView.findViewById(R.id.description)).getText()
                        .toString().trim();
                m.setDescription(description);
                if (closeDialog) {
                    alertDialog.dismiss();
                    editToDB(m);
                }
            }
        });
    }

    private void showAddDialog() {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.view_manage_item, null);
        final AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                .setTitle(R.string.manage_menu)
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
                }).create();

        alertDialog.show();
        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean closeDialog = true;
                String name = ((EditText) dialogView.findViewById(R.id.name)).getText().toString().trim();
                if (TextUtils.isEmpty(name)) closeDialog = false;
                String price = ((EditText) dialogView.findViewById(R.id.price)).getText().toString().trim();
                if (TextUtils.isEmpty(price)) closeDialog = false;
                String description = ((EditText) dialogView.findViewById(R.id.description)).getText()
                        .toString().trim();
                if (closeDialog) {
                    alertDialog.dismiss();
                    saveToDB(new Menu(name, description, Double.parseDouble(price), true));
                }
            }
        });
    }

    private void editToDB(Menu menu) {
        showToast(String.format(getString(R.string.saveNotification), menu.getName()));
        adapter.notifyDataSetChanged();
    }

    private void saveToDB(Menu menu) {
        showToast(String.format(getString(R.string.saveNotification), menu.getName()));
        adapter.addMenu(menu);
    }

    private void removeOfDB(int position) {
        showToast(String.format(getString(R.string.deleteNotification), adapter.getMenu(position).getName()));
        adapter.removeMenu(position);
    }

    public interface OnMenuClickListener {
        void onMenuClick(Menu menu, int adapterPosition);
    }

}
