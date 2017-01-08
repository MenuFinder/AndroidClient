package cat.udl.menufinder.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

import cat.udl.menufinder.R;
import cat.udl.menufinder.fragments.ManageItemsFragment;
import cat.udl.menufinder.models.Item;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder> {

    private Context context;
    private List<Item> items;
    private ManageItemsFragment.OnItemClick listener;

    public ItemsAdapter(Context context, List<Item> items, ManageItemsFragment.OnItemClick listener) {
        this.context = context;
        this.items = items;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);

        View itemView = inflater.inflate(R.layout.item_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        Item item = getItem(position);

        viewHolder.name.setText(item.getName());
        viewHolder.price.setText(String.valueOf(item.getPrice()));
        viewHolder.score.setRating(((float) item.getScore()));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public Item getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getId();
    }

    public void addItem(Item item) {
        items.add(item);
        notifyDataSetChanged();
    }

    public void removeItem(int position) {
        items.remove(position);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView price;
        RatingBar score;

        ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItem(getItem(getAdapterPosition()), getAdapterPosition());
                }
            });
            name = (TextView) itemView.findViewById(R.id.name);
            price = (TextView) itemView.findViewById(R.id.price);
            score = ((RatingBar) itemView.findViewById(R.id.rating));

        }
    }
}
