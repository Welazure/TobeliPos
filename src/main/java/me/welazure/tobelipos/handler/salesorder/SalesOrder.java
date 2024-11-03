package me.welazure.tobelipos.handler.salesorder;

import me.welazure.tobelipos.utils.comparator.SortBy;
import me.welazure.tobelipos.utils.comparator.SortOrder;

import java.util.List;

public interface SalesOrder {

    List<Order> getList();

    List<Order> getListSorted(SortBy sortBy);
    List<Order> getListSorted(SortBy sortBy, SortOrder sortOrder);

    Order getOrderByID(String id);

    List<Order> searchOrderByID(String id);
    List<Order> searchOrderByItemID(String id);
    List<Order> searchOrderByUserName(String name);
    List<Order> searchOrderByStatus(String status);

    void addOrder(Order order);
    void removeOrder(Order order);

}
