package me.welazure.tobelipos.handler.catalog;

import java.util.ArrayList;
import java.util.List;

public interface Catalog {

    List<Item> getListSorted(SortBy sortBy, SortOrder sortOrder);
    List<Item> getList();
    List<Item> getListSorted(SortBy sortBy);
    Item getItemById(String id);
    Item getItemByName(String name);
    List<Item> searchItemByName(String name);
    List<Item> searchItemByID(String id);
    void addItem(Item item);
    void removeItem(Item item);

    enum SortBy {
        NAME,
        ID,
        PRICE
    }
    enum SortOrder {
        DESCENDING,
        ASCENDING
    }
}
