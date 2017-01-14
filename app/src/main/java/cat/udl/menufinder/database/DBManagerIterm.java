package cat.udl.menufinder.database;

import com.google.firebase.messaging.FirebaseMessaging;

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
import cat.udl.menufinder.ws.WebServiceImpl;
import cat.udl.menufinder.ws.WebServiceSoapImpl;
import cat.udl.menufinder.ws.WebServiceSync;

public class DBManagerIterm implements DBManager {

    private static DBManagerIterm ourInstance = new DBManagerIterm();
    private final WebServiceSync sync;
    private final DBManager local;
    private final WebServiceImpl rest;
    private final WebServiceSoapImpl soap;

    private DBManagerIterm() {
        rest = new WebServiceImpl();
        soap = new WebServiceSoapImpl();
        sync = WebServiceSync.getInstance();
        local = DBManagerLocal.getInstance();
    }

    public static DBManagerIterm getInstance() {
        return ourInstance;
    }

    @Override
    public Account getValidLogin(String id, String password) {
        return soap.getValidLogin(id, password);
    }

    @Override
    public boolean addAccount(Account account) {
        return soap.addAccount(account);
    }

    @Override
    public List<Menu> getMenusByRestaurantId(long restaurantId) {
        return local.getMenusByRestaurantId(restaurantId);
    }

    @Override
    public Menu getMenuById(long menuId) {
        return local.getMenuById(menuId);
    }

    @Override
    public boolean addMenu(Menu menu) {
        sync.addMenu(menu);
        return local.addMenu(menu);
    }

    @Override
    public List<Menu> getMenus() {
        return local.getMenus();
    }

    @Override
    public boolean deleteMenu(long menuId) {
        sync.deleteMenu(menuId);
        return local.deleteMenu(menuId);
    }

    @Override
    public boolean updateMenu(Menu menu) {
        sync.updateMenu(menu);
        return local.updateMenu(menu);
    }

    @Override
    public List<Restaurant> getRestaurantsOfAccount(String accountId) {
        return local.getRestaurantsOfAccount(accountId);
    }

    @Override
    public Restaurant getRestaurantById(long restaurantId) {
        return local.getRestaurantById(restaurantId);
    }

    @Override
    public boolean addRestaurant(Restaurant restaurant) {
        sync.addRestaurant(restaurant);
        return local.addRestaurant(restaurant);
    }

    @Override
    public List<Restaurant> getRestaurants() {
        return local.getRestaurants();
    }

    @Override
    public boolean deleteRestaurant(long restaurantId) {
        sync.deleteRestaurant(restaurantId);
        return local.deleteRestaurant(restaurantId);
    }

    @Override
    public boolean updateRestaurant(Restaurant restaurant) {
        sync.updateRestaurant(restaurant);
        return local.updateRestaurant(restaurant);
    }

    @Override
    public Review getReviewById(long reviewId) {
        return local.getReviewById(reviewId);
    }

    @Override
    public List<Review> getReviewsOfItem(long itemId) {
        return local.getReviewsOfItem(itemId);
    }

    @Override
    public List<Review> getReviewsOfMenu(long menuId) {
        return local.getReviewsOfMenu(menuId);
    }

    @Override
    public List<Review> getReviewsOfRestaurant(long restaurantId) {
        return local.getReviewsOfRestaurant(restaurantId);
    }

    @Override
    public boolean updateReview(Review review) {
        sync.updateReview(review);
        return local.updateReview(review);
    }

    @Override
    public boolean deleteReview(long reviewId) {
        sync.deleteReview(reviewId);
        return local.deleteReview(reviewId);
    }

    @Override
    public boolean addReview(Review review) {
        sync.addReview(review);
        return local.addReview(review);
    }

    @Override
    public boolean updateItem(Item item) {
        sync.updateItem(item);
        return local.updateItem(item);
    }

    @Override
    public boolean deleteItem(long itemId) {
        sync.deleteItem(itemId);
        return local.deleteItem(itemId);
    }

    @Override
    public boolean addItem(Item item) {
        sync.addItem(item);
        return local.addItem(item);
    }

