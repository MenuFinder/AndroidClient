package cat.udl.menufinder.models;

import java.util.ArrayList;
import java.util.List;

import cat.udl.menufinder.enums.UserType;

public class User {
    private UserType userType;
    private String username;
    private String password;
    private String email;
    private List<Restaurant> restaurants;

    public User(UserType userType, String username, String password, String email) {
        this.userType = userType;
        this.username = username;
        this.password = password;
        this.email = email;
        restaurants = new ArrayList<Restaurant>();
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public void addRestaurant(Restaurant restaurant) {
        restaurants.add(restaurant);
    }

}
