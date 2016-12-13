package cat.udl.menufinder.models;

import java.io.Serializable;

public class Review implements Serializable {

    private long id;
    private String review;

    public Review(String review) {
        this.review = review;
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

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", review='" + review + '\'' +
                '}';
    }

}
