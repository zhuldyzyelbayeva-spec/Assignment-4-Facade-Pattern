package com.example.orders;
public class NotificationService {
    public void sendConfirmation(Customer customer, String orderId) {
        if (customer.name().equalsIgnoreCase("NotifyFail"))
            throw new IllegalStateException("Notification unavailable");
        System.out.println("Confirmation to " + customer.email() + ": " + orderId);
    }
}
