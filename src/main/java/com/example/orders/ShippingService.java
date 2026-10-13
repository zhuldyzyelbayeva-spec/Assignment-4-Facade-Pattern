package com.example.orders;
import java.util.HashSet;
import java.util.Set;
public class ShippingService {
    private int nextId = 1;
    private final Set<String> shipments = new HashSet<>();
    public String schedule(Customer customer, Cart cart) {
        if (customer.name().equalsIgnoreCase("ShippingFail"))
            throw new IllegalStateException("Shipping unavailable");
        String id = "SHIP-" + nextId++;
        shipments.add(id);
        return id;
    }
    public void cancel(String shipmentId) {
        if (!shipments.remove(shipmentId)) throw new IllegalStateException("Unknown shipment");
    }
    public boolean isScheduled(String shipmentId) { return shipments.contains(shipmentId); }
}
