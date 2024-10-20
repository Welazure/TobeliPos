package me.welazure.tobelipos.handler.catalog;

import java.util.Comparator;

public class ItemNameComparator implements Comparator<Item> {
    public int compare(Item i1, Item i2) {
        return i1.getName().compareToIgnoreCase(i2.getName());
    }
}
