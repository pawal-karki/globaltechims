package com.globaltech.inventory.model.domain.inventory;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.globaltech.inventory.model.domain.base.AuditableEntity;
import com.globaltech.inventory.model.domain.product.Product;

/**
 * Entity representing an inventory item with quantity and location
 */
@Entity
@Table(name = "inventory_items", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"product_id", "location_id"})
})
public class InventoryItem extends AuditableEntity {
    
    private static final long serialVersionUID = 1L;
    
    @NotNull(message = "Product is required")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
    
    @NotNull(message = "Location is required")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;
    
    @Min(value = 0, message = "Quantity cannot be negative")
    @Column(name = "quantity", nullable = false)
    private int quantity;
    
    @Min(value = 0, message = "Minimum stock level cannot be negative")
    @Column(name = "min_stock_level")
    private int minStockLevel;
    
    @Min(value = 0, message = "Maximum stock level cannot be negative")
    @Column(name = "max_stock_level")
    private int maxStockLevel;
    
    @Column(name = "reorder_point")
    private int reorderPoint;
    
    public InventoryItem() {
    }
    
    public InventoryItem(Product product, Location location, int quantity) {
        this.product = product;
        this.location = location;
        this.quantity = quantity;
    }
    
    public Product getProduct() {
        return product;
    }
    
    public void setProduct(Product product) {
        this.product = product;
    }
    
    public Location getLocation() {
        return location;
    }
    
    public void setLocation(Location location) {
        this.location = location;
    }
    
    public int getQuantity() {
        return quantity;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public int getMinStockLevel() {
        return minStockLevel;
    }
    
    public void setMinStockLevel(int minStockLevel) {
        this.minStockLevel = minStockLevel;
    }
    
    public int getMaxStockLevel() {
        return maxStockLevel;
    }
    
    public void setMaxStockLevel(int maxStockLevel) {
        this.maxStockLevel = maxStockLevel;
    }
    
    public int getReorderPoint() {
        return reorderPoint;
    }
    
    public void setReorderPoint(int reorderPoint) {
        this.reorderPoint = reorderPoint;
    }
    
    /**
     * Adds the specified quantity to the current quantity.
     * 
     * @param quantityToAdd the quantity to add
     * @return the new quantity
     */
    public int addQuantity(int quantityToAdd) {
        if (quantityToAdd < 0) {
            throw new IllegalArgumentException("Quantity to add cannot be negative");
        }
        this.quantity += quantityToAdd;
        return this.quantity;
    }
    
    /**
     * Removes the specified quantity from the current quantity.
     * 
     * @param quantityToRemove the quantity to remove
     * @return the new quantity
     * @throws IllegalArgumentException if not enough quantity available
     */
    public int removeQuantity(int quantityToRemove) {
        if (quantityToRemove < 0) {
            throw new IllegalArgumentException("Quantity to remove cannot be negative");
        }
        if (quantityToRemove > this.quantity) {
            throw new IllegalArgumentException("Not enough quantity available");
        }
        this.quantity -= quantityToRemove;
        return this.quantity;
    }
    
    /**
     * Checks if the item needs to be reordered.
     * 
     * @return true if the quantity is less than or equal to the reorder point
     */
    public boolean needsReorder() {
        return this.quantity <= this.reorderPoint;
    }
    
    @Override
    public String toString() {
        return "InventoryItem [id=" + getId() + ", product=" + product.getName() + ", location=" + location.getName() + ", quantity=" + quantity + "]";
    }
} 