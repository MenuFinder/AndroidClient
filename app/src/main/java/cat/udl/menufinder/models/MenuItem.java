package cat.udl.menufinder.models;

import java.io.Serializable;

public class MenuItem implements Serializable {

    private long menu;
    private long item;
    private long itemCategory;

    public MenuItem() {
    }

    public MenuItem(long menu, long item, long itemCategory) {
        this.menu = menu;
        this.item = item;
        this.itemCategory = itemCategory;
    }

    public long getMenu() {
        return menu;
    }

    public void setMenu(long menu) {
        this.menu = menu;
    }

    public long getItem() {
        return item;
    }

    public void setItem(long item) {
        this.item = item;
    }

    public long getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(long itemCategory) {
        this.itemCategory = itemCategory;
    }

    @Override
    public String toString() {
        return "MenuItem{" +
                "menu=" + menu +
                ", item=" + item +
                ", itemCategory=" + itemCategory +
                '}';
    }
}
