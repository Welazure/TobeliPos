package me.welazure.tobelipos.handler.salesorder;

import me.welazure.tobelipos.handler.auth.user.User;
import me.welazure.tobelipos.handler.catalog.Item;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private String ID;
    private User customer;
    private double total;
    private String status;
    private LocalDateTime orderDate;
    private List<Item> items;

    public Order(String ID, User customer, double total, String status, LocalDateTime orderDate) {
        this(ID, customer, total, status, orderDate, new ArrayList<>());
    }
    public Order(String ID, User customer, double total, String status, LocalDateTime orderDate, List<Item> items) {
        this.ID = ID;
        this.customer = customer;
        this.total = total;
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
    public User getCustomer() {
        return customer;
    }
    public void setCustomer(User customer) {
        this.customer = customer;
    }
    public double getTotal() {
        return total;
    }
    public void setTotal(double total) {
        this.total = total;
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
    public List<Item> getItems() {
        return items;
    }
    public void setItems(List<Item> items) {
        this.items = items;
    }

}
