package cat.udl.menufinder.fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cat.udl.menufinder.R;
import cat.udl.menufinder.application.MasterFragment;
import cat.udl.menufinder.models.Restaurant;

public class ManageRestaurantsFragment extends MasterFragment {

    List<String> values;
    private List<Restaurant> restaurants;
    private ArrayAdapter<String> adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_manage_restaurants, container, false);
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
        ListView listView = (ListView) getView().findViewById(R.id.list);

        // TODO Posar la llista de restaurant
        restaurants = getDbManager().getRestaurantsOfAccount(getMasterApplication().getUsername());

        values = new ArrayList<>();
        for (Restaurant restaurant : restaurants) {
            values.add(restaurant.getName());
        }
        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, values);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                showToast(adapter.getItem(i));
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                showEditDialog(restaurants.get(i), i);
                return true;
            }
        });

    }

    private void showAddDialog() {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.register_restaurant, null);
        final AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                .setTitle(R.string.register_restaurant)
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
                String cif = ((EditText) dialogView.findViewById(R.id.cif)).getText().toString().trim();
                if (TextUtils.isEmpty(cif)) closeDialog = false;
                String address = ((EditText) dialogView.findViewById(R.id.address)).getText().toString().trim();
                if (TextUtils.isEmpty(address)) closeDialog = false;
                String city = ((EditText) dialogView.findViewById(R.id.city)).getText().toString().trim();
                if (TextUtils.isEmpty(city)) closeDialog = false;
                String postalCode = ((EditText) dialogView.findViewById(R.id.postal_code)).getText().toString().trim();
                if (TextUtils.isEmpty(postalCode)) closeDialog = false;
                String state = ((EditText) dialogView.findViewById(R.id.state)).getText().toString().trim();
                if (TextUtils.isEmpty(state)) closeDialog = false;
                String country = ((EditText) dialogView.findViewById(R.id.country)).getText().toString().trim();
                if (TextUtils.isEmpty(country)) closeDialog = false;
                String email = (dialogView.findViewById(R.id.email)).toString().trim();
                if (TextUtils.isEmpty(email)) closeDialog = false;
                String phone = ((EditText) dialogView.findViewById(R.id.phone)).getText().toString().trim();
                if (TextUtils.isEmpty(phone)) closeDialog = false;
                String account = getMasterApplication().getUsername().trim();
                if (closeDialog) {
                    alertDialog.dismiss();
                    //TODO Posar la id del restaurant
                    saveToDB(new Restaurant(name, cif, address, city, postalCode, state, country, email, phone, account));
                    values.add(name);
                }
            }
        });
    }

    private void showEditDialog(final Restaurant restaurant, final int position) {
        LayoutInflater inflater = getActivity().getLayoutInflater();

        final View dialogView = inflater.inflate(R.layout.register_restaurant, null);
        ((EditText) dialogView.findViewById(R.id.restaurant_name)).setText(restaurant.getName());
        ((EditText) dialogView.findViewById(R.id.cif)).setText(restaurant.getCif());
        ((EditText) dialogView.findViewById(R.id.address)).setText(restaurant.getAddress());
        ((EditText) dialogView.findViewById(R.id.city)).setText(restaurant.getCity());
        ((EditText) dialogView.findViewById(R.id.postal_code)).setText(restaurant.getPostalCode());
        ((EditText) dialogView.findViewById(R.id.state)).setText(restaurant.getState());
        ((EditText) dialogView.findViewById(R.id.country)).setText(restaurant.getCountry());
        ((TextView) dialogView.findViewById(R.id.restaurant_email)).setText(restaurant.getEmail());
        ((EditText) dialogView.findViewById(R.id.phone)).setText(restaurant.getPhone());

        final AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                .setTitle(R.string.manage_restaurant)
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
                boolean closeDialog = true;

                EditText viewById = (EditText) dialogView.findViewById(R.id.restaurant_name);
                String name = viewById.getText().toString().trim();
                if (TextUtils.isEmpty(name)) closeDialog = false;
                String cif = ((EditText) dialogView.findViewById(R.id.cif)).getText().toString().trim();
                if (TextUtils.isEmpty(cif)) closeDialog = false;
                String address = ((EditText) dialogView.findViewById(R.id.address)).getText().toString().trim();
                if (TextUtils.isEmpty(address)) closeDialog = false;
                String city = ((EditText) dialogView.findViewById(R.id.city)).getText().toString().trim();
                if (TextUtils.isEmpty(city)) closeDialog = false;
                String postalCode = ((EditText) dialogView.findViewById(R.id.postal_code)).getText().toString().trim();
                if (TextUtils.isEmpty(postalCode)) closeDialog = false;
                String state = ((EditText) dialogView.findViewById(R.id.state)).getText().toString().trim();
                if (TextUtils.isEmpty(state)) closeDialog = false;
                String country = ((EditText) dialogView.findViewById(R.id.country)).getText().toString().trim();
                if (TextUtils.isEmpty(country)) closeDialog = false;
                String email = ((EditText) dialogView.findViewById(R.id.restaurant_email)).getText().toString().trim();
                if (TextUtils.isEmpty(email)) closeDialog = false;
                String phone = ((EditText) dialogView.findViewById(R.id.phone)).getText().toString().trim();
                if (TextUtils.isEmpty(phone)) closeDialog = false;
                String account = getMasterApplication().getUsername().trim();
                if (closeDialog) {
                    alertDialog.dismiss();
                    Restaurant r = new Restaurant(name, cif, address, city, postalCode, state, country, email, phone, account);
                    r.setId(restaurant.getId());
                    editToDB(r, true);
                    values.remove(position);
                    values.add(name);
                    restaurants.remove(position);
                    restaurants.add(r);
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }

    private void showDeleteConfirmation(final int position) {
        String restaurant = adapter.getItem(position);
        new AlertDialog.Builder(getActivity())
                .setMessage(String.format(getString(R.string.deleteConfirmation), restaurant))
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
                        values.remove(position);
                        restaurants.remove(position);
                        adapter.notifyDataSetChanged();
                    }
                }).show();
    }

    private void saveToDB(Restaurant restaurant) {
        showToast(String.format(getString(R.string.saveNotification), restaurant.getName()));
        adapter.notifyDataSetChanged();
        getDbManager().addRestaurant(restaurant);
    }

    private void editToDB(Restaurant restaurant, boolean need) {
        if (need) {
            showToast(String.format(getString(R.string.updateNotification), restaurant.getName()));
        }
        Log.d("restaurant", restaurant.toString());
        getDbManager().updateRestaurant(restaurant);
    }

    private void removeOfDB(int position) {
        showToast(R.string.restaurant_deleted);
        Restaurant restaurant = restaurants.get(position);
        getDbManager().deleteItem(restaurant.getId());
    }

}
