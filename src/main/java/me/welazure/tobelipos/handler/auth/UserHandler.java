package me.welazure.tobelipos.handler.auth;

import me.welazure.tobelipos.handler.auth.user.Admin;
import me.welazure.tobelipos.handler.auth.user.Customer;
import me.welazure.tobelipos.handler.auth.user.User;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class UserHandler implements Users {
    private final List<User> users;
    public UserHandler() {
        users = new ArrayList<User>();
    }

    public UserHandler(List<User> users) {
        this.users = users;
    }

    public boolean exists(String name) {
        return users.stream().anyMatch(user -> user.getName().equals(name));
    }

    @Nullable
    public User getUser(String name) {
        return users.stream().filter(user -> user.getName().equals(name)).findFirst().orElse(null);
    }

    @Nullable
    public User getUser(int id) {
        return users.stream().filter(user -> user.getId() == id).findFirst().orElse(null);
    }

    public boolean createUser(boolean admin, String username, String password) {
        User user;

        if(users.stream().anyMatch(x ->
                x.getName().equalsIgnoreCase(username)))
            return false;
        password = Authenticator.generateHash(password);
        if(admin) {
            user = new Admin(users.size(), username, password);
            users.add(user);
        } else {
            user = new Customer(users.size(), username, password);
            users.add(user);
        }
        return true;
    }

}
