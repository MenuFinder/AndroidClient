package cat.udl.menufinder.models;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

import java.io.Serializable;
import java.util.Hashtable;

public class MenuItem implements Serializable, KvmSerializable {

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

    @Override
    public Object getProperty(int i) {
        switch (i) {
            case 0:
                return menu;
            case 1:
                return item;
            case 2:
                return itemCategory;
            default:
                return null;
        }
    }

    @Override
    public int getPropertyCount() {
        return 3;
    }

    @Override
    public void setProperty(int i, Object o) {
        switch (i) {
            case 0:
                menu = Long.parseLong(o.toString());
                break;
            case 1:
                item = Long.parseLong(o.toString());
                break;
            case 2:
                itemCategory = Long.parseLong(o.toString());
                break;
        }
    }

    @Override
    public void getPropertyInfo(int i, Hashtable hashtable, PropertyInfo propertyInfo) {
        switch (i) {
            case 0:
                propertyInfo.type = PropertyInfo.LONG_CLASS;
                propertyInfo.name = "menu";
                break;
            case 1:
                propertyInfo.type = PropertyInfo.LONG_CLASS;
                propertyInfo.name = "item";
                break;
            case 2:
                propertyInfo.type = PropertyInfo.LONG_CLASS;
                propertyInfo.name = "itemCategory";
                break;
        }
    }
}
