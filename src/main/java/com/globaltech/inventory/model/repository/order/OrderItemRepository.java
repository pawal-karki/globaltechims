package com.globaltech.inventory.model.repository.order;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.globaltech.inventory.model.domain.order.OrderItem;
import com.globaltech.inventory.model.repository.base.BaseRepository;

/**
 * Repository interface for OrderItem entity
 */
@Repository
public interface OrderItemRepository extends BaseRepository<OrderItem, Long> {
    
    /**
     * Find order items by order ID
     * 
     * @param orderId the order ID
     * @return list of order items for the specified order
     */
    List<OrderItem> findByOrderId(Long orderId);
    
    /**
     * Find order items by product ID
     * 
     * @param productId the product ID
     * @return list of order items for the specified product
     */
    List<OrderItem> findByProductId(Long productId);
    
    /**
     * Find order items by order ID and product ID
     * 
     * @param orderId the order ID
     * @param productId the product ID
     * @return list of order items for the specified order and product
     */
    List<OrderItem> findByOrderIdAndProductId(Long orderId, Long productId);
    
    /**
     * Find order items by quantity greater than or equal to the specified amount
     * 
     * @param quantity the minimum quantity
     * @return list of order items with quantity greater than or equal to the specified amount
     */
    List<OrderItem> findByQuantityGreaterThanEqual(int quantity);
    
    /**
     * Find order items by unit price greater than or equal to the specified amount
     * 
     * @param unitPrice the minimum unit price
     * @return list of order items with unit price greater than or equal to the specified amount
     */
    List<OrderItem> findByUnitPriceGreaterThanEqual(BigDecimal unitPrice);
    
    /**
     * Get total quantity of a product sold
     * 
     * @param productId the product ID
     * @return the total quantity of the specified product sold
     */
    @Query("SELECT SUM(oi.quantity) FROM OrderItem oi WHERE oi.product.id = :productId")
    Integer getTotalQuantitySoldByProductId(@Param("productId") Long productId);
    
    /**
     * Get total revenue for a product
     * 
     * @param productId the product ID
     * @return the total revenue for the specified product
     */
    @Query("SELECT SUM(oi.subtotal) FROM OrderItem oi WHERE oi.product.id = :productId")
    BigDecimal getTotalRevenueByProductId(@Param("productId") Long productId);
    
    /**
     * Find top selling products by quantity
     * 
     * @param limit the number of products to return
     * @return list of top selling products by quantity
     */
    @Query("SELECT oi.product.id, SUM(oi.quantity) as totalQuantity FROM OrderItem oi GROUP BY oi.product.id ORDER BY totalQuantity DESC")
    List<Object[]> findTopSellingProductsByQuantity(@Param("limit") int limit);
    
    /**
     * Find top selling products by revenue
     * 
     * @param limit the number of products to return
     * @return list of top selling products by revenue
     */
    @Query("SELECT oi.product.id, SUM(oi.subtotal) as totalRevenue FROM OrderItem oi GROUP BY oi.product.id ORDER BY totalRevenue DESC")
    List<Object[]> findTopSellingProductsByRevenue(@Param("limit") int limit);
} 