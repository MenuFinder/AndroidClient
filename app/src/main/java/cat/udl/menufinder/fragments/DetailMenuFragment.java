package cat.udl.menufinder.fragments;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import cat.udl.menufinder.R;
import cat.udl.menufinder.activities.ReviewActivity;
import cat.udl.menufinder.application.MasterActivity;
import cat.udl.menufinder.application.MasterFragment;
import cat.udl.menufinder.models.Item;
import cat.udl.menufinder.models.ItemCategory;
import cat.udl.menufinder.models.Menu;
import cat.udl.menufinder.utils.Constants;

import static android.view.Gravity.CENTER;
import static cat.udl.menufinder.utils.Constants.KEY_ITEM;

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
        ((TextView) getView().findViewById(R.id.price)).setText(String.valueOf(menu.getPrice() + "€"));
        ((MasterActivity) getActivity()).getSupportActionBar().setTitle(menu.getName());
        container.removeAllViews();


        for (ItemCategory ic : menu.getItemCategories()) {
            String categoria = ic.getName();
            LinearLayout layout = getNewLayout();
            layout.addView(getCategory(categoria));
            for (final Item item : menu.getItemsByCategory(ic)) {
                String itemName = item.getName();
                View view = getItem(itemName);
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getActivity(), ReviewActivity.class);
                        intent.putExtra(KEY_ITEM, item);
                        startActivity(intent);
                    }
                });
                layout.addView(view);
            }
            container.addView(layout);
        }
    }

    private View getCategory(String categoria) {
        LinearLayout layout = new LinearLayout(getActivity());
        layout.setOrientation(LinearLayout.HORIZONTAL);
        layout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        float weightSum = 5;
        layout.setWeightSum(weightSum);
        int padding2 = getResources().getDimensionPixelSize(R.dimen.space_10);
        layout.setPadding(padding2, padding2, padding2, padding2);
        layout.setGravity(CENTER);


        TextView textView = new TextView(getActivity());
        textView.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, weightSum - 1));
        int padding = getResources().getDimensionPixelSize(R.dimen.space_10);
        textView.setPadding(padding, padding, padding, padding);
        textView.setBackgroundResource(R.color.title_background);
        textView.setGravity(CENTER);
        textView.setTypeface(null, Typeface.BOLD);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.category_title));
        textView.setText(categoria);

        layout.addView(textView);
        return layout;
    }

    private View getItem(String itemName) {
        TextView textView = new TextView(getActivity());
        textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        int padding = getResources().getDimensionPixelSize(R.dimen.space_5);
        textView.setPadding(padding, padding, padding, padding);
        textView.setText(itemName);
        return textView;
    }

    public LinearLayout getNewLayout() {
        LinearLayout layout = new LinearLayout(getActivity());
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        int padding = getResources().getDimensionPixelSize(R.dimen.space_10);
        layout.setPadding(padding, padding, padding, padding);
        layout.setGravity(CENTER);
        return layout;
    }
}
