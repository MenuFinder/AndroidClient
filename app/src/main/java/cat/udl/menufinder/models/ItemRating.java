package cat.udl.menufinder.models;

import java.io.Serializable;

public class ItemRating implements Serializable {

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
    
}
