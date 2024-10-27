package me.welazure.tobelipos.handler.salesorder;

import me.welazure.tobelipos.handler.catalog.Item;
import me.welazure.tobelipos.utils.comparator.SortBy;
import me.welazure.tobelipos.utils.comparator.SortOrder;

import java.util.List;

public interface SalesOrder {

    List<Item> getList();

    List<Item> getListSorted(SortBy sortBy);
    List<Item> getListSorted(SortBy sortBy, SortOrder sortOrder);

    Item getSalesByID(String id);
    Item getItemByName(String name);

    List<Item> searchSalesByID(String id);
    List<Item> searchItemByID(String id);

    void addItem(Item item);
    void removeItem(Item item);

}
