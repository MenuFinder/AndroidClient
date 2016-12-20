package cat.udl.menufinder.utils;

import cat.udl.menufinder.database.DBManager;
import cat.udl.menufinder.models.Item;
import cat.udl.menufinder.models.ItemCategory;
import cat.udl.menufinder.models.Menu;
import cat.udl.menufinder.models.MenuItem;
import cat.udl.menufinder.models.Restaurant;

public class FakeData {

    private final DBManager dbManager;

    public FakeData(DBManager dbManager) {
        this.dbManager = dbManager;
        initializeFakeData();
        initializeFakeCategories();
    }

    private void initializeFakeData() {
        int restaurantId = 0;
        int menuId = 0;
        int itemId = 0;
        int itemCategoryId = 0;
        Restaurant restaurant;
        Menu menu;
        restaurant = new Restaurant("Mesillas Restaurant", "123456789X",
                "Av. Balmes 54", "Lleida", "25003", "España", "Lleida", "", "973 973 973", "restaurant");
        restaurant.setId(restaurantId);
        restaurant.setScore(4.2);
        dbManager.addRestaurant(restaurant);

        menu = new Menu(restaurantId, "Mesillas Menu", "The best menu of Lleida city", 172.16);
        menu.setId(menuId);
        dbManager.addMenu(menu);

        addMenuItem(new Item("Garlic Bread", "", 4.5, restaurantId), itemId, menuId, itemCategoryId);
        itemId++;
        addMenuItem(new Item("Cheese Plate", "", 4.5, restaurantId), itemId, menuId, itemCategoryId);
        itemId++;
        addMenuItem(new Item("Nachos", "", 4.5, restaurantId), itemId, menuId, itemCategoryId);
        itemId++;
        itemCategoryId++;

        addMenuItem(new Item("Tossed Salad", "", 4.5, restaurantId), itemId, menuId, itemCategoryId);
        itemId++;
        addMenuItem(new Item("Caesar Salad", "", 4.5, restaurantId), itemId, menuId, itemCategoryId);
        itemId++;
        addMenuItem(new Item("Soup of the Day", "", 4.5, restaurantId), itemId, menuId, itemCategoryId);
        itemId++;
        itemCategoryId++;

        addMenuItem(new Item("Grilled Chicken Sandwich", "", 4.5, restaurantId), itemId, menuId, itemCategoryId);
        itemId++;
        addMenuItem(new Item("Veggie (Garden) Burger", "", 4.5, restaurantId), itemId, menuId, itemCategoryId);
        itemId++;
        addMenuItem(new Item("Steak Sandwich", "", 4.5, restaurantId), menuId, itemId, itemCategoryId);
        itemCategoryId++;

        menuId++;

        menu = new Menu(restaurantId, "Mesillas Italian", "", 11.50);
        menu.setId(menuId);
        dbManager.addMenu(menu);

        addMenuItem(new Item("Spaghetti", "", 4.5, restaurantId), itemId, menuId, itemCategoryId);
        itemId++;
        addMenuItem(new Item("Pepperoni Pizza", "", 4.5, restaurantId), itemId, menuId, itemCategoryId);
        itemId++;
        addMenuItem(new Item("Fettucini", "", 4.5, restaurantId), itemId, menuId, itemCategoryId);
        itemId++;
        itemCategoryId++;

        restaurantId++;


        restaurant = new Restaurant("Calmao Restaurant", "123456781X",
                "Av. Madrid 1", "Lleida", "25002", "Spaña", "Lleida", "", "973 973 970", "restaurant");
        restaurant.setId(restaurantId);
        restaurant.setScore(4.5);
        dbManager.addRestaurant(restaurant);

        menuId++;

        menu = new Menu(restaurantId, "Calmao Menu", "The very best menu of Lleida city", 12.50);
        menu.setId(menuId);
        dbManager.addMenu(menu);
        addMenuItem(new Item("New York Steak", "", 4.5, restaurantId), itemId, menuId, itemCategoryId);
        itemId++;
        addMenuItem(new Item("Chicken Stirfry", "", 4.5, restaurantId), itemId, menuId, itemCategoryId);
        itemId++;
        addMenuItem(new Item("Hearty Stew", "", 4.5, restaurantId), itemId, menuId, itemCategoryId);
        itemId++;
        itemCategoryId++;

        addMenuItem(new Item("French Fries", "", 4.5, restaurantId), itemId, menuId, itemCategoryId);
        itemId++;
        addMenuItem(new Item("Rice, Grilled Veggies", "", 4.5, restaurantId), itemId, menuId, itemCategoryId);
        itemId++;
        itemCategoryId++;

        addMenuItem(new Item("Fish and Chips", "", 4.5, restaurantId), itemId, menuId, itemCategoryId);
        itemId++;
        addMenuItem(new Item("Battered Shrimp", "", 4.5, restaurantId), itemId, menuId, itemCategoryId);
        itemId++;
        addMenuItem(new Item("Smoked Salmon", "", 4.5, restaurantId), itemId, menuId, itemCategoryId);
        itemId++;
        itemCategoryId++;

        addMenuItem(new Item("Fajitas", "", 4.5, restaurantId), itemId, menuId, itemCategoryId);
        itemId++;
        addMenuItem(new Item("Nachos", "", 4.5, restaurantId), itemId, menuId, itemCategoryId);
        itemId++;
        addMenuItem(new Item("Enchilladas", "", 4.5, restaurantId), itemId, menuId, itemCategoryId);
        itemId++;
        itemCategoryId++;

        restaurantId++;

        restaurant = new Restaurant("Messon 2004", "123416781X",
                "C\\Sant Pelegrí 25", "Tàrrega", "25300", "Spaña", "Lleida", "", "973 310 367", "restaurant");
        restaurant.setId(restaurantId);
        restaurant.setScore(3.5);
        dbManager.addRestaurant(restaurant);

        menuId++;

        menu = new Menu(restaurantId, "Messon Menu", "The very best menu of Tàrrega city", 10.0);
        menu.setId(menuId);
        dbManager.addMenu(menu);
        addMenuItem(new Item("BBQ Ribs", "", 4.5, 2), itemId, menuId, itemCategoryId);
        itemId++;
        addMenuItem(new Item("Hot Wings", "", 4.5, 2), itemId, menuId, itemCategoryId);
        itemId++;
        addMenuItem(new Item("Chicken Cordon Bleu", "", 4.5, 2), itemId, menuId, itemCategoryId);
        itemId++;
        itemCategoryId++;

        addMenuItem(new Item("Apple Pie", "", 4.5, restaurantId), itemId, menuId, itemCategoryId);
        itemId++;
        addMenuItem(new Item("Mocha Cheesecake", "", 4.5, restaurantId), itemId, menuId, itemCategoryId);
        itemId++;
        addMenuItem(new Item("Banana Split", "", 4.5, restaurantId), itemId, menuId, itemCategoryId);
        itemId++;
        itemCategoryId++;

        addMenuItem(new Item("Soda Pop", "", 4.5, restaurantId), itemId, menuId, itemCategoryId);
        itemId++;
        addMenuItem(new Item("Juice", "", 4.5, restaurantId), itemId, menuId, itemCategoryId);
        itemId++;
        addMenuItem(new Item("Milk", "", 4.5, restaurantId), itemId, menuId, itemCategoryId);
        itemId++;
        itemCategoryId++;

        addMenuItem(new Item("House Wine", "", 4.5, restaurantId), itemId, menuId, itemCategoryId);
        itemId++;
        addMenuItem(new Item("Jug of Beer", "", 4.5, restaurantId), itemId, menuId, itemCategoryId);
        itemId++;
        addMenuItem(new Item("Peach Cider", "", 4.5, restaurantId), itemId, menuId, itemCategoryId);
        itemId++;
        itemCategoryId++;

        addMenuItem(new Item("Spaghetti and Meatballs", "", 4.5, restaurantId), itemId, menuId, itemCategoryId);
        itemId++;
        addMenuItem(new Item("Cheeseburger", "", 4.5, restaurantId), itemId, menuId, itemCategoryId);
        itemId++;
        addMenuItem(new Item("Chicken Fingers", "", 4.5, restaurantId), itemId, menuId, itemCategoryId);
    }

