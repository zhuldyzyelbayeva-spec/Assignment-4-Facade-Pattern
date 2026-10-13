package com.example.orders;
import java.util.HashSet;
import java.util.Set;
public class PaymentProcessor {
    private int nextId = 1;
    private final Set<String> paid = new HashSet<>();
    public String charge(Customer customer, int amount) {
        if (amount <= 0) throw new IllegalArgumentException("Amount must be positive");
        if (!customer.paymentApproved()) throw new IllegalStateException("Payment declined");
        String id = "PAY-" + nextId++;
        paid.add(id);
        return id;
    }
    public void refund(String paymentId) {
        if (!paid.remove(paymentId)) throw new IllegalStateException("Unknown payment");
    }
    public boolean isPaid(String paymentId) { return paid.contains(paymentId); }
}
