package cat.udl.menufinder.models;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

import java.io.Serializable;
import java.util.Hashtable;

public class Item implements Serializable, KvmSerializable {

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

    @Override
    public Object getProperty(int i) {
        switch (i) {
            case 0:
                return id;
            case 1:
                return name;
            case 2:
                return description;
            case 3:
                return String.valueOf(price);
            case 4:
                return String.valueOf(score);
            case 5:
                return restaurant;
            default:
                return null;
        }
    }

    @Override
    public int getPropertyCount() {
        return 6;
    }

    @Override
    public void setProperty(int i, Object o) {
        switch (i) {
            case 0:
                id = Long.parseLong(o.toString());
                break;
            case 1:
                name = o.toString();
                break;
            case 2:
                description = o.toString();
                break;
            case 3:
                price = Double.parseDouble(o.toString());
                break;
            case 4:
                score = Double.parseDouble(o.toString());
                break;
            case 5:
                restaurant = Long.parseLong(o.toString());
                break;
        }
    }

    @Override
    public void getPropertyInfo(int i, Hashtable hashtable, PropertyInfo propertyInfo) {
        switch (i) {
            case 0:
                propertyInfo.type = PropertyInfo.LONG_CLASS;
                propertyInfo.name = "id";
                break;
            case 1:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "name";
                break;
            case 2:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "description";
                break;
            case 3:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "price";
                break;
            case 4:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "score";
                break;
            case 5:
                propertyInfo.type = PropertyInfo.LONG_CLASS;
                propertyInfo.name = "restaurant";
                break;
        }
    }
}
