package me.welazure.tobelipos.handler.auth;

import me.welazure.tobelipos.handler.auth.user.User;
import org.jetbrains.annotations.Nullable;

public interface Users {

    boolean exists(String name);
    @Nullable
    User getUser(String name);
    @Nullable
    User getUser(int id);
    boolean createUser(boolean admin,String username, String password);

}
