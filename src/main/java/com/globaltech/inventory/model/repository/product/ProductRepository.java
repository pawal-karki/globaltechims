package com.globaltech.inventory.model.repository.product;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.globaltech.inventory.model.domain.product.Product;
import com.globaltech.inventory.model.repository.base.BaseRepository;

/**
 * Repository interface for Product entity
 */
@Repository
public interface ProductRepository extends BaseRepository<Product, Long> {
    
    /**
     * Find products by name containing the specified string (case insensitive)
     * 
     * @param name the name to search for
     * @return list of products with name containing the specified string
     */
    List<Product> findByNameContainingIgnoreCase(String name);
    
    /**
     * Find products by category ID
     * 
     * @param categoryId the category ID
     * @return list of products in the specified category
     */
    List<Product> findByCategoryId(Long categoryId);
    
    /**
     * Find products by supplier ID
     * 
     * @param supplierId the supplier ID
     * @return list of products from the specified supplier
     */
    List<Product> findBySupplierId(Long supplierId);
    
    /**
     * Find products with price less than or equal to the specified price
     * 
     * @param price the maximum price
     * @return list of products with price less than or equal to the specified price
     */
    List<Product> findByPriceLessThanEqual(BigDecimal price);
    
    /**
     * Find products with price greater than or equal to the specified price
     * 
     * @param price the minimum price
     * @return list of products with price greater than or equal to the specified price
     */
    List<Product> findByPriceGreaterThanEqual(BigDecimal price);
    
    /**
     * Find products with price between the specified minimum and maximum prices
     * 
     * @param minPrice the minimum price
     * @param maxPrice the maximum price
     * @return list of products with price between the specified minimum and maximum prices
     */
    List<Product> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);
    
    /**
     * Find products by SKU
     * 
     * @param sku the SKU to search for
     * @return the product with the specified SKU
     */
    Product findBySku(String sku);
    
    /**
     * Search for products by name or description containing the specified string
     * 
     * @param searchTerm the search term
     * @return list of products with name or description containing the specified string
     */
    @Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(p.description) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    List<Product> searchByNameOrDescription(@Param("searchTerm") String searchTerm);
    
    /**
     * Find products in the specified category with price less than or equal to the specified price
     * 
     * @param categoryId the category ID
     * @param maxPrice the maximum price
     * @return list of products in the specified category with price less than or equal to the specified price
     */
    @Query("SELECT p FROM Product p WHERE p.category.id = :categoryId AND p.price <= :maxPrice")
    List<Product> findByCategoryIdAndMaxPrice(@Param("categoryId") Long categoryId, @Param("maxPrice") BigDecimal maxPrice);
} 