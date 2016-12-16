package cat.udl.menufinder.ws;

import java.util.List;
import java.util.Map;

import cat.udl.menufinder.database.DBManager;
import cat.udl.menufinder.models.Account;
import cat.udl.menufinder.models.Item;
import cat.udl.menufinder.models.ItemCategory;
import cat.udl.menufinder.models.Menu;
import cat.udl.menufinder.models.MenuItem;
import cat.udl.menufinder.models.Restaurant;
import cat.udl.menufinder.models.Review;

public class WebServiceImpl implements DBManager {
    @Override
    public Account getValidLogin(String id, String password) {
        return null;
    }

    @Override
    public List<Menu> getMenusByRestaurantId(long restaurantId) {
        return WebServiceUtils.getBeanList(WebServiceUtils.get(Path.GET_MENUS_BY_RESTAURANT_ID + restaurantId));
    }

    @Override
    public Menu getMenuById(long menuId) {
        return WebServiceUtils.getBean(WebServiceUtils.get(Path.GET_MENU_BY_ID + menuId), Menu.class);
    }

    @Override
    public boolean addMenu(Menu menu) {
        return WebServiceUtils.post(Path.POST_ADD_MENU, menu);
    }

    @Override
    public List<Menu> getMenus() {
        return WebServiceUtils.getBeanList(WebServiceUtils.get(Path.GET_MENUS));
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
        return WebServiceUtils.getBean(WebServiceUtils.get(Path.GET_RESTAURANT_BY_ID + restaurantId), Restaurant.class);
    }

    @Override
    public boolean addRestaurant(Restaurant restaurant) {
        return WebServiceUtils.post(Path.POST_ADD_RESTAURANT, restaurant);
    }

    @Override
    public List<Restaurant> getRestaurants() {
        return WebServiceUtils.getBeanList(WebServiceUtils.get(Path.GET_RESTAURANTS));
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
        return WebServiceUtils.getBean(WebServiceUtils.get(Path.GET_REVIEW_BY_ID + reviewId), Review.class);
    }

    @Override
    public List<Review> getReviewsOfItem(long itemId) {
        return WebServiceUtils.getBeanList(WebServiceUtils.get(Path.GET_REVIEWS_OF_ITEM + itemId));
    }

    @Override
    public List<Review> getReviewsOfMenu(long menuId) {
        return WebServiceUtils.getBeanList(WebServiceUtils.get(Path.GET_REVIEWS_OF_MENU + menuId));
    }

    @Override
    public List<Review> getReviewsOfRestaurant(long restaurantId) {
        return WebServiceUtils.getBeanList(WebServiceUtils.get(Path.GET_REVIEWS_OF_RESTAURANT + restaurantId));
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
        return WebServiceUtils.post(Path.POST_ADD_REVIEW, review);
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
        return WebServiceUtils.post(Path.POST_ADD_ITEM, item);
    }

    @Override
    public Item getItemById(long itemId) {
        return WebServiceUtils.getBean(WebServiceUtils.get(Path.GET_ITEM_BY_ID + itemId), Item.class);
    }

    @Override
    public List<Item> getRestaurantItems(long restaurantId) {
        return WebServiceUtils.getBeanList(WebServiceUtils.get(Path.GET_RESTAURANT_ITEMS + restaurantId));
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
        return WebServiceUtils.post(Path.POST_ADD_MENU_ITEM, menuItem);
    }

    @Override
    public ItemCategory getItemCategoryById(long itemCategoryId) {
        return WebServiceUtils.getBean(WebServiceUtils.get(Path.GET_ITEM_CATEGORY_BY_ID + itemCategoryId), ItemCategory.class);
    }

    @Override
    public boolean updateItemCategory(ItemCategory itemCategory) {
        return false;
    }

    @Override
    public boolean deleteItemCategory(long id) {
        return false;
    }

    @Override
    public boolean addItemCategory(ItemCategory itemCategory) {
        return WebServiceUtils.post(Path.POST_ADD_ITEM_CATEGORY, itemCategory);
    }

    @Override
    public List<ItemCategory> getItemCategories() {
        return WebServiceUtils.getBeanList(WebServiceUtils.get(Path.GET_ITEM_CATEGORIES));
    }
}
