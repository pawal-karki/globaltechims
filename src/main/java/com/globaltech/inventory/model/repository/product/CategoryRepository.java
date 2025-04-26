package com.globaltech.inventory.model.repository.product;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.globaltech.inventory.model.domain.product.Category;
import com.globaltech.inventory.model.repository.base.BaseRepository;

/**
 * Repository interface for Category entity
 */
@Repository
public interface CategoryRepository extends BaseRepository<Category, Long> {
    
    /**
     * Find categories by name containing the specified string (case insensitive)
     * 
     * @param name the name to search for
     * @return list of categories with name containing the specified string
     */
    List<Category> findByNameContainingIgnoreCase(String name);
    
    /**
     * Find a category by name (exact match, case insensitive)
     * 
     * @param name the category name
     * @return the category with the specified name
     */
    Category findByNameIgnoreCase(String name);
    
    /**
     * Find categories by description containing the specified string (case insensitive)
     * 
     * @param description the description to search for
     * @return list of categories with description containing the specified string
     */
    List<Category> findByDescriptionContainingIgnoreCase(String description);
} 