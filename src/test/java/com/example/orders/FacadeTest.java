package com.example.orders;
public class FacadeTest {
    public static void main(String[] args) {
        OrderFacade orders = new OrderFacade();
        Cart cart = new Cart("BOOK", 2, 1000);
        check(orders.availableStock("BOOK") == 5, "initial stock");
        check(orders.placeOrder(cart, new Customer("Dana", "d@x.com", true)).equals("ORDER-1"), "success");
        check(orders.availableStock("BOOK") == 3, "stock after success");
        failure(orders, cart, new Customer("Alex", "a@x.com", false));
        failure(orders, cart, new Customer("ShippingFail", "s@x.com", true));
        failure(orders, cart, new Customer("NotifyFail", "n@x.com", true));
        check(orders.availableStock("BOOK") == 3, "stock after failures");
        check(orders.placeOrder(new Cart("BOOK", 3, 1000), new Customer("Eve", "e@x.com", true)).equals("ORDER-2"), "sequence after rollback");
        check(orders.availableStock("BOOK") == 0, "remaining stock");
        System.out.println("ALL TESTS PASSED");
    }
    static void failure(OrderFacade orders, Cart cart, Customer customer) {
        try { orders.placeOrder(cart, customer); throw new AssertionError("Expected failure"); }
        catch (IllegalStateException expected) { check(orders.availableStock("BOOK") == 3, "rollback " + customer.name()); }
    }
    static void check(boolean ok, String label) { if (!ok) throw new AssertionError(label); }
}
