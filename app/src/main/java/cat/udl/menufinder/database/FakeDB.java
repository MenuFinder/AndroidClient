package cat.udl.menufinder.database;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cat.udl.menufinder.models.Item;
import cat.udl.menufinder.models.ItemCategory;
import cat.udl.menufinder.models.Menu;
import cat.udl.menufinder.models.MenuItem;
import cat.udl.menufinder.models.Restaurant;
import cat.udl.menufinder.models.Review;

public class FakeDB implements DBManager {

    private static FakeDB ourInstance = new FakeDB();
    private List<Restaurant> restaurants;
    private List<ItemCategory> itemCategories;

    public static FakeDB getInstance() {
        return ourInstance;
    }

    private FakeDB() {
        restaurants = new ArrayList<>();
        itemCategories = new ArrayList<>();
        initializeFakeCategories();
        initializeFakeData();
    }

    @Override
    public void addRestaurant(Restaurant restaurant) {
        restaurants.add(restaurant);
    }

    @Override
    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    @Override
    public List<ItemCategory> getItemCategories() {
        return itemCategories;
    }

    private void initializeFakeData() {
        Restaurant testRestaurant = new Restaurant("Mesillas Restaurant", "123456789X",
                "Av. Balmes 54", "Lleida", "25003", "Spaña", "Lleida", "", "973 973 973");
        testRestaurant.setScore(4.2);
        restaurants.add(testRestaurant);
        Menu testMenu = new Menu("Mesillas Menu", "The best menu of Lleida city", 172.16, true);
        testRestaurant.addMenu(testMenu);
        Item item = new Item("Garlic Bread", "Very strangerous flavour", 4.5);
        item.addReview(new Review("Very good dish!"));
        item.addReview(new Review("I don't like"));
        testMenu.addMenuItem(new MenuItem(item, itemCategories.get(0)));
        testMenu.addMenuItem(new MenuItem(new Item("Cheese Plate", "", 4.5), itemCategories.get(0)));
        testMenu.addMenuItem(new MenuItem(new Item("Nachos", "", 4.5), itemCategories.get(0)));
        testMenu.addMenuItem(new MenuItem(new Item("Tossed Salad", "", 4.5), itemCategories.get(1)));
        testMenu.addMenuItem(new MenuItem(new Item("Caesar Salad", "", 4.5), itemCategories.get(1)));
        testMenu.addMenuItem(new MenuItem(new Item("Soup of the Day", "", 4.5), itemCategories.get(1)));
        testMenu.addMenuItem(new MenuItem(new Item("Grilled Chicken Sandwich", "", 4.5), itemCategories.get(2)));
        testMenu.addMenuItem(new MenuItem(new Item("Veggie (Garden) Burger", "", 4.5), itemCategories.get(2)));
        testMenu.addMenuItem(new MenuItem(new Item("Steak Sandwich", "", 4.5), itemCategories.get(2)));
        Menu testMenu01 = new Menu("Mesillas Italian", "", 11.50, true);
        testRestaurant.addMenu(testMenu01);
        testMenu01.addMenuItem(new MenuItem(new Item("Spaghetti", "", 4.5), itemCategories.get(3)));
        testMenu01.addMenuItem(new MenuItem(new Item("Pepperoni Pizza", "", 4.5), itemCategories.get(3)));
        testMenu01.addMenuItem(new MenuItem(new Item("Fettucini", "", 4.5), itemCategories.get(3)));

        Restaurant testRestaurant1 = new Restaurant("Calmao Restaurant", "123456781X",
                "Av. Madrid 1", "Lleida", "25002", "Spaña", "Lleida", "", "973 973 970");
        testRestaurant1.setScore(4.5);
        restaurants.add(testRestaurant1);
        Menu testMenu1 = new Menu("Calmao Menu", "The very best menu of Lleida city", 12.50, true);
        testRestaurant1.addMenu(testMenu1);
        testMenu1.addMenuItem(new MenuItem(new Item("New York Steak", "", 4.5), itemCategories.get(4)));
        testMenu1.addMenuItem(new MenuItem(new Item("Chicken Stirfry", "", 4.5), itemCategories.get(4)));
        testMenu1.addMenuItem(new MenuItem(new Item("Hearty Stew", "", 4.5), itemCategories.get(4)));
        testMenu1.addMenuItem(new MenuItem(new Item("French Fries", "", 4.5), itemCategories.get(5)));
        testMenu1.addMenuItem(new MenuItem(new Item("Rice, Grilled Veggies", "", 4.5), itemCategories.get(5)));
        testMenu1.addMenuItem(new MenuItem(new Item("Fish and Chips", "", 4.5), itemCategories.get(6)));
        testMenu1.addMenuItem(new MenuItem(new Item("Battered Shrimp", "", 4.5), itemCategories.get(6)));
        testMenu1.addMenuItem(new MenuItem(new Item("Smoked Salmon", "", 4.5), itemCategories.get(6)));
        testMenu1.addMenuItem(new MenuItem(new Item("Fajitas", "", 4.5), itemCategories.get(7)));
        testMenu1.addMenuItem(new MenuItem(new Item("Nachos", "", 4.5), itemCategories.get(7)));
        testMenu1.addMenuItem(new MenuItem(new Item("Enchilladas", "", 4.5), itemCategories.get(7)));

        Restaurant testRestaurant2 = new Restaurant("Messon 2004", "123416781X",
                "C\\Sant Pelegrí 25", "Tàrrega", "25300", "Spaña", "Lleida", "", "973 310 367");
        testRestaurant2.setScore(3.5);
        restaurants.add(testRestaurant2);
        Menu testMenu2 = new Menu("Messon Menu", "The very best menu of Tàrrega city", 10.0, true);
        testRestaurant2.addMenu(testMenu2);
        testMenu2.addMenuItem(new MenuItem(new Item("BBQ Ribs", "", 4.5), itemCategories.get(8)));
        testMenu2.addMenuItem(new MenuItem(new Item("Hot Wings", "", 4.5), itemCategories.get(8)));
        testMenu2.addMenuItem(new MenuItem(new Item("Chicken Cordon Bleu", "", 4.5), itemCategories.get(8)));
        testMenu2.addMenuItem(new MenuItem(new Item("Apple Pie", "", 4.5), itemCategories.get(9)));
        testMenu2.addMenuItem(new MenuItem(new Item("Mocha Cheesecake", "", 4.5), itemCategories.get(9)));
        testMenu2.addMenuItem(new MenuItem(new Item("Banana Split", "", 4.5), itemCategories.get(9)));
        testMenu2.addMenuItem(new MenuItem(new Item("Soda Pop", "", 4.5), itemCategories.get(10)));
        testMenu2.addMenuItem(new MenuItem(new Item("Juice", "", 4.5), itemCategories.get(10)));
        testMenu2.addMenuItem(new MenuItem(new Item("Milk", "", 4.5), itemCategories.get(10)));
        testMenu2.addMenuItem(new MenuItem(new Item("House Wine", "", 4.5), itemCategories.get(11)));
        testMenu2.addMenuItem(new MenuItem(new Item("Jug of Beer", "", 4.5), itemCategories.get(11)));
        testMenu2.addMenuItem(new MenuItem(new Item("Peach Cider", "", 4.5), itemCategories.get(11)));
        testMenu2.addMenuItem(new MenuItem(new Item("Spaghetti and Meatballs", "", 4.5), itemCategories.get(12)));
        testMenu2.addMenuItem(new MenuItem(new Item("Cheeseburger", "", 4.5), itemCategories.get(12)));
        testMenu2.addMenuItem(new MenuItem(new Item("Chicken Fingers", "", 4.5), itemCategories.get(12)));
    }

