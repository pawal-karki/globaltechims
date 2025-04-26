package com.globaltech.inventory.model.domain.order;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.globaltech.inventory.model.domain.base.AuditableEntity;

/**
 * Entity representing an order
 */
@Entity
@Table(name = "orders")
public class Order extends AuditableEntity {
    
    private static final long serialVersionUID = 1L;
    
    @NotBlank(message = "Order number is required")
    @Column(name = "order_number", nullable = false, unique = true)
    private String orderNumber;
    
    @NotNull(message = "Order date is required")
    @Column(name = "order_date", nullable = false)
    private LocalDateTime orderDate;
    
    @NotBlank(message = "Customer name is required")
    @Size(max = 100, message = "Customer name must be less than 100 characters")
    @Column(name = "customer_name", nullable = false)
    private String customerName;
    
    @Column(name = "customer_email")
    private String customerEmail;
    
    @Column(name = "customer_phone")
    private String customerPhone;
    
    @Size(max = 255, message = "Shipping address must be less than 255 characters")
    @Column(name = "shipping_address")
    private String shippingAddress;
    
    @Column(name = "shipping_city")
    private String shippingCity;
    
    @Column(name = "shipping_country")
    private String shippingCountry;
    
    @Column(name = "shipping_postal_code")
    private String shippingPostalCode;
    
    @Column(name = "shipping_method")
    private String shippingMethod;
    
    @Column(name = "payment_method")
    private String paymentMethod;
    
    @Column(name = "subtotal")
    private BigDecimal subtotal = BigDecimal.ZERO;
    
    @Column(name = "tax")
    private BigDecimal tax = BigDecimal.ZERO;
    
    @Column(name = "shipping_cost")
    private BigDecimal shippingCost = BigDecimal.ZERO;
    
    @Column(name = "total")
    private BigDecimal total = BigDecimal.ZERO;
    
    @NotNull(message = "Order status is required")
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private OrderStatus status = OrderStatus.PENDING;
    
    @Column(name = "notes")
    private String notes;
    
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<OrderItem> items = new ArrayList<>();
    
    public Order() {
        this.orderDate = LocalDateTime.now();
    }
    
    public Order(String orderNumber, String customerName) {
        this.orderNumber = orderNumber;
        this.customerName = customerName;
        this.orderDate = LocalDateTime.now();
        this.status = OrderStatus.PENDING;
    }
    
    public String getOrderNumber() {
        return orderNumber;
    }
    
    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }
    
    public LocalDateTime getOrderDate() {
        return orderDate;
    }
    
    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }
    
    public String getCustomerName() {
        return customerName;
    }
    
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    
    public String getCustomerEmail() {
        return customerEmail;
    }
    
    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }
    
    public String getCustomerPhone() {
        return customerPhone;
    }
    
    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }
    
    public String getShippingAddress() {
        return shippingAddress;
    }
    
    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }
    
    public String getShippingCity() {
        return shippingCity;
    }
    
    public void setShippingCity(String shippingCity) {
        this.shippingCity = shippingCity;
    }
    
    public String getShippingCountry() {
        return shippingCountry;
    }
    
    public void setShippingCountry(String shippingCountry) {
        this.shippingCountry = shippingCountry;
    }
    
    public String getShippingPostalCode() {
        return shippingPostalCode;
    }
    
    public void setShippingPostalCode(String shippingPostalCode) {
        this.shippingPostalCode = shippingPostalCode;
    }
    
    public String getShippingMethod() {
        return shippingMethod;
    }
    
    public void setShippingMethod(String shippingMethod) {
        this.shippingMethod = shippingMethod;
    }
    
    public String getPaymentMethod() {
        return paymentMethod;
    }
    
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
    
    public BigDecimal getSubtotal() {
        return subtotal;
    }
    
    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }
    
    public BigDecimal getTax() {
        return tax;
    }
    
    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }
    
    public BigDecimal getShippingCost() {
        return shippingCost;
    }
    
    public void setShippingCost(BigDecimal shippingCost) {
        this.shippingCost = shippingCost;
    }
    
    public BigDecimal getTotal() {
        return total;
    }
    
    public void setTotal(BigDecimal total) {
        this.total = total;
    }
    
    public OrderStatus getStatus() {
        return status;
    }
    
    public void setStatus(OrderStatus status) {
        this.status = status;
    }
    
    public String getNotes() {
        return notes;
    }
    
    public void setNotes(String notes) {
        this.notes = notes;
    }
    
    public List<OrderItem> getItems() {
        return items;
    }
    
    public void setItems(List<OrderItem> items) {
        this.items = items;
    }
    
    /**
     * Add an item to the order and update the order totals
     * 
     * @param item the item to add
     */
    public void addItem(OrderItem item) {
        items.add(item);
        item.setOrder(this);
        calculateTotals();
    }
    
    /**
     * Remove an item from the order and update the order totals
     * 
     * @param item the item to remove
     */
    public void removeItem(OrderItem item) {
        items.remove(item);
        item.setOrder(null);
        calculateTotals();
    }
    
    /**
     * Calculate the order totals (subtotal, tax, shipping, total)
     */
    public void calculateTotals() {
        BigDecimal calculatedSubtotal = BigDecimal.ZERO;
        
        for (OrderItem item : items) {
            if (item.getSubtotal() != null) {
                calculatedSubtotal = calculatedSubtotal.add(item.getSubtotal());
            }
        }
        
        this.subtotal = calculatedSubtotal;
        
        // Calculate the total
        this.total = this.subtotal;
        
        if (this.tax != null) {
            this.total = this.total.add(this.tax);
        }
        
        if (this.shippingCost != null) {
            this.total = this.total.add(this.shippingCost);
        }
    }
    
    @Override
    public String toString() {
        return "Order [id=" + getId() + ", orderNumber=" + orderNumber + 
                ", customerName=" + customerName + ", status=" + status + "]";
    }
} 