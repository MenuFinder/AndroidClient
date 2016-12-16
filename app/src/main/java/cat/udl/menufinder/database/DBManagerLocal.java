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

public class DBManagerLocal implements DBManager {

    private static DBManager ourInstance = new DBManagerLocal();

    private DBManagerLocal() {
    }

    public static DBManager getInstance() {
        return ourInstance;
    }

    @Override
    public Account getValidLogin(String id, String password) {
        return null;
    }

    @Override
    public List<Menu> getMenusByRestaurantId(long restaurantId) {
        return null;
    }

    @Override
    public Menu getMenuById(long menuId) {
        return null;
    }

    @Override
    public boolean addMenu(Menu menu) {
        return false;
    }

    @Override
    public List<Menu> getMenus() {
        return null;
    }

    @Override
    public boolean deleteMenu(long menuId) {
        return false;
    }

    @Override
    public boolean updateMenu(Menu menu) {
        return false;
    }

    @Override
    public Restaurant getRestaurantById(long restaurantId) {
        return null;
    }

    @Override
    public boolean addRestaurant(Restaurant restaurant) {
        return false;
    }

    @Override
    public List<Restaurant> getRestaurants() {
        return null;
    }

    @Override
    public boolean deleteRestaurant(long restaurantId) {
        return false;
    }

    @Override
    public boolean updateRestaurant(Restaurant restaurant) {
        return false;
    }

    @Override
    public Review getReviewById(long reviewId) {
        return null;
    }

    @Override
    public List<Review> getReviewsOfItem(long itemId) {
        return null;
    }

    @Override
    public List<Review> getReviewsOfMenu(long menuId) {
        return null;
    }

    @Override
    public List<Review> getReviewsOfRestaurant(long restaurantId) {
        return null;
    }

    @Override
    public boolean updateReview(Review review) {
        return false;
    }

    @Override
    public boolean deleteReview(long reviewId) {
        return false;
    }

    @Override
    public boolean addReview(Review review) {
        return false;
    }

    @Override
    public boolean updateItem(Item item) {
        return false;
    }

    @Override
    public boolean deleteItem(long itemId) {
        return false;
    }

    @Override
    public boolean addItem(Item item) {
        return false;
    }

    @Override
    public Item getItemById(long itemId) {
        return null;
    }

    @Override
    public List<Item> getRestaurantItems(long restaurantId) {
        return null;
    }

    @Override
    public Map<Long, List<Item>> getMenuItemsByCategory(long menuId) {
        return null;
    }

    @Override
    public boolean deleteMenuItem(MenuItem menuItem) {
        return false;
    }

    @Override
    public boolean addMenuItem(MenuItem menuItem) {
        return false;
    }

    @Override
    public ItemCategory getItemCategoryById(long itemCategoryId) {
        return null;
    }

    @Override
    public boolean updateItemCategory(ItemCategory itemCategory) {
        return false;
    }

    @Override
    public boolean deleteItemCategory(long itemCategoryId) {
        return false;
    }

    @Override
    public boolean addItemCategory(ItemCategory itemCategory) {
        return false;
    }

    @Override
    public List<ItemCategory> getItemCategories() {
        return null;
    }
}
