package cat.udl.menufinder.models;

import java.io.Serializable;

public class AccountSubscription implements Serializable {

    private String account;
    private long restaurant;

    public AccountSubscription() {
    }

    public AccountSubscription(String account, long restaurant) {
        this.account = account;
        this.restaurant = restaurant;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public long getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(long restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public String toString() {
        return "AccountSubscription{" +
                "account='" + account + '\'' +
                ", restaurant=" + restaurant +
                '}';
    }

}
