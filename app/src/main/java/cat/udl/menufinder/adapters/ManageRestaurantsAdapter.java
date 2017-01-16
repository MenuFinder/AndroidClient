package cat.udl.menufinder.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cat.udl.menufinder.R;
import cat.udl.menufinder.models.Restaurant;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class ManageRestaurantsAdapter extends ArrayAdapter {
    private final int layout;
    private final List<Restaurant> restaurants;
    private final LayoutInflater inflater;
    public int selected;

    public ManageRestaurantsAdapter(Context context, int layout, List<Restaurant> restaurants) {
        super(context, layout, restaurants);
        this.layout = layout;
        this.restaurants = restaurants;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Nullable
    @Override
    public Restaurant getItem(int position) {
        return restaurants.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        Restaurant restaurant = getItem(position);
        ViewHolder holder;
        if (convertView == null) {
            v = inflater.inflate(layout, parent, false);
            holder = new ViewHolder();
            holder.selected = (ImageView) v.findViewById(R.id.selected);
            holder.name = (TextView) v.findViewById(R.id.name);
            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }
        int visible = (selected == position) ? VISIBLE : GONE;
        holder.selected.setVisibility(visible);
        holder.name.setText(restaurant.getName());

        return v;
    }

    @Override
    public int getCount() {
        return restaurants.size();
    }

    private class ViewHolder {
        ImageView selected;
        TextView name;
    }
}
