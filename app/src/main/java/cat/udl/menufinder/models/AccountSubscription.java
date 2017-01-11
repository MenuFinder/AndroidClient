package cat.udl.menufinder.models;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

import java.io.Serializable;
import java.util.Hashtable;

public class AccountSubscription implements Serializable, KvmSerializable {

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

    @Override
    public Object getProperty(int i) {
        switch (i) {
            case 0:
                return account;
            case 1:
                return restaurant;
            default:
                return null;
        }
    }

    @Override
    public int getPropertyCount() {
        return 2;
    }

    @Override
    public void setProperty(int i, Object o) {
        switch (i) {
            case 0:
                account = o.toString();
                break;
            case 1:
                restaurant = Long.parseLong(o.toString());
                break;
        }
    }

    @Override
    public void getPropertyInfo(int i, Hashtable hashtable, PropertyInfo propertyInfo) {
        switch (i) {
            case 0:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "account";
                break;
            case 1:
                propertyInfo.type = PropertyInfo.LONG_CLASS;
                propertyInfo.name = "restaurant";
                break;
        }
    }
}
