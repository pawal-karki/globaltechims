package com.globaltech.inventory.model.repository.inventory;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.globaltech.inventory.model.domain.inventory.StockMovement;
import com.globaltech.inventory.model.domain.inventory.StockMovement.MovementType;
import com.globaltech.inventory.model.repository.base.BaseRepository;

/**
 * Repository interface for StockMovement entity
 */
@Repository
public interface StockMovementRepository extends BaseRepository<StockMovement, Long> {
    
    /**
     * Find stock movements by product ID
     * 
     * @param productId the product ID
     * @return list of stock movements for the specified product
     */
    List<StockMovement> findByProductId(Long productId);
    
    /**
     * Find stock movements by source location ID
     * 
     * @param sourceLocationId the source location ID
     * @return list of stock movements from the specified source location
     */
    List<StockMovement> findBySourceLocationId(Long sourceLocationId);
    
    /**
     * Find stock movements by target location ID
     * 
     * @param targetLocationId the target location ID
     * @return list of stock movements to the specified target location
     */
    List<StockMovement> findByTargetLocationId(Long targetLocationId);
    
    /**
     * Find stock movements by movement type
     * 
     * @param movementType the movement type
     * @return list of stock movements of the specified type
     */
    List<StockMovement> findByMovementType(MovementType movementType);
    
    /**
     * Find stock movements by reference number
     * 
     * @param referenceNumber the reference number
     * @return list of stock movements with the specified reference number
     */
    List<StockMovement> findByReferenceNumber(String referenceNumber);
    
    /**
     * Find stock movements by movement date between the specified start and end dates
     * 
     * @param startDate the start date
     * @param endDate the end date
     * @return list of stock movements within the specified date range
     */
    List<StockMovement> findByMovementDateBetween(LocalDateTime startDate, LocalDateTime endDate);
    
    /**
     * Find stock movements by product ID and movement date between the specified start and end dates
     * 
     * @param productId the product ID
     * @param startDate the start date
     * @param endDate the end date
     * @return list of stock movements for the specified product within the specified date range
     */
    List<StockMovement> findByProductIdAndMovementDateBetween(Long productId, LocalDateTime startDate, LocalDateTime endDate);
    
    /**
     * Get total quantity moved in for a product by movement type
     * 
     * @param productId the product ID
     * @param movementType the movement type
     * @return the total quantity moved in for the specified product by movement type
     */
    @Query("SELECT SUM(m.quantity) FROM StockMovement m WHERE m.product.id = :productId AND m.movementType = :movementType")
    Integer getTotalQuantityByProductAndMovementType(@Param("productId") Long productId, @Param("movementType") MovementType movementType);
    
    /**
     * Get the most recent stock movement for a product
     * 
     * @param productId the product ID
     * @return the most recent stock movement for the specified product
     */
    @Query("SELECT m FROM StockMovement m WHERE m.product.id = :productId ORDER BY m.movementDate DESC")
    List<StockMovement> findMostRecentMovementsForProduct(@Param("productId") Long productId);
} 