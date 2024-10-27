package me.welazure.tobelipos.utils.comparator;

import me.welazure.tobelipos.handler.catalog.Item;

import java.util.Comparator;

public class ItemPriceComparator implements Comparator<Item> {
    public int compare(Item i1, Item i2) {
        return Double.compare(i1.getPrice(), i2.getPrice());
    }
}
