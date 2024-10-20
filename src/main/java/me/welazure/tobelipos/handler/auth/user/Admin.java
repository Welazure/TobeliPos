package me.welazure.tobelipos.handler.auth.user;

public class Admin extends User {
    public Admin(int id, String name, String hashedPass) {
        super(id, name, hashedPass);
    }
}
