package cat.udl.menufinder.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Menu implements Serializable {

    private long id;
    private long restaurant;
    private String name;
    private String description;
    private double price;
    private double score;
    private boolean isVisible;

    public Menu() {
    }

    public Menu(long restaurant, String name, String description, double price) {
        this.restaurant = restaurant;
        this.name = name;
        this.description = description;
        this.price = price;
        this.score = 0;
        this.isVisible = true;
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

    public boolean getIsVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public int isVisible() {
        return isVisible ? 1 : 0;
    }

    public void setVisible(int visible) {
        isVisible = visible == 0 ? false : true;
    }

    public long getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(long restaurant) {
        this.restaurant = restaurant;
    }

    public void addMenuItem(MenuItem menuItem) {

    }

    public List<Item> getItemsByCategory(ItemCategory itemCategory) {
        return new ArrayList<>();
    }

    public Map<ItemCategory, List<Item>> getItemsCategory() {
        return new HashMap<>();
    }

    public Set<ItemCategory> getItemCategories() {
        return new HashSet<>();
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", restaurant=" + restaurant +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", score=" + score +
                ", isVisible=" + isVisible +
                '}';
    }
}
