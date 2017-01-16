package cat.udl.menufinder.utils;

import cat.udl.menufinder.database.RestaurantContract;

public class SearchCriteria {
    private boolean first;
    private StringBuilder where;
    private boolean distance;

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

    public boolean getDistance() {
        return distance;
    }

    public void setDistance(boolean distance) {
        this.distance = distance;
    }
}
