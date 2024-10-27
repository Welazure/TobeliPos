package me.welazure.tobelipos.handler.salesorder;

import me.welazure.tobelipos.handler.catalog.Item;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Order {
    private String ID;
    private String customer;
    private String status;
    private LocalDateTime orderDate;
    private Map<Item, Integer> items;

    public Order(String ID, String customer, String status, LocalDateTime orderDate) {
        this(ID, customer, status, orderDate, new HashMap<>());
    }
    public Order(String ID, String customer, String status, LocalDateTime orderDate, Map<Item, Integer> items) {
        this.ID = ID;
        this.customer = customer;
        this.status = status;
        this.orderDate = orderDate;
        this.items = items;
    }

    public String getID() {
        return ID;
    }
    public void setID(String ID) {
        this.ID = ID;
    }
    public String getCustomer() {
        return customer;
    }
    public void setCustomer(String customer) {
        this.customer = customer;
    }
    public double getTotal() {
        double sum = 0;
        for(Map.Entry<Item, Integer> entry : items.entrySet()) {
            sum += entry.getKey().getPrice() * entry.getValue();
        }
        return sum;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public LocalDateTime getOrderDate() {
        return orderDate;
    }
    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }
    public Map<Item, Integer> getItems() {
        return items;
    }
    public void additem(Item item, int quantity) {
        if(items.containsKey(item)) {
            items.put(item, items.get(item) + quantity);
            return;
        }
        items.put(item, quantity);
    }
    public void setItems(Map<Item, Integer> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("Order #%s, Customer: %s, Total: %.2f, Date: %s, Status: %s\n", ID, customer, getTotal(), orderDate, status));
        builder.append("Items ordered: \n");
        getItems().forEach((x, y) -> {
            builder.append("\t");
            builder.append(x.toString(y));
            builder.append("\n");
        });

        return builder.toString();
    }
}
