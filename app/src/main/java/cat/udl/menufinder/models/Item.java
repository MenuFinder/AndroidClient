package cat.udl.menufinder.models;

import java.io.Serializable;

public class Item implements Serializable {

    private long id;
    private String name;
    private String description;
    private double price;
    private double score;
    private long restaurant;

    public Item() {
    }

    public Item(String name, String description, double price, long restaurant) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.score = 0;
        this.restaurant = restaurant;
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

    public long getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(long restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public String toString() {
        return "Item{" +
                "restaurant=" + restaurant +
                ", score=" + score +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
