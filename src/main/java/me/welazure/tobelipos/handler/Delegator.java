package me.welazure.tobelipos.handler;


import me.welazure.tobelipos.handler.auth.UserHandler;
import me.welazure.tobelipos.handler.auth.user.User;
import me.welazure.tobelipos.handler.auth.Authenticator;
import me.welazure.tobelipos.handler.consoleinterface.MenuHandler;
import me.welazure.tobelipos.handler.auth.Users;
import me.welazure.tobelipos.utils.Reader;
import org.jetbrains.annotations.NotNull;


public class Delegator {
    private static Delegator instance;

    private Users users;
    private Authenticator authenticator;
    private Reader reader;
    private MenuHandler menuHandler;


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

    public void init() {
        reader = new Reader(System.in);
        authenticator = new Authenticator();
        users = new UserHandler();
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
}
