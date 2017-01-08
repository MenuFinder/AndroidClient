package cat.udl.menufinder.database;

import java.util.List;
import java.util.Map;

import cat.udl.menufinder.models.Account;
import cat.udl.menufinder.models.AccountSubscription;
import cat.udl.menufinder.models.Item;
import cat.udl.menufinder.models.ItemCategory;
import cat.udl.menufinder.models.ItemRating;
import cat.udl.menufinder.models.Menu;
import cat.udl.menufinder.models.MenuItem;
import cat.udl.menufinder.models.Restaurant;
import cat.udl.menufinder.models.Review;

public interface DBManager {

    Account getValidLogin(String id, String password);

    boolean addAccount(Account account);

    List<Menu> getMenusByRestaurantId(long restaurantId);

    Menu getMenuById(long menuId);

    boolean addMenu(Menu menu);

    List<Menu> getMenus();

    boolean deleteMenu(long menuId);

    boolean updateMenu(Menu menu);

    List<Restaurant> getRestaurantsOfAccount(String accountId);

    Restaurant getRestaurantById(long restaurantId);

    boolean addRestaurant(Restaurant restaurant);

    List<Restaurant> getRestaurants();

    boolean deleteRestaurant(long restaurantId);

    boolean updateRestaurant(Restaurant restaurant);

    Review getReviewById(long reviewId);

    List<Review> getReviewsOfItem(long itemId);

    List<Review> getReviewsOfMenu(long menuId);

    List<Review> getReviewsOfRestaurant(long restaurantId);

    boolean updateReview(Review review);

    boolean deleteReview(long reviewId);

    boolean addReview(Review review);

    boolean updateItem(Item item);

    boolean deleteItem(long itemId);

    boolean addItem(Item item);

    Item getItemById(long itemId);

    List<Item> getRestaurantItems(long restaurantId);

    Map<Long, List<Item>> getMenuItemsByCategory(long menuId);

    boolean deleteMenuItem(MenuItem menuItem);

    boolean addMenuItem(MenuItem menuItem);

    ItemCategory getItemCategoryById(long itemCategoryId);

    boolean updateItemCategory(ItemCategory itemCategory);

    boolean deleteItemCategory(long itemCategoryId);

    boolean addItemCategory(ItemCategory itemCategory);

    List<ItemCategory> getItemCategories();

    boolean updateItemRating(ItemRating itemRating);

    boolean deleteItemRating(ItemRating itemRating);

    boolean addItemRating(ItemRating itemRating);

    List<ItemRating> getRatingsOfItem(long itemId);

    double getItemRatingOfItem(long itemId);

    boolean deleteAccountSubscription(AccountSubscription accountSubscription);

    boolean addAccountSubscription(AccountSubscription accountSubscription);

    List<Restaurant> getSubscribedRestaurantsOfAccount(String accountId);

    void deleteAll();

    List<Menu> getAllMenusByRestaurantId(long restaurantId);

    boolean updateAccountToken(Account account);

    List<Restaurant> getFilteredRestaurants(String where);
}
