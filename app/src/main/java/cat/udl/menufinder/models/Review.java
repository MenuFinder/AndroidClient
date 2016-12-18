package cat.udl.menufinder.models;

import java.io.Serializable;

public class Review implements Serializable {

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
}
