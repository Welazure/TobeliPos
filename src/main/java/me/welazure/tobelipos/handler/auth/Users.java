package me.welazure.tobelipos.handler.auth;

import me.welazure.tobelipos.handler.auth.user.Admin;
import me.welazure.tobelipos.handler.auth.user.Customer;
import me.welazure.tobelipos.handler.auth.user.User;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public interface Users {

    boolean exists(String name);
    @Nullable
    User getUser(String name);
    @Nullable
    User getUser(int id);
    boolean createUser(boolean admin,String username, String password);

}
