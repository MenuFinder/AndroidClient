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
    private String account;
    private double score;

    public Restaurant() {
    }

    public Restaurant(String name, String cif, String address, String city, String postalCode,
                      String state, String country, String email, String phone, String account) {
        this.name = name;
        this.cif = cif;
        this.address = address;
        this.city = city;
        this.postalCode = postalCode;
        this.state = state;
        this.country = country;
        this.email = email;
        this.phone = phone;
        this.account = account;
        this.score = 0;
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

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
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
                ", account='" + account + '\'' +
                ", score=" + score +
                '}';
    }

    public List<Menu> getVisibleMenus() {
        return new ArrayList<>();
    }

    public void addCategory(Category category) {

    }

    public void addMenu(Menu menu) {

    }

    public String getAddressWithCity() {
        return address + "," + city;
    }

}
