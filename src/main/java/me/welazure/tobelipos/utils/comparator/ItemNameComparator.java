package me.welazure.tobelipos.utils.comparator;

import me.welazure.tobelipos.handler.catalog.Item;

import java.util.Comparator;

public class ItemNameComparator implements Comparator<Item> {
    public int compare(Item i1, Item i2) {
        return i1.getName().compareToIgnoreCase(i2.getName());
    }
}
