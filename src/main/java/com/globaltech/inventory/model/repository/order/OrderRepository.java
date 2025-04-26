package com.globaltech.inventory.model.repository.order;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.globaltech.inventory.model.domain.order.Order;
import com.globaltech.inventory.model.domain.order.OrderStatus;
import com.globaltech.inventory.model.repository.base.BaseRepository;

/**
 * Repository interface for Order entity
 */
@Repository
public interface OrderRepository extends BaseRepository<Order, Long> {
    
    /**
     * Find order by order number
     * 
     * @param orderNumber the order number
     * @return the order with the specified order number
     */
    Order findByOrderNumber(String orderNumber);
    
    /**
     * Find orders by customer name containing the specified string (case insensitive)
     * 
     * @param customerName the customer name to search for
     * @return list of orders with customer name containing the specified string
     */
    List<Order> findByCustomerNameContainingIgnoreCase(String customerName);
    
    /**
     * Find orders by customer email (exact match)
     * 
     * @param customerEmail the customer email
     * @return list of orders with the specified customer email
     */
    List<Order> findByCustomerEmail(String customerEmail);
    
    /**
     * Find orders by status
     * 
     * @param status the order status
     * @return list of orders with the specified status
     */
    List<Order> findByStatus(OrderStatus status);
    
    /**
     * Find orders by order date between the specified start and end dates
     * 
     * @param startDate the start date
     * @param endDate the end date
     * @return list of orders within the specified date range
     */
    List<Order> findByOrderDateBetween(LocalDateTime startDate, LocalDateTime endDate);
    
    /**
     * Find orders with total greater than or equal to the specified amount
     * 
     * @param total the minimum total
     * @return list of orders with total greater than or equal to the specified amount
     */
    List<Order> findByTotalGreaterThanEqual(BigDecimal total);
    
    /**
     * Find orders with total less than or equal to the specified amount
     * 
     * @param total the maximum total
     * @return list of orders with total less than or equal to the specified amount
     */
    List<Order> findByTotalLessThanEqual(BigDecimal total);
    
    /**
     * Find orders by status and order date between the specified start and end dates
     * 
     * @param status the order status
     * @param startDate the start date
     * @param endDate the end date
     * @return list of orders with the specified status within the specified date range
     */
    List<Order> findByStatusAndOrderDateBetween(OrderStatus status, LocalDateTime startDate, LocalDateTime endDate);
    
    /**
     * Find orders containing a specific product
     * 
     * @param productId the product ID
     * @return list of orders containing the specified product
     */
    @Query("SELECT DISTINCT o FROM Order o JOIN o.items i WHERE i.product.id = :productId")
    List<Order> findOrdersContainingProduct(@Param("productId") Long productId);
    
    /**
     * Get total sales for a date range
     * 
     * @param startDate the start date
     * @param endDate the end date
     * @return the total sales amount for the specified date range
     */
    @Query("SELECT SUM(o.total) FROM Order o WHERE o.orderDate BETWEEN :startDate AND :endDate AND o.status != 'CANCELLED'")
    BigDecimal getTotalSalesForDateRange(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
    
    /**
     * Count orders by status
     * 
     * @param status the order status
     * @return the number of orders with the specified status
     */
    long countByStatus(OrderStatus status);
} 