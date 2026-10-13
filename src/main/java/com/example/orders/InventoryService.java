package com.example.orders;
import java.util.HashMap;
import java.util.Map;
public class InventoryService {
    private final Map<String,Integer> stock = new HashMap<>(Map.of("BOOK",5));
    public void reserve(Cart cart) {
        int available = available(cart.productId());
        if (available < cart.quantity()) throw new IllegalStateException("Insufficient stock");
        stock.put(cart.productId(), available - cart.quantity());
    }
    public void release(Cart cart) {
        stock.merge(cart.productId(), cart.quantity(), Integer::sum);
    }
    public int available(String productId) { return stock.getOrDefault(productId,0); }
}
