package me.welazure.tobelipos.handler.catalog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CatalogHandler implements Catalog {
    private List<Item> items;

    public CatalogHandler(List<Item> items) {
        this.items = items;
    }

    public CatalogHandler() {
        this.items = new ArrayList<>();
    }


    @Override
    public List<Item> getListSorted(SortBy sortBy, SortOrder sortOrder) {
        switch (sortBy) {
            case NAME:
                if (sortOrder == SortOrder.ASCENDING) {
                    return items.stream().sorted(new ItemNameComparator()).collect(Collectors.toList());
                } else if (sortOrder == SortOrder.DESCENDING) {
                    List<Item> item = items.stream().sorted(new ItemNameComparator()).collect(Collectors.toList());
                    Collections.reverse(item);
                    return item;
                } else {
                    return new ArrayList<>();
                }
            case ID:
                if (sortOrder == SortOrder.ASCENDING) {
                    return items.stream().sorted(new ItemIDComparator()).collect(Collectors.toList());
                } else if (sortOrder == SortOrder.DESCENDING) {
                    List<Item> item = items.stream().sorted(new ItemIDComparator()).collect(Collectors.toList());
                    Collections.reverse(item);
                    return item;
                } else {
                    return new ArrayList<>();
                }
            case PRICE:
                if (sortOrder == SortOrder.ASCENDING) {
                    return items.stream().sorted(new ItemPriceComparator()).collect(Collectors.toList());
                } else if (sortOrder == SortOrder.DESCENDING) {
                    List<Item> item = items.stream().sorted(new ItemPriceComparator()).collect(Collectors.toList());
                    Collections.reverse(item);
                    return item;
                } else {
                    return new ArrayList<>();
                }
            default:
                return new ArrayList<>();
        }
    }

    @Override
    public List<Item> getList() {
        return items;
    }

    @Override
    public List<Item> getListSorted(SortBy sortBy) {
        return getListSorted(sortBy, SortOrder.ASCENDING);
    }

    @Override
    public Item getItemById(String id) {
        return items.stream().filter(x -> x.getID().equals(id)).findFirst().orElse(null);
    }

    @Override
    public Item getItemByName(String name) {
        return items.stream().filter(x -> x.getName().equals(name)).findFirst().orElse(null);
    }

    @Override
    public List<Item> searchItemByName(String name) {
        return items.stream().filter(x -> x.getName().equals(name)).collect(Collectors.toList());
    }

    @Override
    public List<Item> searchItemByID(String id) {
        return items.stream().filter(x -> x.getID().equals(id)).collect(Collectors.toList());
    }

    @Override
    public void addItem(Item item) {
        items.add(item);
    }

    @Override
    public void removeItem(Item item) {
        items.remove(item);
    }
}
