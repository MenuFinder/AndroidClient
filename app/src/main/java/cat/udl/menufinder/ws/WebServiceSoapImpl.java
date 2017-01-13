package cat.udl.menufinder.ws;

import java.util.List;
import java.util.Map;

import cat.udl.menufinder.database.DBManager;
import cat.udl.menufinder.models.Account;
import cat.udl.menufinder.models.AccountSubscription;
import cat.udl.menufinder.models.Item;
import cat.udl.menufinder.models.ItemCategory;
import cat.udl.menufinder.models.ItemRating;
import cat.udl.menufinder.models.Menu;
import cat.udl.menufinder.models.MenuItem;
import cat.udl.menufinder.models.Restaurant;
import cat.udl.menufinder.models.Review;

public class WebServiceSoapImpl implements DBManager {
    @Override
    public Account getValidLogin(String id, String password) {
        return WebServiceUtils.getBean(WebServiceUtils.login(Path.SOAP_GET_VALID_LOGIN,
                Path.PARAM_ACCOUNT, id, password), Account.class);
    }

    @Override
    public boolean addAccount(Account account) {
        return WebServiceUtils.soap(Path.SOAP_REGISTER_ACCOUNT, Path.PARAM_ACCOUNT, account);
    }

    @Override
    public boolean updateAccountToken(Account account) {
        return WebServiceUtils.soap(Path.SOAP_UPDATE_ACCOUNT_TOKEN, Path.PARAM_ACCOUNT, account);
    }

    @Override
    public List<Menu> getMenusByRestaurantId(long restaurantId) {
        return WebServiceUtils.getBeanList(WebServiceUtils.soap(Path.SOAP_GET_MENUS_BY_RESTAURANT_ID,
                Path.PARAM_RESTRAUNT_ID, String.valueOf(restaurantId)), Menu[].class);
    }

    @Override
    public Menu getMenuById(long menuId) {
        return WebServiceUtils.getBean(WebServiceUtils.soap(Path.SOAP_GET_MENU_BY_ID,
                Path.PARAM_MENU_ID, String.valueOf(menuId)), Menu.class);
    }

    @Override
    public boolean addMenu(Menu menu) {
        return WebServiceUtils.soap(Path.SOAP_ADD_MENU, Path.PARAM_MENU, menu);
    }

    @Override
    public List<Menu> getMenus() {
        return WebServiceUtils.getBeanList(WebServiceUtils.soap(Path.SOAP_GET_MENUS), Menu[].class);
    }

    @Override
    public boolean deleteMenu(long menuId) {
        return WebServiceUtils.delete(Path.SOAP_DELETE_MENU, Path.PARAM_MENU_ID, String.valueOf(menuId));
    }

    @Override
    public boolean updateMenu(Menu menu) {
        return WebServiceUtils.soap(Path.SOAP_UPDATE_MENU, Path.PARAM_MENU, menu);
    }

    @Override
    public List<Restaurant> getRestaurantsOfAccount(String accountId) {
        return WebServiceUtils.getBeanList(WebServiceUtils.soap(Path.SOAP_GET_RESTAURANTS_OF_ACCOUNT,
                Path.PARAM_RESTRAUNT_ID, accountId), Restaurant[].class);
    }

    @Override
    public Restaurant getRestaurantById(long restaurantId) {
        return WebServiceUtils.getBean(WebServiceUtils.soap(Path.SOAP_GET_RESTAURANT_BY_ID,
                Path.PARAM_RESTRAUNT_ID, String.valueOf(restaurantId)), Restaurant.class);
    }

    @Override
    public boolean addRestaurant(Restaurant restaurant) {
        return WebServiceUtils.soap(Path.SOAP_ADD_RESTAURANT, Path.PARAM_RESTAURANT, restaurant);
    }

    @Override
    public List<Restaurant> getRestaurants() {
        return WebServiceUtils.getBeanList(WebServiceUtils.soap(Path.SOAP_GET_RESTAURANTS), Restaurant[].class);
    }

