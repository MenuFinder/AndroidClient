package cat.udl.menufinder.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RatingBar;
import android.widget.Switch;
import android.widget.TextView;

import java.util.List;

import cat.udl.menufinder.R;
import cat.udl.menufinder.enums.UserType;
import cat.udl.menufinder.fragments.ManageMenusFragment;
import cat.udl.menufinder.models.Menu;

import static android.view.View.GONE;
import static cat.udl.menufinder.enums.UserType.RESTAURANT;

public class MenusAdapter extends RecyclerView.Adapter<MenusAdapter.ViewHolder> {

    private final UserType userType;
    private Context context;
    private List<Menu> menus;
    private ManageMenusFragment.OnMenuClickListener listener;

    public MenusAdapter(Context context, List<Menu> menus, ManageMenusFragment.OnMenuClickListener listener, UserType userType) {
        this.context = context;
        this.menus = menus;
        this.listener = listener;
        this.userType = userType;
    }

    @Override
    public MenusAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View menuView = inflater.inflate(R.layout.item_menu, parent, false);
        return new ViewHolder(menuView);
    }

    @Override
    public void onBindViewHolder(MenusAdapter.ViewHolder holder, final int position) {
        final Menu menu = getMenu(position);
        holder.name.setText(menu.getName());
        holder.price.setText(String.valueOf(menu.getPrice()));
        holder.rating.setRating(((float) menu.getScore()));
        if (userType != RESTAURANT) holder.is_visible.setVisibility(GONE);
        holder.is_visible.setChecked(menu.isVisible());
    }

    @Override
    public int getItemCount() {
        return menus.size();
    }

    @Override
    public long getItemId(int position) {
        return getMenu(position).getId();
    }

    public Menu getMenu(int position) {
        return menus.get(position);
    }

    public void addMenu(Menu menu) {
        menus.add(menu);
        notifyDataSetChanged();
    }

    public void removeMenu(int position) {
        menus.remove(position);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView price;
        RatingBar rating;
        Switch is_visible;

        ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    listener.onMenuLongClick(getMenu(getAdapterPosition()), getAdapterPosition());
                    return true;
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onMenuClick(getMenu(getAdapterPosition()), getAdapterPosition());
                }
            });
            name = (TextView) itemView.findViewById(R.id.name);
            price = (TextView) itemView.findViewById(R.id.price);
            rating = (RatingBar) itemView.findViewById(R.id.rating);
            is_visible = (Switch) itemView.findViewById(R.id.is_visible);
            is_visible.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    is_visible.setChecked(b);
                    listener.onIsVisibleClick(getMenu(getAdapterPosition()), b);
                }
            });
        }
    }

}
