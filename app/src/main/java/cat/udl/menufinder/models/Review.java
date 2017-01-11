package cat.udl.menufinder.models;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

import java.io.Serializable;
import java.util.Hashtable;

public class Review implements Serializable, KvmSerializable {

    private long id;
    private String review;
    private String parentType;
    private long parentId;
    private String account;

    public Review() {
    }

    public Review(String review, String parentType, long parentId, String account) {
        this.review = review;
        this.parentType = parentType;
        this.parentId = parentId;
        this.account = account;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getParentType() {
        return parentType;
    }

    public void setParentType(String parentType) {
        this.parentType = parentType;
    }

    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", review='" + review + '\'' +
                ", parentType='" + parentType + '\'' +
                ", parentId=" + parentId +
                ", account='" + account + '\'' +
                '}';
    }

    @Override
    public Object getProperty(int i) {
        switch (i) {
            case 0:
                return id;
            case 1:
                return review;
            case 2:
                return parentType;
            case 3:
                return parentId;
            case 4:
                return account;
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
                id = Long.parseLong(o.toString());
                break;
            case 1:
                review = o.toString();
                break;
            case 2:
                parentType = o.toString();
                break;
            case 3:
                parentId = Long.parseLong(o.toString());
                break;
            case 4:
                account = o.toString();
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
                propertyInfo.name = "review";
                break;
            case 2:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "parentType";
                break;
            case 3:
                propertyInfo.type = PropertyInfo.LONG_CLASS;
                propertyInfo.name = "parentId";
                break;
            case 4:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "account";
                break;
        }
    }
}
