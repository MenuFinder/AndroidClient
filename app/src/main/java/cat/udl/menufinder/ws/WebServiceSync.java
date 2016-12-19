package cat.udl.menufinder.ws;

import android.os.AsyncTask;
import android.util.Log;

import java.util.List;
import java.util.Map;

import cat.udl.menufinder.application.MasterApplication;
import cat.udl.menufinder.database.DBManager;
import cat.udl.menufinder.enums.UserType;
import cat.udl.menufinder.models.AccountSubscription;
import cat.udl.menufinder.models.Item;
import cat.udl.menufinder.models.ItemCategory;
import cat.udl.menufinder.models.ItemRating;
import cat.udl.menufinder.models.Menu;
import cat.udl.menufinder.models.MenuItem;
import cat.udl.menufinder.models.Restaurant;
import cat.udl.menufinder.models.Review;

public class WebServiceSync {

    public static final String TAG = WebServiceSync.class.getSimpleName();

    private static WebServiceSync ourInstance = new WebServiceSync();
    private final DBManager dbManager;
    private final WebServiceImpl webService;

    private WebServiceSync() {
        dbManager = MasterApplication.getContext().getDbManager();
        webService = new WebServiceImpl();
    }

    public static WebServiceSync getInstance() {
        return ourInstance;
    }

    public void syncMenusByRestaurantId(final long restaurantId) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                for (Menu menu : webService.getMenusByRestaurantId(restaurantId)) {
                    Log.d(TAG, menu.toString());
                    dbManager.addMenu(menu);
                }
                return null;
            }
        }.execute();
    }

    public void syncMenuById(final long menuId) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                dbManager.addMenu(webService.getMenuById(menuId));
                return null;
            }
        }.execute();
    }

    public boolean addMenu(Menu menu) {
        return false;
    }

    public void syncMenus() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                for (Menu menu : webService.getMenus()) {
                    dbManager.addMenu(menu);
                }
                return null;
            }
        }.execute();
    }

    public boolean deleteMenu(long menuId) {
        return false;
    }

    public boolean updateMenu(Menu menu) {
        return false;
    }

    public Restaurant getRestaurantById(long restaurantId) {
        return null;
    }


    public boolean addRestaurant(Restaurant restaurant) {
        return false;
    }


    public void syncRestaurants() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                for (Restaurant restaurant : webService.getRestaurants()) {
                    dbManager.addRestaurant(restaurant);
                }
                return null;
            }
        }.execute();
    }


    public boolean deleteRestaurant(long restaurantId) {
        return false;
    }


    public boolean updateRestaurant(Restaurant restaurant) {
        return false;
    }


    public void syncReviewById(final long reviewId) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                dbManager.addReview(webService.getReviewById(reviewId));
                return null;
            }
        }.execute();
    }


    public void syncReviewsOfItem(final long itemId) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                for (Review review : webService.getReviewsOfItem(itemId)) {
                    dbManager.addReview(review);
                }
                return null;
            }
        }.execute();
    }


    public void syncReviewsOfMenu(final long menuId) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                for (Review review : webService.getReviewsOfMenu(menuId)) {
                    dbManager.addReview(review);
                }
                return null;
            }
        }.execute();
    }


    public void syncReviewsOfRestaurant(final long restaurantId) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                for (Review review : webService.getReviewsOfRestaurant(restaurantId)) {
                    dbManager.addReview(review);
                }
                return null;
            }
        }.execute();
    }


    public boolean updateReview(Review review) {
        return false;
    }


    public boolean deleteReview(long reviewId) {
        return false;
    }


    public boolean addReview(Review review) {
        return false;
    }


    public boolean updateItem(Item item) {
        return false;
    }


    public boolean deleteItem(long itemId) {
        return false;
    }


    public boolean addItem(Item item) {
        return false;
    }


    public void syncItemById(final long itemId) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                dbManager.addItem(webService.getItemById(itemId));
                return null;
            }
        }.execute();
    }


    public void syncRestaurantItems(final long restaurantId) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                for (Item item : webService.getRestaurantItems(restaurantId)) {
                    dbManager.addItem(item);
                }
                return null;
            }
        }.execute();
    }

    public void syncItems() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                for (Menu menu : webService.getMenus()) {
                    syncRestaurantItems(menu.getRestaurant());
                    syncMenuItemsByCategory(menu.getId());
                }
                return null;
            }
        }.execute();
    }

    public void syncMenuItemsByCategory(final long menuId) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                Map<Long, List<Item>> longListMap = webService.getMenuItemsByCategory(menuId);
                for (Map.Entry<Long, List<Item>> entry : longListMap.entrySet()) {
                    Long itemCategoryId = entry.getKey();
                    for (Item item : entry.getValue())
                        dbManager.addMenuItem(new MenuItem(menuId, item.getId(), itemCategoryId));
                }
                return null;
            }
        }.execute();
    }

    public boolean deleteMenuItem(MenuItem menuItem) {
        return false;
    }

    public boolean addMenuItem(MenuItem menuItem) {
        return false;
    }

    public void syncItemCategoryById(final long itemCategoryId) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                dbManager.addItemCategory(webService.getItemCategoryById(itemCategoryId));
                return null;
            }
        }.execute();
    }

    public boolean updateItemCategory(ItemCategory itemCategory) {
        return false;
    }

    public boolean deleteItemCategory(long itemCategoryId) {
        return false;
    }

    public boolean addItemCategory(ItemCategory itemCategory) {
        return false;
    }

    public void syncItemCategories() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                for (ItemCategory itemCategory : webService.getItemCategories()) {
                    dbManager.addItemCategory(itemCategory);
                }
                return null;
            }
        }.execute();
    }

    public boolean updateItemRating(ItemRating itemRating) {
        return false;
    }

    public boolean deleteItemRating(ItemRating itemRating) {
        return false;
    }

    public boolean addItemRating(ItemRating itemRating) {
        return false;
    }

    public void syncRatingsOfItem(final long itemId) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                for (ItemRating itemRating : webService.getRatingsOfItem(itemId)) {
                    dbManager.addItemRating(itemRating);
                }
                return null;
            }
        }.execute();
    }


    public double syncItemRatingOfItem(long itemId) {
        return 0;
    }

    public boolean deleteAccountSubscription(AccountSubscription accountSubscription) {
        return false;
    }

    public boolean addAccountSubscription(AccountSubscription accountSubscription) {
        return false;
    }

    public void syncSubscribedRestaurantsOfAccount(final String accountId) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                for (Restaurant restaurant : webService.getSubscribedRestaurantsOfAccount(accountId)) {
                    dbManager.addRestaurant(restaurant);
                    dbManager.addAccountSubscription(new AccountSubscription(accountId, restaurant.getId()));
                }
                return null;
            }
        }.execute();
    }

    public void syncAllData() {
        dbManager.deleteAll();
        syncRestaurants();
        syncMenus();
        syncItems();
        syncItemCategories();
        syncReviews();
        String username = MasterApplication.getContext().getUsername();
        if (!username.equalsIgnoreCase(UserType.GUEST.getText()))
            syncSubscribedRestaurantsOfAccount(username);
    }

    public void syncReviews() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                for (Restaurant restaurant : webService.getRestaurants()) {
                    syncReviewsOfRestaurant(restaurant.getId());
                    for (Menu menu : webService.getMenusByRestaurantId(restaurant.getId()))
                        syncReviewsOfMenu(menu.getId());
                    for (Item item : webService.getRestaurantItems(restaurant.getId()))
                        syncReviewsOfItem(item.getId());
                }
                return null;
            }
        }.execute();
    }
}
