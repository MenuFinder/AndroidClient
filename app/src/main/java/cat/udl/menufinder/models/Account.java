package cat.udl.menufinder.models;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

import java.io.Serializable;
import java.util.Hashtable;

import cat.udl.menufinder.enums.UserType;

public class Account implements Serializable, KvmSerializable {

    private String id;
    private String password;
    private String type;
    private String token;
    private String email;

    public Account() {
    }

    public Account(String id, String password, String type, String email) {
        this.id = id;
        this.password = password;
        this.type = type;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type.getText();
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Account{" +
                "email='" + email + '\'' +
                ", token='" + token + '\'' +
                ", type=" + type +
                ", password='" + password + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

    @Override
    public Object getProperty(int i) {
        switch (i) {
            case 1:
                return id;
            case 2:
                return password;
            case 3:
                return type;
            case 4:
                return token;
            case 5:
                return email;
            default:
                return null;
        }
    }

    @Override
    public int getPropertyCount() {
        return 5;
    }

    @Override
    public void setProperty(int i, Object o) {
        switch (i) {
            case 0:
                id = o.toString();
                break;
            case 1:
                password = o.toString();
                break;
            case 2:
                type = o.toString();
                break;
            case 3:
                token = o.toString();
                break;
            case 4:
                email = o.toString();
                break;
        }
    }

    @Override
    public void getPropertyInfo(int i, Hashtable hashtable, PropertyInfo propertyInfo) {
        switch (i) {
            case 0:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "id";
                break;
            case 1:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "password";
                break;
            case 2:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "type";
                break;
            case 3:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "token";
                break;
            case 4:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "email";
                break;
        }
    }
}