    private void addMenuItem(Item item, long itemId, long menuId, long itemCategoryId) {
        item.setId(itemId);
        dbManager.addItem(item);
        dbManager.addMenuItem(new MenuItem(menuId, itemId, itemCategoryId));
    }

    private void initializeFakeCategories() {
        int itemCategoryId = 0;
        dbManager.addItemCategory(new ItemCategory(itemCategoryId, "Appetizers", "")); //0
        itemCategoryId++;
        dbManager.addItemCategory(new ItemCategory(itemCategoryId, "Salads and Soups", "")); //1
        itemCategoryId++;
        dbManager.addItemCategory(new ItemCategory(itemCategoryId, "Sandwiches", "")); //2
        itemCategoryId++;
        dbManager.addItemCategory(new ItemCategory(itemCategoryId, "Italian", "")); //3
        itemCategoryId++;
        dbManager.addItemCategory(new ItemCategory(itemCategoryId, "Main Course", "")); //4
        itemCategoryId++;
        dbManager.addItemCategory(new ItemCategory(itemCategoryId, "Sides", "")); //5
        itemCategoryId++;
        dbManager.addItemCategory(new ItemCategory(itemCategoryId, "Seafood", "")); //6
        itemCategoryId++;
        dbManager.addItemCategory(new ItemCategory(itemCategoryId, "Mexican", "")); //7
        itemCategoryId++;
        dbManager.addItemCategory(new ItemCategory(itemCategoryId, "Specialties", "")); //8
        itemCategoryId++;
        dbManager.addItemCategory(new ItemCategory(itemCategoryId, "Desserts", "")); //9
        itemCategoryId++;
        dbManager.addItemCategory(new ItemCategory(itemCategoryId, "Beverages", "")); //10
        itemCategoryId++;
        dbManager.addItemCategory(new ItemCategory(itemCategoryId, "Wine and Beer", "")); //11
        itemCategoryId++;
        dbManager.addItemCategory(new ItemCategory(itemCategoryId, "Kids Menu", "")); //12
    }
}
