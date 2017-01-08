package cat.udl.menufinder.models;

import java.io.Serializable;

public class Menu implements Serializable {

    private long id;
    private long restaurant;
    private String name;
    private String description;
    private double price;
    private double score;
    private boolean visible;

    public Menu() {
    }

    public Menu(long restaurant, String name, String description, double price) {
        this.restaurant = restaurant;
        this.name = name;
        this.description = description;
        this.price = price;
        this.score = 0;
        this.visible = true;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(long restaurant) {
        this.restaurant = restaurant;
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
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
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
                ", visible=" + visible +
                '}';
    }

    public int getVisible() {
        return visible ? 1 : 0;
    }

    public void setVisible(int visible) {
        this.visible = visible == 1;
    }
}
