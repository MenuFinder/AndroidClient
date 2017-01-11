package cat.udl.menufinder.models;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

import java.io.Serializable;
import java.util.Hashtable;

public class ItemRating implements Serializable, KvmSerializable {

    private long id;
    private double score;
    private String account;
    private long item;

    public ItemRating() {
    }

    public ItemRating(double score, long item, String account) {
        this.score = score;
        this.item = item;
        this.account = account;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public long getItem() {
        return item;
    }

    public void setItem(long item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return "ItemRating{" +
                "id=" + id +
                ", score=" + score +
                ", account='" + account + '\'' +
                ", item=" + item +
                '}';
    }

    @Override
    public Object getProperty(int i) {
        switch (i) {
            case 0:
                return id;
            case 1:
                return String.valueOf(score);
            case 2:
                return account;
            case 3:
                return item;
            default:
                return null;
        }
    }

    @Override
    public int getPropertyCount() {
        return 4;
    }

    @Override
    public void setProperty(int i, Object o) {
        switch (i) {
            case 0:
                id = Long.parseLong(o.toString());
                break;
            case 1:
                score = Double.parseDouble(o.toString());
                break;
            case 2:
                account = o.toString();
                break;
            case 3:
                item = Long.parseLong(o.toString());
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
                propertyInfo.name = "score";
                break;
            case 2:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "account";
                break;
            case 3:
                propertyInfo.type = PropertyInfo.LONG_CLASS;
                propertyInfo.name = "item";
                break;
        }
    }
}
