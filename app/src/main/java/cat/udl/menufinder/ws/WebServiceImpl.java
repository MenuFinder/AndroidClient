package cat.udl.menufinder.ws;

import java.util.List;
import java.util.Map;

import cat.udl.menufinder.models.Account;
import cat.udl.menufinder.models.Item;
import cat.udl.menufinder.models.ItemCategory;
import cat.udl.menufinder.models.Menu;
import cat.udl.menufinder.models.MenuItem;
import cat.udl.menufinder.models.Restaurant;
import cat.udl.menufinder.models.Review;

public class WebServiceImpl implements WebService {
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
    public String addNewMenu(Menu menu) {
        return null;
    }

    @Override
    public List<Menu> getMenus() {
        return WebServiceUtils.getBeanList(WebServiceUtils.get(Path.GET_MENUS));
    }

    @Override
    public String deleteMenu(long menuId) {
        return null;
    }

    @Override
    public String updateMenu(Menu menu) {
        return null;
    }

    @Override
    public Restaurant getRestaurantById(long restaurantId) {
        return WebServiceUtils.getBean(WebServiceUtils.get(Path.GET_RESTAURANT_BY_ID + restaurantId), Restaurant.class);
    }

    @Override
    public String addNewRestaurant(Restaurant restaurant) {
        return null;
    }

    @Override
    public List<Restaurant> getRestaurants() {
        return WebServiceUtils.getBeanList(WebServiceUtils.get(Path.GET_RESTAURANTS));
    }

    @Override
    public String deleteRestaurant(long restaurantId) {
        return null;
    }

    @Override
    public String updateRestaurant(Restaurant restaurant) {
        return null;
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
    public String updateReview(Review review) {
        return null;
    }

    @Override
    public String deleteReview(long reviewId) {
        return null;
    }

    @Override
    public String addReview(Review review) {
        return null;
    }

    @Override
    public String updateItem(Item item) {
        return null;
    }

    @Override
    public String deleteItem(long itemId) {
        return null;
    }

    @Override
    public String addItem(Item item) {
        return null;
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
    public String deleteMenuItem(MenuItem menuItem) {
        return null;
    }

    @Override
    public String addMenuItem(MenuItem menuItem) {
        return null;
    }

    @Override
    public ItemCategory getItemCategoryById(long itemCategoryId) {
        return WebServiceUtils.getBean(WebServiceUtils.get(Path.GET_ITEM_CATEGORY_BY_ID + itemCategoryId), ItemCategory.class);
    }

    @Override
    public String updateItemCategory(ItemCategory itemCategory) {
        return null;
    }

    @Override
    public String deleteItemCategory(long id) {
        return null;
    }

    @Override
    public String addItemCategory(ItemCategory itemCategory) {
        return null;
    }

    @Override
    public List<ItemCategory> getItemCategories() {
        return WebServiceUtils.getBeanList(WebServiceUtils.get(Path.GET_ITEM_CATEGORIES));
    }
}
