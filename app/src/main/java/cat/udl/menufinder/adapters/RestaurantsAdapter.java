package cat.udl.menufinder.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import cat.udl.menufinder.R;
import cat.udl.menufinder.application.MasterApplication;
import cat.udl.menufinder.fragments.RestaurantsFragment;
import cat.udl.menufinder.models.Restaurant;

import static android.view.View.GONE;
import static cat.udl.menufinder.enums.UserType.CLIENT;

public class RestaurantsAdapter extends RecyclerView.Adapter<RestaurantsAdapter.ViewHolder> {

    private Context context;
    private List<Restaurant> restaurants;
    private RestaurantsFragment.OnRestaurantClickListener listener;
    private boolean userChecked = true;

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
        Restaurant restaurant = getRestaurant(position);
        holder.name.setText(restaurant.getName());
        holder.address.setText(restaurant.getAddress() + ", " + restaurant.getPostalCode() + " "
                + restaurant.getCity());
        bindFavourites(holder, restaurant);
    }

    private void bindFavourites(ViewHolder holder, Restaurant restaurant) {
        if (MasterApplication.getContext().getUserType() == CLIENT) {
            List<Restaurant> subscribedRestaurantsOfAccount = MasterApplication.getContext().getDbManager().getSubscribedRestaurantsOfAccount(MasterApplication.getContext().getUsername());
            for (Restaurant restaurant1 : subscribedRestaurantsOfAccount) {
                if (restaurant.equals(restaurant1)) {
                    userChecked = false;
                    holder.favourite.setChecked(true);
                    break;
                }
            }
        } else {
            holder.itemView.findViewById(R.id.favourite_button).setVisibility(GONE);
            holder.itemView.findViewById(R.id.review__button).setVisibility(GONE);
        }
    }

    @Override
    public int getItemCount() {
        return restaurants.size();
    }

    public Restaurant getRestaurant(int position) {
        return restaurants.get(position);
    }

    public void setRestaurants(List<Restaurant> restaurantList) {
        restaurants = restaurantList;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        final View itemView;
        TextView name;
        TextView address;
        CheckBox favourite;

        ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            address = (TextView) itemView.findViewById(R.id.address);
            favourite = (CheckBox) itemView.findViewById(R.id.favourite_checkbox);
            this.itemView = itemView;
            ImageButton share_button = (ImageButton) itemView.findViewById(R.id.share_button);
            ImageButton view_map_button = (ImageButton) itemView.findViewById(R.id.view_map_button);
            ImageButton review_button = (ImageButton) itemView.findViewById(R.id.review__button);
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
            review_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onReViewClick(getRestaurant(getAdapterPosition()));
                }
            });

            favourite.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    favourite.setChecked(b);
                    if (userChecked)
                        listener.onFavouriteClick(getRestaurant(getAdapterPosition()), b);
                    userChecked = true;
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
