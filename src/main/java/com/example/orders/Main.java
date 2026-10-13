package com.example.orders;
public class Main {
    public static void main(String[] args) {
        OrderFacade orders = new OrderFacade();
        Cart cart = new Cart("BOOK", 2, 1000);
        attempt(orders, cart, new Customer("Dana", "dana@example.com", true));
        attempt(orders, cart, new Customer("Alex", "alex@example.com", false));
        attempt(orders, cart, new Customer("ShippingFail", "ship@example.com", true));
        attempt(orders, cart, new Customer("NotifyFail", "notify@example.com", true));
    }
    private static void attempt(OrderFacade orders, Cart cart, Customer customer) {
        try { System.out.println("Placed: " + orders.placeOrder(cart, customer)); }
        catch (IllegalStateException e) { System.out.println(e.getMessage()); }
        System.out.println("Available BOOK: " + orders.availableStock("BOOK"));
    }
}
