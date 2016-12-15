package cat.udl.menufinder.database;

import java.util.List;
import java.util.Map;

import cat.udl.menufinder.models.Account;
import cat.udl.menufinder.models.Item;
import cat.udl.menufinder.models.ItemCategory;
import cat.udl.menufinder.models.Menu;
import cat.udl.menufinder.models.MenuItem;
import cat.udl.menufinder.models.Restaurant;
import cat.udl.menufinder.models.Review;

public interface DBManager {

    Account getValidLogin(String id, String password);

    List<Menu> getMenusByRestaurantId(long restaurantId);

    Menu getMenuById(long menuId);

    String addNewMenu(Menu menu);

    List<Menu> getMenus();

    String deleteMenu(long menuId);

    String updateMenu(Menu menu);

    Restaurant getRestaurantById(long restaurantId);

    String addNewRestaurant(Restaurant restaurant);

    List<Restaurant> getRestaurants();

    String deleteRestaurant(long restaurantId);

    String updateRestaurant(Restaurant restaurant);

    Review getReviewById(long reviewId);

    List<Review> getReviewsOfItem(long itemId);

    List<Review> getReviewsOfMenu(long menuId);

    List<Review> getReviewsOfRestaurant(long restaurantId);

    String updateReview(Review review);

    String deleteReview(long reviewId);

    String addReview(Review review);

    String updateItem(Item item);

    String deleteItem(long itemId);

    String addItem(Item item);

    Item getItemById(long itemId);

    List<Item> getRestaurantItems(long restaurantId);

    Map<Long, List<Item>> getMenuItemsByCategory(long menuId);

    String deleteMenuItem(MenuItem menuItem);

    String addMenuItem(MenuItem menuItem);

    ItemCategory getItemCategoryById(long id);

    Boolean updateItemCategory(ItemCategory itemCategory);

    Boolean deleteItemCategory(long id);

    Boolean addItemCategory(ItemCategory itemCategory);

    List<ItemCategory> getItemCategories();

//    String updateItemRating(ItemRating itemRating);
//
//    String deleteItemRating(ItemRating itemRating);
//
//    String addItemRating(ItemRating itemRating);
//
//    List<ItemRating> getRatingsOfItem(long itemId);
//
//    double getItemRatingOfItem(long itemId);
//
//    String deleteAccountSubscription(AccountSubscription accountSubscription);
//
//    String addAccountSubscription(AccountSubscription accountSubscription);

}
