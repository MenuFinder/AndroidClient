package cat.udl.menufinder.ws;

import android.os.AsyncTask;
import android.util.Log;

import java.util.List;
import java.util.Map;

import cat.udl.menufinder.application.MasterApplication;
import cat.udl.menufinder.database.DBManager;
import cat.udl.menufinder.database.DBManagerLocal;
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
        dbManager = DBManagerLocal.getInstance();
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

    public void addMenu(final Menu menu) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                webService.addMenu(menu);
                return null;
            }
        }.execute();
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

    public void deleteMenu(final long menuId) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                webService.deleteMenu(menuId);
                return null;
            }
        }.execute();
    }

    public void updateMenu(final Menu menu) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                webService.updateMenu(menu);
                return null;
            }
        }.execute();
    }

    public void getRestaurantsOfAccount(final String accountId) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                for (Restaurant restaurant : webService.getRestaurantsOfAccount(accountId)) {
                    dbManager.addRestaurant(restaurant);
                }
                return null;
            }
        }.execute();
    }

    public Restaurant getRestaurantById(long restaurantId) {
        return null;
    }


    public void addRestaurant(final Restaurant restaurant) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                webService.addRestaurant(restaurant);
                return null;
            }
        }.execute();
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


    public void deleteRestaurant(final long restaurantId) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                webService.deleteRestaurant(restaurantId);
                return null;
            }
        }.execute();
    }


    public void updateRestaurant(final Restaurant restaurant) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                webService.updateRestaurant(restaurant);
                return null;
            }
        }.execute();
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


    public void updateReview(final Review review) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                webService.updateReview(review);
                return null;
            }
        }.execute();
    }


    public void deleteReview(final long reviewId) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                webService.deleteReview(reviewId);
                return null;
            }
        }.execute();
    }


    public void addReview(final Review review) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                webService.addReview(review);
                return null;
            }
        }.execute();
    }


    public void updateItem(final Item item) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                webService.updateItem(item);
                return null;
            }
        }.execute();
    }


    public void deleteItem(final long itemId) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                webService.deleteItem(itemId);
                return null;
            }
        }.execute();
    }


    public void addItem(final Item item) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                webService.addItem(item);
                return null;
            }
        }.execute();
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

    public void deleteMenuItem(final MenuItem menuItem) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                webService.deleteMenuItem(menuItem);
                return null;
            }
        }.execute();
    }

    public void addMenuItem(final MenuItem menuItem) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                webService.addMenuItem(menuItem);
                return null;
            }
        }.execute();
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

    public void updateItemCategory(final ItemCategory itemCategory) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                webService.updateItemCategory(itemCategory);
                return null;
            }
        }.execute();
    }

    public void deleteItemCategory(final long itemCategoryId) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                webService.deleteItemCategory(itemCategoryId);
                return null;
            }
        }.execute();
    }

    public void addItemCategory(final ItemCategory itemCategory) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                webService.addItemCategory(itemCategory);
                return null;
            }
        }.execute();
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

    public void updateItemRating(final ItemRating itemRating) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                webService.updateItemRating(itemRating);
                return null;
            }
        }.execute();
    }

    public void deleteItemRating(final ItemRating itemRating) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                webService.deleteItemRating(itemRating);
                return null;
            }
        }.execute();
    }

    public void addItemRating(final ItemRating itemRating) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                webService.addItemRating(itemRating);
                return null;
            }
        }.execute();
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

    public void deleteAccountSubscription(final AccountSubscription accountSubscription) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                webService.deleteAccountSubscription(accountSubscription);
                return null;
            }
        }.execute();
    }

    public void addAccountSubscription(final AccountSubscription accountSubscription) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                webService.addAccountSubscription(accountSubscription);
                return null;
            }
        }.execute();
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
        if (!username.equalsIgnoreCase(""))
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