    private void initializeFakeCategories() {
        itemCategories.add(new ItemCategory("Appetizers", "")); //0
        itemCategories.add(new ItemCategory("Salads and Soups", "")); //1
        itemCategories.add(new ItemCategory("Sandwiches", "")); //2
        itemCategories.add(new ItemCategory("Italian", "")); //3
        itemCategories.add(new ItemCategory("Main Course", "")); //4
        itemCategories.add(new ItemCategory("Sides", "")); //5
        itemCategories.add(new ItemCategory("Seafood", "")); //6
        itemCategories.add(new ItemCategory("Mexican", "")); //7
        itemCategories.add(new ItemCategory("Specialties", "")); //8
        itemCategories.add(new ItemCategory("Desserts", "")); //9
        itemCategories.add(new ItemCategory("Beverages", "")); //10
        itemCategories.add(new ItemCategory("Wine and Beer", "")); //11
        itemCategories.add(new ItemCategory("Kids Menu", "")); //12
    }

    public List<Item> getAllItemsOfRestaurant(int restaurant_id) {
        List<Item> items = new ArrayList<>();
        for (Menu m : restaurants.get(restaurant_id).getMenus()) {
            for (Map.Entry<ItemCategory, List<Item>> itemCategoryList : m.getItemsCategory().entrySet()) {
                for (Item item : itemCategoryList.getValue()) {
                    items.add(item);
                }
            }
        }
        return items;
    }

    public List<Menu> getAllMenusOfRestaurant(int restaurant_id) {
        return restaurants.get(restaurant_id).getMenus();
    }

}
