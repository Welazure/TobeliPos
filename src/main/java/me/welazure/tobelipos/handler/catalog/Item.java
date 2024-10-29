package me.welazure.tobelipos.handler.catalog;

public class Item {
    private final String CURRENCY = "Rp. ";
    private String name;
    private String ID;
    private String description;
    private double price;
    private String unit;

    public Item(String name, String ID, String description, double price, String unit) {
        this.name = name;
        this.ID = ID;
        this.description = description;
        this.price = price;
        this.unit = unit;
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
    public String getUnit() {
        return unit;
    }
    public void setUnit(String unit) {
        this.unit = unit;
    }
    @Override
    public String toString() {
        return String.format("Item %s, ID: %s, Description: %s, Price: %s%.2f", name, ID, description, CURRENCY, price);
    }
    public String toString(int quantity) {
        return this + String.format(", Quantity: %d %s", quantity, unit);
    }
}
