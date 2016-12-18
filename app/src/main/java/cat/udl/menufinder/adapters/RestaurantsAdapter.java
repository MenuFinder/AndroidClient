package cat.udl.menufinder.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import cat.udl.menufinder.R;
import cat.udl.menufinder.fragments.RestaurantsFragment;
import cat.udl.menufinder.models.Restaurant;

public class RestaurantsAdapter extends RecyclerView.Adapter<RestaurantsAdapter.ViewHolder> {

    private Context context;
    private List<Restaurant> restaurants;
    private RestaurantsFragment.OnRestaurantClickListener listener;

    public RestaurantsAdapter(Context context, List<Restaurant> restaurants, RestaurantsFragment.OnRestaurantClickListener listener) {
        this.context = context;
        this.restaurants = restaurants;
        this.listener = listener;
    }

    @Override
    public RestaurantsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View menuView = inflater.inflate(R.layout.item_restaurant, parent, false);
        return new ViewHolder(menuView);
    }

    @Override
    public void onBindViewHolder(RestaurantsAdapter.ViewHolder holder, final int position) {
        Restaurant restaurants = getRestaurant(position);
        holder.name.setText(restaurants.getName());
        holder.address.setText(restaurants.getAddress() + ", " + restaurants.getPostalCode() + " "
                + restaurants.getCity());
    }

    @Override
    public int getItemCount() {
        return restaurants.size();
    }

    public Restaurant getRestaurant(int position) {
        return restaurants.get(position);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView address;

        ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            address = (TextView) itemView.findViewById(R.id.address);
            ImageButton share_button = (ImageButton) itemView.findViewById(R.id.share_button);
            ImageButton view_map_button = (ImageButton) itemView.findViewById(R.id.view_map_button);
            View favourite_button = itemView.findViewById(R.id.favourite_button);
            final CheckBox favourite_checkbox = (CheckBox) itemView.findViewById(R.id.favourite_checkbox);
            CardView cardView = (CardView) itemView.findViewById(R.id.card_view);
            share_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onShareClick(getRestaurant(getAdapterPosition()));
                }
            });
            view_map_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onViewMapClick(getRestaurant(getAdapterPosition()));
                }
            });
            favourite_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    favourite_checkbox.setChecked(!favourite_checkbox.isChecked());
                    listener.onFavouriteClick(getRestaurant(getAdapterPosition()));
                }
            });
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onRestaurantClick(getRestaurant(getAdapterPosition()), view);
                }
            });
        }
    }

}
