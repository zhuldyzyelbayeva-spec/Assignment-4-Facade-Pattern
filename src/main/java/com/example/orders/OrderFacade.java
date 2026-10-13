package com.example.orders;
import java.util.Objects;
public class OrderFacade {
    private final InventoryService inventory;
    private final PaymentProcessor payments;
    private final ShippingService shipping;
    private final NotificationService notifications;
    private int nextOrderNumber = 1;
    public OrderFacade() {
        this(new InventoryService(), new PaymentProcessor(), new ShippingService(), new NotificationService());
    }
    // Dependency injection supports tests and advanced integration without burdening Main.
    public OrderFacade(InventoryService inventory, PaymentProcessor payments,
                       ShippingService shipping, NotificationService notifications) {
        this.inventory = Objects.requireNonNull(inventory);
        this.payments = Objects.requireNonNull(payments);
        this.shipping = Objects.requireNonNull(shipping);
        this.notifications = Objects.requireNonNull(notifications);
    }
    public String placeOrder(Cart cart, Customer customer) {
        Objects.requireNonNull(cart, "cart");
        Objects.requireNonNull(customer, "customer");
        inventory.reserve(cart);
        String paymentId = null;
        String shipmentId = null;
        try {
            paymentId = payments.charge(customer, cart.total());
            shipmentId = shipping.schedule(customer, cart);
            String orderId = "ORDER-" + nextOrderNumber;
            notifications.sendConfirmation(customer, orderId);
            nextOrderNumber++;
            return orderId;
        } catch (RuntimeException failure) {
            // Reverse completed steps. Keep the original failure if compensation also fails.
            if (shipmentId != null) {
                try { shipping.cancel(shipmentId); }
                catch (RuntimeException rollbackFailure) { failure.addSuppressed(rollbackFailure); }
            }
            if (paymentId != null) {
                try { payments.refund(paymentId); }
                catch (RuntimeException rollbackFailure) { failure.addSuppressed(rollbackFailure); }
            }
            try { inventory.release(cart); }
            catch (RuntimeException rollbackFailure) { failure.addSuppressed(rollbackFailure); }
            throw new IllegalStateException("Order failed: " + failure.getMessage(), failure);
        }
    }
    // Read-only, simplified status query; no subsystem instance escapes the facade.
    public int availableStock(String productId) { return inventory.available(productId); }
}
