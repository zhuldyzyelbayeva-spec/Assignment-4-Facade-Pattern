package com.example.orders;
public record Cart(String productId, int quantity, int unitPrice) {
    public Cart {
        if (productId == null || productId.isBlank() || quantity <= 0 || unitPrice <= 0)
            throw new IllegalArgumentException("Invalid cart");
    }
    public int total() { return Math.multiplyExact(quantity, unitPrice); }
}
