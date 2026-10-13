package com.example.orders;
public record Customer(String name, String email, boolean paymentApproved) {
    public Customer {
        if (name == null || name.isBlank() || email == null || email.isBlank())
            throw new IllegalArgumentException("Customer name and email are required");
    }
}
