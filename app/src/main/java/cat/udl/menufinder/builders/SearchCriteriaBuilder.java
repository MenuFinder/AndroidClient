package cat.udl.menufinder.builders;

import cat.udl.menufinder.utils.SearchCriteria;

public class SearchCriteriaBuilder {

    private SearchCriteria searchCriteria;

    public SearchCriteriaBuilder() {
        searchCriteria = new SearchCriteria();
    }

    public SearchCriteriaBuilder setRestaurantName(String name) {
        searchCriteria.setRestaurantName(name);
        return this;
    }

    public SearchCriteriaBuilder setCity(String city) {
        searchCriteria.setCity(city);
        return this;
    }

    public SearchCriteria build() {
        return searchCriteria;
    }

    public void setDistance(boolean distance) {
        searchCriteria.setDistance(distance);
    }
}
