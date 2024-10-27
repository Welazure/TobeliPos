package me.welazure.tobelipos.handler.auth.user;


public abstract class User {
    private final String name;
    private final int id;

    private final String hashedPass;
    private String authToken;

    public User(final int id, final String name, final String hashedPass) {
        this.id = id;
        this.name = name;
        this.hashedPass = hashedPass;
    }

    public boolean isAdmin() {
        return this instanceof Admin;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }
    public String getHashedPass() {
        return this.hashedPass;
    }
    public String getAuthToken() {
        return this.authToken;
    }
    public void setAuthToken(final String authToken) {
        this.authToken = authToken;
    }

}
