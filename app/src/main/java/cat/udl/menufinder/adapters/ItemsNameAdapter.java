package cat.udl.menufinder.adapters;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import cat.udl.menufinder.models.Item;

public class ItemsNameAdapter extends ArrayAdapter {
    private final LayoutInflater inflater;
    private final int layout;
    private final List<Item> items;

    public ItemsNameAdapter(Context context, int layout, List<Item> items) {
        super(context, layout, items);
        inflater = LayoutInflater.from(context);
        this.layout = layout;
        this.items = items;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        Item item = getItem(position);
        ViewHolder holder;
        if (convertView == null) {
            v = inflater.inflate(layout, parent, false);
            holder = new ViewHolder();
            holder.name = (TextView) v.findViewById(android.R.id.text1);
            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }
        holder.name.setText(item.getName());

        return v;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getView(position, convertView, parent);
    }

    @Nullable
    @Override
    public Item getItem(int position) {
        return items.get(position);
    }

    private class ViewHolder {
        TextView name;
    }
}
