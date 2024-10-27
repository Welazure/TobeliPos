package me.welazure.tobelipos.handler;


import me.welazure.tobelipos.handler.auth.UserHandler;
import me.welazure.tobelipos.handler.auth.user.User;
import me.welazure.tobelipos.handler.auth.Authenticator;
import me.welazure.tobelipos.handler.catalog.Catalog;
import me.welazure.tobelipos.handler.catalog.CatalogHandler;
import me.welazure.tobelipos.handler.catalog.Item;
import me.welazure.tobelipos.handler.consoleinterface.MenuHandler;
import me.welazure.tobelipos.handler.auth.Users;
import me.welazure.tobelipos.utils.Reader;


public class Delegator {
    private static Delegator instance;

    private Users users;
    private Authenticator authenticator;
    private Reader reader;
    private MenuHandler menuHandler;
    private Catalog catalog;


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

    public void init() {
        reader = new Reader(System.in);
        authenticator = new Authenticator();
        users = new UserHandler();
        catalog = new CatalogHandler();

        catalog.addItem(new Item("asdfasdf", "asdfasdf", "asdfasdf", 10000.0d, 100));
        catalog.addItem(new Item("asdfasdf1", "asdfasdf1", "asdfasdf1", 10000.0d, 100));
        catalog.addItem(new Item("asdfasdf2", "asdfasdf2", "asdfasdf2", 10000.0d, 100));
        catalog.addItem(new Item("asdfasdf3", "asdfasdf3", "asdfasdf3", 10000.0d, 100));

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
