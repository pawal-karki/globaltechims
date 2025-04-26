package com.globaltech.inventory.model.domain.order;

/**
 * Enum representing the status of an order
 */
public enum OrderStatus {
    
    PENDING("Pending", "Order has been created but not yet processed"),
    PROCESSING("Processing", "Order is being processed"),
    SHIPPED("Shipped", "Order has been shipped"),
    DELIVERED("Delivered", "Order has been delivered"),
    CANCELLED("Cancelled", "Order has been cancelled"),
    RETURNED("Returned", "Order has been returned");
    
    private final String displayName;
    private final String description;
    
    OrderStatus(String displayName, String description) {
        this.displayName = displayName;
        this.description = description;
    }
    
    public String getDisplayName() {
        return displayName;
    }
    
    public String getDescription() {
        return description;
    }
    
    @Override
    public String toString() {
        return this.displayName;
    }
} 