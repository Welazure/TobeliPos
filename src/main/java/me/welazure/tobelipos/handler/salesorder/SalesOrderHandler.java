package me.welazure.tobelipos.handler.salesorder;

import me.welazure.tobelipos.handler.catalog.Item;
import me.welazure.tobelipos.utils.comparator.SortBy;
import me.welazure.tobelipos.utils.comparator.SortOrder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SalesOrderHandler implements SalesOrder{
    private final List<Order> orders;

    public SalesOrderHandler() {
        this(new ArrayList<>());
    }
    public SalesOrderHandler(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public List<Order> getList() {
        return orders;
    }

    @Override
    public List<Order> getListSorted(SortBy sortBy) {
        return getListSorted(sortBy, SortOrder.ASCENDING);
    }

    @Override
    public List<Order> getListSorted(SortBy sortBy, SortOrder sortOrder) {
        return orders;
    }

    @Override
    public Order getOrderByID(String id) {
        return orders.stream().filter(order -> order.getID().equals(id)).findFirst().orElse(null);
    }

    @Override
    public List<Order> searchOrderByID(String id) {
        return orders.stream().filter(order -> order.getID().toLowerCase().contains(id.toLowerCase())).collect(Collectors.toList());
    }

    @Override
    public List<Order> searchOrderByItemID(String id) {
        return orders.stream().filter(order -> {
            List<Item> items = order.getItems().keySet().stream().toList();
            return items.stream().anyMatch(item -> item.getID().toLowerCase().contains(id.toLowerCase()));
        }).collect(Collectors.toList());
    }

    @Override
    public List<Order> searchOrderByUserName(String name) {
        return orders.stream().filter(order -> order.getCustomer().toLowerCase().contains(name.toLowerCase())).collect(Collectors.toList());
    }

    @Override
    public void addOrder(Order order) {
        this.orders.add(order);
    }

    @Override
    public void removeOrder(Order order) {
        this.orders.remove(order);
    }
}
