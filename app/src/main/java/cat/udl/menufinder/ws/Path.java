package cat.udl.menufinder.ws;

abstract class Path {

    //GET
    static final String GET_MENUS_BY_RESTAURANT_ID = "/restaurantMenus/";
    static final String GET_MENU_BY_ID = "/getMenu/";
    static final String GET_MENUS = "/getMenus";
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
    static final String GET_RATING_OF_ITEM = "/itemRating/";
    static final String GET_ITEM_RATING_OF_ITEM = "/itemRatingItem/";

    //POST
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

    //DELETE

}
