package cat.udl.menufinder.models;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

import java.io.Serializable;
import java.util.Hashtable;

public class Restaurant implements Serializable, KvmSerializable {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Restaurant that = (Restaurant) o;

        if (id != that.id) return false;
        if (Double.compare(that.score, score) != 0) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (cif != null ? !cif.equals(that.cif) : that.cif != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (city != null ? !city.equals(that.city) : that.city != null) return false;
        if (postalCode != null ? !postalCode.equals(that.postalCode) : that.postalCode != null)
            return false;
        if (state != null ? !state.equals(that.state) : that.state != null) return false;
        if (country != null ? !country.equals(that.country) : that.country != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;
        return account != null ? account.equals(that.account) : that.account == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (cif != null ? cif.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (postalCode != null ? postalCode.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (account != null ? account.hashCode() : 0);
        temp = Double.doubleToLongBits(score);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    public String getAddressWithCity() {
        return address + "," + city;
    }

    @Override
    public Object getProperty(int i) {
        switch (i) {
            case 0:
                return id;
            case 1:
                return name;
            case 2:
                return cif;
            case 3:
                return address;
            case 4:
                return city;
            case 5:
                return postalCode;
            case 6:
                return state;
            case 7:
                return country;
            case 8:
                return email;
            case 9:
                return phone;
            case 10:
                return account;
            case 11:
                return String.valueOf(score);
        }
        return null;
    }

    @Override
    public int getPropertyCount() {
        return 12;
    }

    @Override
    public void setProperty(int i, Object o) {
        switch (i) {
            case 0:
                id = (long) Double.parseDouble((String) o);
                break;
            case 1:
                name = o.toString();
                break;
            case 2:
                cif = o.toString();
                break;
            case 3:
                address = o.toString();
                break;
            case 4:
                city = o.toString();
                break;
            case 5:
                postalCode = o.toString();
                break;
            case 6:
                state = o.toString();
                break;
            case 7:
                country = o.toString();
                break;
            case 8:
                email = o.toString();
                break;
            case 9:
                phone = o.toString();
                break;
            case 10:
                account = o.toString();
                break;
            case 11:
                score = Double.parseDouble((String) o);
                break;
        }
    }

    @Override
    public void getPropertyInfo(int i, Hashtable hashtable, PropertyInfo info) {
        switch (i) {
            case 0:
                info.type = PropertyInfo.LONG_CLASS;
                info.name = "id";
                break;
            case 1:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "name";
                break;
            case 2:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "cif";
                break;
            case 3:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "address";
                break;
            case 4:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "city";
                break;
            case 5:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "postalCode";
                break;
            case 6:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "state";
                break;
            case 7:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "country";
                break;
            case 8:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "email";
                break;
            case 9:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "phone";
                break;
            case 10:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "account";
                break;
            case 11:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "score";
                break;
        }
    }
}
