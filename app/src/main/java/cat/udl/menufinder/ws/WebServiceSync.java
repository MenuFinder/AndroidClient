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
    private final WebServiceImpl rest;
    private final WebServiceSoapImpl soap;

    private WebServiceSync() {
        dbManager = DBManagerLocal.getInstance();
        rest = new WebServiceImpl();
        soap = new WebServiceSoapImpl();
    }

    public static WebServiceSync getInstance() {
        return ourInstance;
    }

    public void syncMenusByRestaurantId(final long restaurantId) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                for (Menu menu : rest.getMenusByRestaurantId(restaurantId)) {
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
                dbManager.addMenu(rest.getMenuById(menuId));
                return null;
            }
        }.execute();
    }

    public void addMenu(final Menu menu) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                soap.addMenu(menu);
                return null;
            }
        }.execute();
    }

    public void syncMenus() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                for (Menu menu : rest.getMenus()) {
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
                soap.deleteMenu(menuId);
                return null;
            }
        }.execute();
    }

    public void updateMenu(final Menu menu) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                soap.updateMenu(menu);
                return null;
            }
        }.execute();
    }

    public void getRestaurantsOfAccount(final String accountId) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                for (Restaurant restaurant : rest.getRestaurantsOfAccount(accountId)) {
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
                soap.addRestaurant(restaurant);
                return null;
            }
        }.execute();
    }


    public void syncRestaurants() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                for (Restaurant restaurant : rest.getRestaurants()) {
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
                soap.deleteRestaurant(restaurantId);
                return null;
            }
        }.execute();
    }


    public void updateRestaurant(final Restaurant restaurant) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                soap.updateRestaurant(restaurant);
                return null;
            }
        }.execute();
    }


    public void syncReviewById(final long reviewId) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                dbManager.addReview(rest.getReviewById(reviewId));
                return null;
            }
        }.execute();
    }


    public void syncReviewsOfItem(final long itemId) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                for (Review review : rest.getReviewsOfItem(itemId)) {
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
                for (Review review : rest.getReviewsOfMenu(menuId)) {
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
                for (Review review : rest.getReviewsOfRestaurant(restaurantId)) {
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
                rest.updateReview(review);
                return null;
            }
        }.execute();
    }


    public void deleteReview(final long reviewId) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                rest.deleteReview(reviewId);
                return null;
            }
        }.execute();
    }


    public void addReview(final Review review) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                rest.addReview(review);
                return null;
            }
        }.execute();
    }


    public void updateItem(final Item item) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                soap.updateItem(item);
                return null;
            }
        }.execute();
    }


    public void deleteItem(final long itemId) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                soap.deleteItem(itemId);
                return null;
            }
        }.execute();
    }


    public void addItem(final Item item) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                soap.addItem(item);
                return null;
            }
        }.execute();
    }


    public void syncItemById(final long itemId) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                dbManager.addItem(rest.getItemById(itemId));
                return null;
            }
        }.execute();
    }


    public void syncRestaurantItems(final long restaurantId) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                for (Item item : rest.getRestaurantItems(restaurantId)) {
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
                for (Menu menu : rest.getMenus()) {
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
                Map<Long, List<Item>> longListMap = rest.getMenuItemsByCategory(menuId);
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
                soap.deleteMenuItem(menuItem);
                return null;
            }
        }.execute();
    }

    public void addMenuItem(final MenuItem menuItem) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                soap.addMenuItem(menuItem);
                return null;
            }
        }.execute();
    }

    public void syncItemCategoryById(final long itemCategoryId) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                dbManager.addItemCategory(rest.getItemCategoryById(itemCategoryId));
                return null;
            }
        }.execute();
    }

    public void updateItemCategory(final ItemCategory itemCategory) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                soap.updateItemCategory(itemCategory);
                return null;
            }
        }.execute();
    }

    public void deleteItemCategory(final long itemCategoryId) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                soap.deleteItemCategory(itemCategoryId);
                return null;
            }
        }.execute();
    }

    public void addItemCategory(final ItemCategory itemCategory) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                soap.addItemCategory(itemCategory);
                return null;
            }
        }.execute();
    }

    public void syncItemCategories() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                for (ItemCategory itemCategory : rest.getItemCategories()) {
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
                rest.updateItemRating(itemRating);
                return null;
            }
        }.execute();
    }

    public void deleteItemRating(final ItemRating itemRating) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                rest.deleteItemRating(itemRating);
                return null;
            }
        }.execute();
    }

    public void addItemRating(final ItemRating itemRating) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                rest.addItemRating(itemRating);
                return null;
            }
        }.execute();
    }

    public void syncRatingsOfItem(final long itemId) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                for (ItemRating itemRating : rest.getRatingsOfItem(itemId)) {
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
                rest.deleteAccountSubscription(accountSubscription);
                return null;
            }
        }.execute();
    }

    public void addAccountSubscription(final AccountSubscription accountSubscription) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                rest.addAccountSubscription(accountSubscription);
                return null;
            }
        }.execute();
    }

    public void syncSubscribedRestaurantsOfAccount(final String accountId) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                for (Restaurant restaurant : rest.getSubscribedRestaurantsOfAccount(accountId)) {
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
                for (Restaurant restaurant : rest.getRestaurants()) {
                    syncReviewsOfRestaurant(restaurant.getId());
                    for (Menu menu : rest.getMenusByRestaurantId(restaurant.getId()))
                        syncReviewsOfMenu(menu.getId());
                    for (Item item : rest.getRestaurantItems(restaurant.getId()))
                        syncReviewsOfItem(item.getId());
                }
                return null;
            }
        }.execute();
    }
}
