package me.welazure.tobelipos.handler.auth.user;



public class Customer extends User {
    private me.welazure.tobelipos.handler.auth.user.Cart cart;
    public Customer(int id, String name, String hashedPass) {
        super(id, name, hashedPass);
    }
    public Cart getCart() {
        return cart;
    }
    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
