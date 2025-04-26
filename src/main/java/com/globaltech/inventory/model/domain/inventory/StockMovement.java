package com.globaltech.inventory.model.domain.inventory;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.globaltech.inventory.model.domain.base.AuditableEntity;
import com.globaltech.inventory.model.domain.product.Product;

/**
 * Entity representing a stock movement record
 */
@Entity
@Table(name = "stock_movements")
public class StockMovement extends AuditableEntity {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * Enum representing the type of stock movement
     */
    public enum MovementType {
        STOCK_IN,    // Stock received
        STOCK_OUT,   // Stock shipped to customer
        ADJUSTMENT,  // Stock adjusted due to count or damage
        TRANSFER     // Stock transferred between locations
    }
    
    @NotNull(message = "Product is required")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "source_location_id")
    private Location sourceLocation;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "target_location_id")
    private Location targetLocation;
    
    @NotNull(message = "Quantity is required")
    @Min(value = 1, message = "Quantity must be at least 1")
    @Column(name = "quantity", nullable = false)
    private int quantity;
    
    @NotNull(message = "Movement type is required")
    @Enumerated(EnumType.STRING)
    @Column(name = "movement_type", nullable = false)
    private MovementType movementType;
    
    @Column(name = "reference_number")
    private String referenceNumber;
    
    @Column(name = "notes")
    private String notes;
    
    @NotNull(message = "Movement date is required")
    @Column(name = "movement_date", nullable = false)
    private LocalDateTime movementDate;
    
    public StockMovement() {
        this.movementDate = LocalDateTime.now();
    }
    
    public StockMovement(Product product, Location sourceLocation, Location targetLocation, 
                        int quantity, MovementType movementType) {
        this.product = product;
        this.sourceLocation = sourceLocation;
        this.targetLocation = targetLocation;
        this.quantity = quantity;
        this.movementType = movementType;
        this.movementDate = LocalDateTime.now();
    }
    
    public Product getProduct() {
        return product;
    }
    
    public void setProduct(Product product) {
        this.product = product;
    }
    
    public Location getSourceLocation() {
        return sourceLocation;
    }
    
    public void setSourceLocation(Location sourceLocation) {
        this.sourceLocation = sourceLocation;
    }
    
    public Location getTargetLocation() {
        return targetLocation;
    }
    
    public void setTargetLocation(Location targetLocation) {
        this.targetLocation = targetLocation;
    }
    
    public int getQuantity() {
        return quantity;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public MovementType getMovementType() {
        return movementType;
    }
    
    public void setMovementType(MovementType movementType) {
        this.movementType = movementType;
    }
    
    public String getReferenceNumber() {
        return referenceNumber;
    }
    
    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }
    
    public String getNotes() {
        return notes;
    }
    
    public void setNotes(String notes) {
        this.notes = notes;
    }
    
    public LocalDateTime getMovementDate() {
        return movementDate;
    }
    
    public void setMovementDate(LocalDateTime movementDate) {
        this.movementDate = movementDate;
    }
    
    @Override
    public String toString() {
        return "StockMovement [id=" + getId() + ", product=" + product.getName() + 
                ", type=" + movementType + ", quantity=" + quantity + "]";
    }
} 