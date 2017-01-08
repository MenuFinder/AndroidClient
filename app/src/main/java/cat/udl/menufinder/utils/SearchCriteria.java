package cat.udl.menufinder.utils;

import cat.udl.menufinder.database.RestaurantContract;

public class SearchCriteria {
    private boolean first;
    private StringBuilder where;

    public SearchCriteria() {
        where = new StringBuilder();
        first = true;
    }

    public void setRestaurantName(String name) {
        addProperty(RestaurantContract.RestaurantTable.NAME, name);
    }

    public void setCity(String city) {
        addProperty(RestaurantContract.RestaurantTable.CITY, city);
    }

    private void addProperty(String key, String value) {
        if (!first) where.append(" AND ");
        else first = false;
        where.append(key).append(" = \"").append(value).append("\"");
    }

    public String getWhere() {
        return where.toString();
    }
}
