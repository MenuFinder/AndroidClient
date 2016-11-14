package cat.udl.menufinder.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Menu implements Serializable {

    private long id;
    private String name;
    private String description;
    private double price;
    private double score;
    private boolean isVisible;
    private long restaurant_id;
    private Map<ItemCategory, List<Item>> itemsCategory;

    public Menu(String name, String description, double price, boolean isVisible) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.isVisible = isVisible;
        itemsCategory = new HashMap<ItemCategory, List<Item>>();
    }

    public long getId() {

        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public long getRestaurant_id() {
        return restaurant_id;
    }

    public void setRestaurant_id(long restaurant_id) {
        this.restaurant_id = restaurant_id;
    }

    public void addMenuItem(MenuItem menuItem) {
        if (!itemsCategory.containsKey(menuItem.getItemCategory())) {
            itemsCategory.put(menuItem.getItemCategory(), new ArrayList<Item>());
        }
        itemsCategory.get(menuItem.getItemCategory()).add(menuItem.getItem());
    }

    public List<Item> getItemsByCategory(ItemCategory itemCategory) {
        return itemsCategory.get(itemCategory);
    }

    public Map<ItemCategory, List<Item>> getItemsCategory() {
        return itemsCategory;
    }

    public Set<ItemCategory> getItemCategories() {
        return itemsCategory.keySet();
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", score=" + score +
                ", isVisible=" + isVisible +
                ", restaurant_id=" + restaurant_id +
                '}';
    }

}
