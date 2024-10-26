package me.welazure.tobelipos.handler.catalog;

public class Item {
    private String name;
    private String ID;
    private String description;
    private double price;
    private int quantity;

    public Item(String name, String ID, String description, double price, int quantity) {
        this.name = name;
        this.ID = ID;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getID() {
        return ID;
    }
    public void setID(String ID) {
        this.ID = ID;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return String.format("Item %s, ID: %s, Description: %s, Price: %.2f, Stock: %s, ", name, ID, description, price, quantity);
    }
}