    @Override
    public Item getItemById(long itemId) {
        return local.getItemById(itemId);
    }

    @Override
    public List<Item> getRestaurantItems(long restaurantId) {
        return local.getRestaurantItems(restaurantId);
    }

    @Override
    public Map<Long, List<Item>> getMenuItemsByCategory(long menuId) {
        return local.getMenuItemsByCategory(menuId);
    }

    @Override
    public boolean deleteMenuItem(MenuItem menuItem) {
        sync.deleteMenuItem(menuItem);
        return local.deleteMenuItem(menuItem);
    }

    @Override
    public boolean addMenuItem(MenuItem menuItem) {
        sync.addMenuItem(menuItem);
        return local.addMenuItem(menuItem);
    }

    @Override
    public ItemCategory getItemCategoryById(long itemCategoryId) {
        return local.getItemCategoryById(itemCategoryId);
    }

    @Override
    public boolean updateItemCategory(ItemCategory itemCategory) {
        sync.updateItemCategory(itemCategory);
        return local.updateItemCategory(itemCategory);
    }

    @Override
    public boolean deleteItemCategory(long itemCategoryId) {
        sync.deleteItemCategory(itemCategoryId);
        return local.deleteItemCategory(itemCategoryId);
    }

    @Override
    public boolean addItemCategory(ItemCategory itemCategory) {
        sync.addItemCategory(itemCategory);
        return local.addItemCategory(itemCategory);
    }

    @Override
    public List<ItemCategory> getItemCategories() {
        return local.getItemCategories();
    }

    @Override
    public boolean updateItemRating(ItemRating itemRating) {
        sync.updateItemRating(itemRating);
        return local.updateItemRating(itemRating);
    }

    @Override
    public boolean deleteItemRating(ItemRating itemRating) {
        sync.deleteItemRating(itemRating);
        return local.deleteItemRating(itemRating);
    }

    @Override
    public boolean addItemRating(ItemRating itemRating) {
        sync.addItemRating(itemRating);
        return local.addItemRating(itemRating);
    }

    @Override
    public List<ItemRating> getRatingsOfItem(long itemId) {
        return local.getRatingsOfItem(itemId);
    }

    @Override
    public double getItemRatingOfItem(long itemId) {
        return local.getItemRatingOfItem(itemId);
    }

    @Override
    public boolean deleteAccountSubscription(AccountSubscription accountSubscription) {
        FirebaseMessaging.getInstance().unsubscribeFromTopic(String.valueOf(local.getRestaurantById(accountSubscription.getRestaurant()).getId()));
        sync.deleteAccountSubscription(accountSubscription);
        return local.deleteAccountSubscription(accountSubscription);
    }

    @Override
    public boolean addAccountSubscription(AccountSubscription accountSubscription) {
        FirebaseMessaging.getInstance().subscribeToTopic(String.valueOf(local.getRestaurantById(accountSubscription.getRestaurant()).getId()));
        sync.addAccountSubscription(accountSubscription);
        return local.addAccountSubscription(accountSubscription);
    }

    @Override
    public List<Restaurant> getSubscribedRestaurantsOfAccount(String accountId) {
        return local.getSubscribedRestaurantsOfAccount(accountId);
    }

    @Override
    public void deleteAll() {
        local.deleteAll();
    }

    @Override
    public List<Menu> getAllMenusByRestaurantId(long restaurantId) {
        return local.getAllMenusByRestaurantId(restaurantId);
    }

    @Override
    public boolean updateAccountToken(Account account) {
        return soap.updateAccountToken(account);
    }

    @Override
    public List<Restaurant> getFilteredRestaurants(String where) {
        List<Restaurant> restaurants = local.getFilteredRestaurants(where);
        if (restaurants.isEmpty()) restaurants = rest.getFilteredRestaurants(where);
        return restaurants;
    }

    @Override
    public List<String> getAllDifferentCities() {
        return local.getAllDifferentCities();
    }

    @Override
    public List<String> getAllRestaurantNames() {
        return local.getAllRestaurantNames();
    }
}
