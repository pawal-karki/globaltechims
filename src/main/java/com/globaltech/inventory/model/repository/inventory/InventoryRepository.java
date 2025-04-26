package com.globaltech.inventory.model.repository.inventory;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.globaltech.inventory.model.domain.inventory.InventoryItem;
import com.globaltech.inventory.model.repository.base.BaseRepository;

/**
 * Repository interface for InventoryItem entity
 */
@Repository
public interface InventoryRepository extends BaseRepository<InventoryItem, Long> {
    
    /**
     * Find inventory items by product ID
     * 
     * @param productId the product ID
     * @return list of inventory items for the specified product
     */
    List<InventoryItem> findByProductId(Long productId);
    
    /**
     * Find inventory items by location ID
     * 
     * @param locationId the location ID
     * @return list of inventory items at the specified location
     */
    List<InventoryItem> findByLocationId(Long locationId);
    
    /**
     * Find inventory item by product ID and location ID
     * 
     * @param productId the product ID
     * @param locationId the location ID
     * @return optional of inventory item for the specified product at the specified location
     */
    Optional<InventoryItem> findByProductIdAndLocationId(Long productId, Long locationId);
    
    /**
     * Find inventory items with quantity less than the specified amount
     * 
     * @param quantity the maximum quantity
     * @return list of inventory items with quantity less than the specified amount
     */
    List<InventoryItem> findByQuantityLessThan(int quantity);
    
    /**
     * Find inventory items with quantity greater than or equal to the specified amount
     * 
     * @param quantity the minimum quantity
     * @return list of inventory items with quantity greater than or equal to the specified amount
     */
    List<InventoryItem> findByQuantityGreaterThanEqual(int quantity);
    
    /**
     * Find inventory items that need to be reordered (quantity <= reorder point)
     * 
     * @return list of inventory items that need to be reordered
     */
    @Query("SELECT i FROM InventoryItem i WHERE i.quantity <= i.reorderPoint")
    List<InventoryItem> findItemsNeedingReorder();
    
    /**
     * Find total quantity of a product across all locations
     * 
     * @param productId the product ID
     * @return the total quantity of the specified product
     */
    @Query("SELECT SUM(i.quantity) FROM InventoryItem i WHERE i.product.id = :productId")
    int getTotalQuantityByProductId(@Param("productId") Long productId);
    
    /**
     * Find low stock items (quantity < min stock level)
     * 
     * @return list of inventory items with quantity less than minimum stock level
     */
    @Query("SELECT i FROM InventoryItem i WHERE i.quantity < i.minStockLevel")
    List<InventoryItem> findLowStockItems();
    
    /**
     * Find overstocked items (quantity > max stock level)
     * 
     * @return list of inventory items with quantity greater than maximum stock level
     */
    @Query("SELECT i FROM InventoryItem i WHERE i.quantity > i.maxStockLevel")
    List<InventoryItem> findOverstockedItems();
} 