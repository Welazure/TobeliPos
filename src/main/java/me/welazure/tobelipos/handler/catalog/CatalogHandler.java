package me.welazure.tobelipos.handler.catalog;

import me.welazure.tobelipos.utils.comparator.*;

import java.util.*;
import java.util.stream.Collectors;

public class CatalogHandler implements Catalog {
    private Map<Item, Integer> itemsMap = new HashMap<>();

    public CatalogHandler(Map<Item, Integer> itemsMap) {
        this.itemsMap = itemsMap;
    }

    public CatalogHandler() {
        this.itemsMap = new HashMap<>();
    }


    @Override
    public Map<Item, Integer> getMap() {
        return itemsMap;
    }

    @Override
    public List<Item> getListSorted(SortBy sortBy, SortOrder sortOrder) {
        List<Item> items = new ArrayList<>(itemsMap.keySet());
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
        return new ArrayList<>(itemsMap.keySet());
    }

    @Override
    public List<Item> getListSorted(SortBy sortBy) {
        return getListSorted(sortBy, SortOrder.ASCENDING);
    }

    @Override
    public Item getItemById(String id) {
        List<Item> items = new ArrayList<>(itemsMap.keySet());
        return items.stream().filter(x -> x.getID().equals(id)).findFirst().orElse(null);
    }

    @Override
    public Item getItemByName(String name) {
        List<Item> items = new ArrayList<>(itemsMap.keySet());
        return items.stream().filter(x -> x.getName().equals(name)).findFirst().orElse(null);
    }

    @Override
    public List<Item> searchItemByName(String name) {
        List<Item> items = new ArrayList<>(itemsMap.keySet());
        return items.stream().filter(x -> x.getName().toLowerCase().contains(name.toLowerCase())).collect(Collectors.toList());
    }

    @Override
    public List<Item> searchItemByID(String id) {
        List<Item> items = new ArrayList<>(itemsMap.keySet());
        return items.stream().filter(x -> x.getID().toLowerCase().contains(id.toLowerCase())).collect(Collectors.toList());
    }
    @Override
    public List<Item> searchItemByDescription(String description) {
        List<Item> items = new ArrayList<>(itemsMap.keySet());
        return items.stream().filter(x -> x.getDescription().toLowerCase().contains(description.toLowerCase())).collect(Collectors.toList());
    }

    @Override
    public int getItemCount(Item item) {
        return itemsMap.getOrDefault(item, 0);
    }
    @Override
    public void setItemCount(Item item, int count) {
        if(!itemsMap.containsKey(item))
            return;
        itemsMap.remove(item);
        itemsMap.put(item, count);
    }

    @Override
    public void addItem(Item item, int quantity) {
        itemsMap.put(item, quantity);
    }

    @Override
    public void removeItem(Item item) {
        itemsMap.remove(item);
    }
}
