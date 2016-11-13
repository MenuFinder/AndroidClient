package cat.udl.menufinder.database;

import java.util.List;

import cat.udl.menufinder.models.ItemCategory;
import cat.udl.menufinder.models.Restaurant;

public interface DBManager {

    public void addRestaurant(Restaurant restaurant);
    public List<Restaurant> getRestaurants();
    public List<ItemCategory> getItemCategories();

}
