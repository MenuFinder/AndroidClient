package cat.udl.menufinder.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Restaurant implements Serializable {

    private long id;
    private String name;
    private String cif;
    private String address;
    private String city;
    private String postalCode;
    private String state;
    private String country;
    private String email;
    private String phone;
    private List<Menu> menus;
    private List<Category> categories;
    private double score;

    public Restaurant(String name, String cif, String address, String city, String postalCode,
                      String state, String country, String email, String phone) {
        this.name = name;
        this.cif = cif;
        this.address = address;
        this.city = city;
        this.postalCode = postalCode;
        this.state = state;
        this.country = country;
        this.email = email;
        this.phone = phone;
        menus = new ArrayList<Menu>();
        categories = new ArrayList<Category>();
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

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void addCategory(Category category) {
        categories.add(category);
    }

    public void addMenu(Menu menu) {
        menus.add(menu);
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cif='" + cif + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", score=" + score +
                '}';
    }

    public List<Menu> getVisibleMenus() {
        List<Menu> visibleMenus = new ArrayList<>();
        for (Menu menu : menus) {
            if (menu.isVisible()) visibleMenus.add(menu);
        }
        return visibleMenus;
    }

    public String getAddressWithCity() {
        return address + "," + city;
    }

}
