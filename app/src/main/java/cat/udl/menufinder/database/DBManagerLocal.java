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

public class DBManagerLocal implements DBManager {

    private static DBManagerLocal ourInstance = new DBManagerLocal();

    private ItemCategoryDataSource itemCategoryDataSource;
    private ItemDataSource itemDataSource;
    private MenuDataSource menuDataSource;
    private MenuItemDataSource menuItemDataSource;
    private RestaurantDataSource restaurantDataSource;
    private ReviewDataSource reviewDataSource;
    private SubscriptionDataSource subscriptionDataSource;

    private DBManagerLocal() {
        itemCategoryDataSource = new ItemCategoryDataSource();
        itemDataSource = new ItemDataSource();
        menuDataSource = new MenuDataSource();
        menuItemDataSource = new MenuItemDataSource();
        restaurantDataSource = new RestaurantDataSource();
        reviewDataSource = new ReviewDataSource();
        subscriptionDataSource = new SubscriptionDataSource();
    }

    public static DBManagerLocal getInstance() {
        return ourInstance;
    }

    @Override
    public Account getValidLogin(String id, String password) {
        return null;
    }

    @Override
    public boolean addAccount(Account account) {
        return false;
    }

    @Override
    public List<Menu> getMenusByRestaurantId(long restaurantId) {
        return menuDataSource.getMenusByRestaurantId(restaurantId);
    }

    @Override
    public List<Menu> getAllMenusByRestaurantId(long restaurantId) {
        return menuDataSource.getAllMenusByRestaurantId(restaurantId);
    }

    @Override
    public boolean updateAccountToken(Account account) {
        return false;
    }

    @Override
    public List<Restaurant> getFilteredRestaurants(String where) {
        return restaurantDataSource.getFilteredRestaurants(where);
    }

    @Override
    public List<String> getAllDifferentCities() {
        return restaurantDataSource.getAllDifferentCities();
    }

    @Override
    public List<String> getAllRestaurantNames() {
        return restaurantDataSource.getAllRestaurantNames();
    }

    @Override
    public Menu getMenuById(long menuId) {
        return menuDataSource.getMenuById(menuId);
    }

    @Override
    public boolean addMenu(Menu menu) {
        return menuDataSource.addMenu(menu);
    }

    @Override
    public List<Menu> getMenus() {
        return menuDataSource.getMenus();
    }

    @Override
    public boolean deleteMenu(long menuId) {
        return menuDataSource.deleteMenu(menuId);
    }

    @Override
    public boolean updateMenu(Menu menu) {
        return menuDataSource.updateMenu(menu);
    }

    @Override
    public List<Restaurant> getRestaurantsOfAccount(String accountId) {
        return restaurantDataSource.getRestaurantsOfAccount(accountId);
    }

    @Override
    public Restaurant getRestaurantById(long restaurantId) {
        return restaurantDataSource.getRestaurantById(restaurantId);
    }

    @Override
    public boolean addRestaurant(Restaurant restaurant) {
        return restaurantDataSource.addRestaurant(restaurant);
    }

    @Override
    public List<Restaurant> getRestaurants() {
        return restaurantDataSource.getRestaurants();
    }

    @Override
    public boolean deleteRestaurant(long restaurantId) {
        return restaurantDataSource.deleteRestaurant(restaurantId);
    }

    @Override
    public boolean updateRestaurant(Restaurant restaurant) {
        return restaurantDataSource.updateRestaurant(restaurant);
    }

    @Override
    public Review getReviewById(long reviewId) {
        return reviewDataSource.getReviewById(reviewId);
    }

    @Override
    public List<Review> getReviewsOfItem(long itemId) {
        return reviewDataSource.getReviewsOfItem(itemId);
    }

    @Override
    public List<Review> getReviewsOfMenu(long menuId) {
        return reviewDataSource.getReviewsOfMenu(menuId);
    }

    @Override
    public List<Review> getReviewsOfRestaurant(long restaurantId) {
        return reviewDataSource.getReviewsOfRestaurant(restaurantId);
    }

    @Override
    public boolean updateReview(Review review) {
        return reviewDataSource.updateReview(review);
    }

    @Override
    public boolean deleteReview(long reviewId) {
        return reviewDataSource.deleteReview(reviewId);
    }

    @Override
    public boolean addReview(Review review) {
        return reviewDataSource.addReview(review);
    }

    @Override
    public boolean updateItem(Item item) {
        return itemDataSource.updateItem(item);
    }

    @Override
    public boolean deleteItem(long itemId) {
        return itemDataSource.deleteItem(itemId);
    }

    @Override
    public boolean addItem(Item item) {
        return itemDataSource.addItem(item);
    }

    @Override
    public Item getItemById(long itemId) {
        return itemDataSource.getItemById(itemId);
    }

    @Override
    public List<Item> getRestaurantItems(long restaurantId) {
        return itemDataSource.getItemsByRestaurantId(restaurantId);
    }

    @Override
    public Map<Long, List<Item>> getMenuItemsByCategory(long menuId) {
        return menuItemDataSource.getMenuItemsByCategory(menuId);
    }

    @Override
    public boolean deleteMenuItem(MenuItem menuItem) {
        return menuItemDataSource.deleteMenuItem(menuItem);
    }

    @Override
    public boolean addMenuItem(MenuItem menuItem) {
        return menuItemDataSource.addMenuItem(menuItem);
    }

    @Override
    public ItemCategory getItemCategoryById(long itemCategoryId) {
        return itemCategoryDataSource.getItemCategoryById(itemCategoryId);
    }

    @Override
    public boolean updateItemCategory(ItemCategory itemCategory) {
        return itemCategoryDataSource.updateItemCategory(itemCategory);
    }

    @Override
    public boolean deleteItemCategory(long itemCategoryId) {
        return itemCategoryDataSource.deleteItemCategory(itemCategoryId);
    }

    @Override
    public boolean addItemCategory(ItemCategory itemCategory) {
        return itemCategoryDataSource.addItemCategory(itemCategory);
    }

    @Override
    public List<ItemCategory> getItemCategories() {
        return itemCategoryDataSource.getItemCategories();
    }

    @Override
    public boolean updateItemRating(ItemRating itemRating) {
        return false;
    }

    @Override
    public boolean deleteItemRating(ItemRating itemRating) {
        return false;
    }

    @Override
    public boolean addItemRating(ItemRating itemRating) {
        return false;
    }

    @Override
    public List<ItemRating> getRatingsOfItem(long itemId) {
        return null;
    }

    @Override
    public double getItemRatingOfItem(long itemId) {
        return 0;
    }

    @Override
    public boolean deleteAccountSubscription(AccountSubscription accountSubscription) {
        return subscriptionDataSource.deleteSubscription(accountSubscription);
    }

    @Override
    public boolean addAccountSubscription(AccountSubscription accountSubscription) {
        return subscriptionDataSource.addSubscription(accountSubscription);
    }

    @Override
    public List<Restaurant> getSubscribedRestaurantsOfAccount(String accountId) {
        return subscriptionDataSource.getSubscribedRestaurants(accountId);
    }

    @Override
    public void deleteAll() {
        restaurantDataSource.deleteRestaurants();
        menuDataSource.deleteMenus();
        itemDataSource.deleteItems();
        menuItemDataSource.deleteMenuItems();
        itemCategoryDataSource.deleteItemCategories();
        reviewDataSource.deleteReviews();
        subscriptionDataSource.deleteSubscriptions();
    }

}
