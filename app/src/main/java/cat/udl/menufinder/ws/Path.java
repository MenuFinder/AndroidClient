package cat.udl.menufinder.ws;

abstract class Path {
    static final String baseUrl = "http://ws.menufinder.tk:8080/MenuFinderWeb/webservice/menufinderws";
    //GET
    static final String GET_METHOD = "GET";
    static final String GET_MENUS_BY_RESTAURANT_ID = "/restaurantMenus/";
    static final String GET_MENU_BY_ID = "/getMenu/";
    static final String GET_MENUS = "/getMenus";
    static final String GET_RESTAURANTS_OF_ACCOUNT = "/restaurantsOfAccount/";
    static final String GET_RESTAURANT_BY_ID = "/restaurant/";
    static final String GET_RESTAURANTS = "/getAllRestaurants";
    static final String GET_REVIEW_BY_ID = "/review/";
    static final String GET_REVIEWS_OF_ITEM = "/itemReviews/";
    static final String GET_REVIEWS_OF_MENU = "/menuReviews/";
    static final String GET_REVIEWS_OF_RESTAURANT = "/restaurantReviews/";
    static final String GET_ITEM_BY_ID = "/item/";
    static final String GET_RESTAURANT_ITEMS = "/restaurantItems/";
    static final String GET_MENU_ITEMS_BY_CATEGORY = "/menuItemsByCategory/";
    static final String GET_ITEM_CATEGORY_BY_ID = "/itemCategory/";
    static final String GET_ITEM_CATEGORIES = "/itemCategories";
    static final String GET_RATINGS_OF_ITEM = "/itemRating/";
    static final String GET_ITEM_RATING_OF_ITEM = "/itemRatingItem/";
    static final String GET_SUBSCRIBED_RESTAURANTS_OF_ACCOUNT = "/subscribedRestaurantsOfAccount/";
    static final String GET_FILTERED_RESTAURANTS = "/getFilteredRestaurants";
    //POST
    static final String POST_METHOD = "POST";
    static final String GET_VALID_LOGIN = "/login";
    static final String POST_ADD_MENU = "/addMenu";
    static final String POST_ADD_RESTAURANT = "/addRestaurant";
    static final String POST_ADD_REVIEW = "/addReview";
    static final String POST_ADD_ITEM = "/addItem";
    static final String POST_ADD_MENU_ITEM = "/addMenuItem";
    static final String POST_ADD_ITEM_CATEGORY = "/addItemCategory";
    static final String POST_ADD_ITEM_RATING = "/addItemRating";
    static final String POST_ADD_ACCOUNT_SUBSCRIPTION = "/addAccountSubscription";
    //PUT
    static final String PUT_METHOD = "PUT";
    static final String POST_ADD_ACCOUNT = "/register";
    static final String PUT_UPDATE_MENU = "/updateMenu";
    static final String PUT_UPDATE_RESTAURANT = "/updateRestaurant";
    static final String PUT_UPDATE_REVIEW = "/updateReview";
    static final String PUT_UPDATE_ITEM = "/updateItem";
    static final String PUT_UPDATE_ITEM_CATEGORY = "/updateItemCategory";
    static final String PUT_UPDATE_ITEM_RATING = "/updateItemRating";
    static final String PUT_UPDATE_ACCOUNT_TOKEN = "/updateAccountToken";
    //DELETE
    static final String DELETE_METHOD = "DELETE";
    static final String DELETE_MENU = "/deleteMenu/";
    static final String DELETE_RESTAURANT = "/deleteRestaurant/";
    static final String DELETE_REVIEW = "/deleteReview/";
    static final String DELETE_ITEM = "/deleteItem/";
    static final String DELETE_MENU_ITEM = "/deleteMenuItem/";
    static final String DELETE_ITEM_CATEGORY = "/deleteItemCategory/";
    static final String DELETE_ITEM_RATING = "/deleteItemRating/";
    static final String DELETE_ACCOUNT_SUBSCRIPTION = "/deleteAccountSubscription/";
}
