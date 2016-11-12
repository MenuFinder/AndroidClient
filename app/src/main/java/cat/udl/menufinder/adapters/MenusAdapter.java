package cat.udl.menufinder.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import java.util.List;

import cat.udl.menufinder.R;
import cat.udl.menufinder.fragments.ManageMenusFragment;
import cat.udl.menufinder.models.Menu;

public class MenusAdapter extends RecyclerView.Adapter<MenusAdapter.ViewHolder> {

    private Context context;
    private List<Menu> menus;
    private ManageMenusFragment.OnMenuClickListener listener;

    public MenusAdapter(Context context, List<Menu> menus, ManageMenusFragment.OnMenuClickListener listener) {
        this.context = context;
        this.menus = menus;
        this.listener = listener;
    }

    @Override
    public MenusAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View menuView = inflater.inflate(R.layout.item_menu, parent, false);
        return new ViewHolder(menuView);
    }

    @Override
    public void onBindViewHolder(MenusAdapter.ViewHolder holder, final int position) {
        Menu menu = getMenu(position);
        holder.name.setText(menu.getName());
        holder.price.setText(String.valueOf(menu.getPrice()));
        holder.is_visible.setChecked(menu.isVisible());
        holder.is_visible.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                getMenu(position).setVisible(b);
            }
        });
    }

    @Override
    public int getItemCount() {
        return menus.size();
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
        Switch is_visible;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onMenuClick(getMenu(getAdapterPosition()), getAdapterPosition());
                }
            });
            name = (TextView) itemView.findViewById(R.id.name);
            price = (TextView) itemView.findViewById(R.id.price);
            is_visible = (Switch) itemView.findViewById(R.id.is_visible);
        }
    }

}
