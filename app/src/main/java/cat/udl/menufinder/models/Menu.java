package cat.udl.menufinder.models;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

import java.io.Serializable;
import java.util.Hashtable;

public class Menu implements Serializable, KvmSerializable {

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

    @Override
    public Object getProperty(int i) {
        switch (i) {
            case 0:
                return id;
            case 1:
                return restaurant;
            case 2:
                return name;
            case 3:
                return description;
            case 4:
                return String.valueOf(price);
            case 5:
                return String.valueOf(score);
            case 6:
                return visible;
            default:
                return null;
        }
    }

    @Override
    public int getPropertyCount() {
        return 7;
    }

    @Override
    public void setProperty(int i, Object o) {
        switch (i) {
            case 0:
                id = Long.parseLong(o.toString());
                break;
            case 1:
                restaurant = Long.parseLong(o.toString());
                break;
            case 2:
                name = o.toString();
                break;
            case 3:
                description = o.toString();
                break;
            case 4:
                price = Double.parseDouble(o.toString());
                break;
            case 5:
                score = Double.parseDouble(o.toString());
                break;
            case 6:
                visible = Boolean.parseBoolean(o.toString());
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
                propertyInfo.type = PropertyInfo.LONG_CLASS;
                propertyInfo.name = "restaurant";
                break;
            case 2:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "name";
                break;
            case 3:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "description";
                break;
            case 4:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "price";
                break;
            case 5:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "score";
                break;
            case 6:
                propertyInfo.type = PropertyInfo.BOOLEAN_CLASS;
                propertyInfo.name = "visible";
                break;
        }
    }
}
