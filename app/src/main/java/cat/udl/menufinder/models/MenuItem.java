package cat.udl.menufinder.models;

import java.io.Serializable;

public class MenuItem implements Serializable {

    private Item item;
    private ItemCategory itemCategory;

    public MenuItem(Item item, ItemCategory itemCategory) {
        this.item = item;
        this.itemCategory = itemCategory;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public ItemCategory getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(ItemCategory itemCategory) {
        this.itemCategory = itemCategory;
    }

}
