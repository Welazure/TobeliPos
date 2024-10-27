package me.welazure.tobelipos.utils.comparator;

import me.welazure.tobelipos.handler.catalog.Item;

import java.util.Comparator;

public class ItemIDComparator implements Comparator<Item> {
    @Override
    public int compare(Item o1, Item o2) {
        return o1.getID().compareToIgnoreCase(o2.getID());
    }
}