    @Override
    public boolean deleteRestaurant(long restaurantId) {
        return WebServiceUtils.delete(Path.SOAP_DELETE_RESTAURANT, Path.PARAM_RESTRAUNT_ID, String.valueOf(restaurantId));
    }

    @Override
    public boolean updateRestaurant(Restaurant restaurant) {
        return WebServiceUtils.soap(Path.SOAP_UPDATE_RESTAURANT, Path.PARAM_RESTRAUNT_ID, restaurant);
    }

    @Override
    public List<Restaurant> getFilteredRestaurants(String where) {
        return WebServiceUtils.getBeanList(WebServiceUtils.soap(Path.SOAP_GET_FILTERED_RESTAURANTS,
                Path.PARAM_FILTER, where), Restaurant[].class);
    }

    @Override
    public Review getReviewById(long reviewId) {
        return WebServiceUtils.getBean(WebServiceUtils.soap(Path.SOAP_GET_REVIEW_BY_ID,
                Path.PARAM_REVIEW_ID, String.valueOf(reviewId)), Review.class);
    }

    @Override
    public List<Review> getReviewsOfItem(long itemId) {
        return WebServiceUtils.getBeanList(WebServiceUtils.soap(Path.SOAP_GET_REVIEWS_OF_ITEM,
                Path.PARAM_ITEM_ID, String.valueOf(itemId)), Review[].class);
    }

    @Override
    public List<Review> getReviewsOfMenu(long menuId) {
        return WebServiceUtils.getBeanList(WebServiceUtils.soap(Path.SOAP_GET_REVIEWS_OF_MENU,
                Path.PARAM_MENU_ID, String.valueOf(menuId)), Review[].class);
    }

    @Override
    public List<Review> getReviewsOfRestaurant(long restaurantId) {
        return WebServiceUtils.getBeanList(WebServiceUtils.soap(Path.SOAP_GET_REVIEWS_OF_RESTAURANT,
                Path.PARAM_RESTRAUNT_ID, String.valueOf(restaurantId)), Review[].class);
    }

    @Override
    public boolean updateReview(Review review) {
        return WebServiceUtils.soap(Path.SOAP_UPDATE_REVIEW, Path.PARAM_REVIEW, review);
    }

    @Override
    public boolean deleteReview(long reviewId) {
        return WebServiceUtils.delete(Path.SOAP_DELETE_REVIEW, Path.PARAM_REVIEW_ID, String.valueOf(reviewId));
    }

    @Override
    public boolean addReview(Review review) {
        return WebServiceUtils.soap(Path.SOAP_ADD_REVIEW, Path.PARAM_REVIEW, review);
    }

    @Override
    public boolean updateItem(Item item) {
        return WebServiceUtils.soap(Path.SOAP_UPDATE_ITEM, Path.PARAM_ITEM, item);
    }

    @Override
    public boolean deleteItem(long itemId) {
        return WebServiceUtils.delete(Path.SOAP_DELETE_ITEM, Path.PARAM_ITEM_ID, String.valueOf(itemId));
    }

    @Override
    public boolean addItem(Item item) {
        return WebServiceUtils.soap(Path.SOAP_ADD_ITEM, Path.PARAM_ITEM, item);
    }

    @Override
    public Item getItemById(long itemId) {
        return WebServiceUtils.getBean(WebServiceUtils.soap(Path.SOAP_GET_ITEM_BY_ID, Path.PARAM_ITEM_ID,
                String.valueOf(itemId)), Item.class);
    }

    @Override
    public List<Item> getRestaurantItems(long restaurantId) {
        return WebServiceUtils.getBeanList(WebServiceUtils.soap(Path.SOAP_GET_RESTAURANT_ITEMS,
                Path.PARAM_RESTRAUNT_ID, String.valueOf(restaurantId)), Item[].class);
    }

