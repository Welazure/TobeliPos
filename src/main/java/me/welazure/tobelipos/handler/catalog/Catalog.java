package me.welazure.tobelipos.handler.catalog;

import me.welazure.tobelipos.utils.comparator.SortBy;
import me.welazure.tobelipos.utils.comparator.SortOrder;

import java.util.List;
import java.util.Map;

public interface Catalog {

    Map<Item, Integer> getMap();
    List<Item> getListSorted(SortBy sortBy, SortOrder sortOrder);
    List<Item> getList();
    List<Item> getListSorted(SortBy sortBy);
    Item getItemById(String id);
    Item getItemByName(String name);
    List<Item> searchItemByName(String name);
    List<Item> searchItemByID(String id);
    List<Item> searchItemByDescription(String description);
    int getItemCount(Item item);
    void setItemCount(Item item, int count);
    void addItem(Item item, int quantity);
    void removeItem(Item item);
}
