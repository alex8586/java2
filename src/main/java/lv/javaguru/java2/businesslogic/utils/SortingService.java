package lv.javaguru.java2.businesslogic.utils;

import org.apache.commons.collections.map.ListOrderedMap;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class SortingService<T> {
    protected Map<String, String> keyToKey = new ListOrderedMap();
    protected Map<String, Comparator<T>> comparators = new HashMap<>();
    protected String defaultSortingStrategy;

    public void sort(String key, List<T> list) {
        if (key == null)
            key = defaultSortingStrategy;
        list.sort(comparators.get(key));
    }

    public void sortReversed(String key, List<T> list) {
        if (key == null)
            key = defaultSortingStrategy;
        list.sort(comparators.get(key).reversed());
    }

    public Map<String, String> sortingStrategies() {
        return keyToKey;
    }

    public String getDefaultSortingStrategy() {
        return defaultSortingStrategy;
    }
}