    @Override
    public Map<Long, List<Item>> getMenuItemsByCategory(long menuId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean deleteMenuItem(MenuItem menuItem) {
        return WebServiceUtils.soap(Path.SOAP_DELETE_MENU_ITEM, Path.PARAM_MENU_ITEM, menuItem);
    }

    @Override
    public boolean addMenuItem(MenuItem menuItem) {
        return WebServiceUtils.soap(Path.SOAP_ADD_MENU_ITEM, Path.PARAM_MENU_ITEM, menuItem);
    }

    @Override
    public ItemCategory getItemCategoryById(long itemCategoryId) {
        return WebServiceUtils.getBean(WebServiceUtils.soap(Path.SOAP_GET_ITEM_CATEGORY_BY_ID,
                Path.PARAM_ITEM_CATEGORY_ID, String.valueOf(itemCategoryId)), ItemCategory.class);
    }

    @Override
    public boolean updateItemCategory(ItemCategory itemCategory) {
        return WebServiceUtils.soap(Path.SOAP_UPDATE_ITEM_CATEGORY, Path.PARAM_ITEM_CATEGORY, itemCategory);
    }

    @Override
    public boolean deleteItemCategory(long itemCategoryId) {
        return WebServiceUtils.delete(Path.SOAP_DELETE_ITEM_CATEGORY, Path.PARAM_ITEM_CATEGORY_ID,
                String.valueOf(itemCategoryId));
    }

    @Override
    public boolean addItemCategory(ItemCategory itemCategory) {
        return WebServiceUtils.soap(Path.SOAP_ADD_ITEM_CATEGORY, Path.PARAM_ITEM_CATEGORY, itemCategory);
    }

    @Override
    public List<ItemCategory> getItemCategories() {
        return WebServiceUtils.getBeanList(WebServiceUtils.soap(Path.SOAP_GET_ITEM_CATEGORIES),
                ItemCategory[].class);
    }

    @Override
    public boolean updateItemRating(ItemRating itemRating) {
        return WebServiceUtils.soap(Path.SOAP_UPDATE_ITEM_RATING, Path.PARAM_ITEM_RATING, itemRating);
    }

    @Override
    public boolean deleteItemRating(ItemRating itemRating) {
        return WebServiceUtils.soap(Path.SOAP_DELETE_ITEM_RATING, Path.PARAM_ITEM_RATING, itemRating);
    }

    @Override
    public boolean addItemRating(ItemRating itemRating) {
        return WebServiceUtils.soap(Path.SOAP_ADD_ITEM_RATING, Path.PARAM_ITEM_RATING, itemRating);
    }

    @Override
    public List<ItemRating> getRatingsOfItem(long itemId) {
        return WebServiceUtils.getBeanList(WebServiceUtils.soap(Path.SOAP_GET_RATINGS_OF_ITEMS,
                Path.PARAM_ITEM_ID, String.valueOf(itemId)), ItemRating[].class);
    }

    @Override
    public double getItemRatingOfItem(long itemId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean deleteAccountSubscription(AccountSubscription accountSubscription) {
        return WebServiceUtils.soap(Path.SOAP_DELETE_ACCOUNT_SUBSCRIPTION,
                Path.PARAM_ACCOUNT_SUBSCRIPTION, accountSubscription);
    }

    @Override
    public boolean addAccountSubscription(AccountSubscription accountSubscription) {
        return WebServiceUtils.post(Path.SOAP_ADD_ACCOUNT_SUBSCRIPTION, accountSubscription);
    }

    @Override
    public List<Restaurant> getSubscribedRestaurantsOfAccount(String accountId) {
        return WebServiceUtils.getBeanList(WebServiceUtils.soap(
                Path.SOAP_GET_SUBSCRIBED_RESTAURANTS_OF_ACCOUNT, Path.PARAM_ACCOUNT_ID, accountId),
                Restaurant[].class);
    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Menu> getAllMenusByRestaurantId(long restaurantId) {
        throw new UnsupportedOperationException();
    }

}
