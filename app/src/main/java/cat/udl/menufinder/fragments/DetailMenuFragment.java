package cat.udl.menufinder.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import cat.udl.menufinder.R;
import cat.udl.menufinder.application.MasterFragment;
import cat.udl.menufinder.models.Item;
import cat.udl.menufinder.models.ItemCategory;
import cat.udl.menufinder.models.Menu;

public class DetailMenuFragment extends MasterFragment {
    private LinearLayout container;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail_menu, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        container = (LinearLayout) getView().findViewById(R.id.container);
    }

    public void update(Menu menu) {
        container.removeAllViews();
        for (ItemCategory ic : menu.getItemCategories()) {
            String categoria = ic.getName();
            setCategory(categoria);
            for (Item item : menu.getItemsByCategory(ic)) {
                String itemName = item.getName();
                setItem(itemName);
            }
        }
    }

    private void setCategory(String categoria) {
        TextView textView = new TextView(getContext());
        textView.setTextColor(getResources().getColor(R.color.colorPrimary));
        textView.setText(categoria);
        container.addView(textView);
    }

    private void setItem(String itemName) {
        TextView textView = new TextView(getContext());
        textView.setText(itemName);
        container.addView(textView);
    }
}
