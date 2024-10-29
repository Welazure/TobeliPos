package me.welazure.tobelipos.handler;


import me.welazure.tobelipos.handler.auth.Authenticator;
import me.welazure.tobelipos.handler.auth.UserHandler;
import me.welazure.tobelipos.handler.auth.Users;
import me.welazure.tobelipos.handler.auth.user.User;
import me.welazure.tobelipos.handler.catalog.Catalog;
import me.welazure.tobelipos.handler.catalog.CatalogHandler;
import me.welazure.tobelipos.handler.catalog.Item;
import me.welazure.tobelipos.handler.consoleinterface.MenuHandler;
import me.welazure.tobelipos.handler.salesorder.Order;
import me.welazure.tobelipos.handler.salesorder.SalesOrder;
import me.welazure.tobelipos.handler.salesorder.SalesOrderHandler;
import me.welazure.tobelipos.utils.Reader;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


public class Delegator {
    private static Delegator instance;

    private Users users;
    private Authenticator authenticator;
    private Reader reader;
    private MenuHandler menuHandler;
    private Catalog catalog;
    private SalesOrder salesOrder;


    private User currentUser;

    private Delegator() {
        init();
    }

    public static Delegator getInstance() {
        if(instance == null) {
            instance = new Delegator();
        }
        return instance;
    }
    public Catalog getCatalog() {
        return catalog;
    }
    public SalesOrder getSalesOrder() {
        return salesOrder;
    }

    public void init() {
        reader = new Reader(System.in);
        authenticator = new Authenticator();
        users = new UserHandler();
        catalog = new CatalogHandler();
        salesOrder = new SalesOrderHandler();


        // Sample data for testing!
        catalog.addItem(new Item("Garam", "S001", "Garam Dapur", 10000.0d, "Bungkus"), 100);
        catalog.addItem(new Item("Merica", "S002", "Merica Putih", 10000.0d, "Bungkus"), 100);
        catalog.addItem(new Item("Cengkeh", "S003", "Cengkeh", 10000.0d, "Bungkus"), 100);
        catalog.addItem(new Item("Syrup", "D001", "Sirup", 10000.0d, "Botol"), 100);

        Map<Item, Integer> items = new HashMap<Item, Integer>();
        items.put((Item) catalog.getMap().keySet().toArray()[0], 1);
        items.put((Item) catalog.getMap().keySet().toArray()[1], 2);
        salesOrder.addOrder(new Order("OD001", "James", "Shipping", LocalDateTime.now(), items));
        Map<Item, Integer> items2 = new HashMap<>();
        items2.put((Item) catalog.getMap().keySet().toArray()[1], 1);
        items2.put((Item) catalog.getMap().keySet().toArray()[2], 2);
        salesOrder.addOrder(new Order("OD002", "Justin", "Shipping", LocalDateTime.now(), items2));

        users.createUser(true, "root", "root");
        menuHandler = new MenuHandler(this);
    }

    public Users getUsers() {
        return users;
    }

    public Authenticator getAuthenticator() {
        return authenticator;
    }

    public Reader getReader() {
        return reader;
    }
    public MenuHandler getMenuHandler() {
        return menuHandler;
    }

    public User getCurrentUser() {
        if(currentUser == null) return null;
        if(!getAuthenticator().isLoggedIn(currentUser)) {
            currentUser = null;
            getMenuHandler().mainMenu();
            return null;
        }
        return currentUser;
    }

    public boolean setCurrentUser(User user) {
        if(!getAuthenticator().isLoggedIn(user))
            return false;

        currentUser = user;
        return true;
    }

    public void exit() {
        System.exit(0);
    }
}
