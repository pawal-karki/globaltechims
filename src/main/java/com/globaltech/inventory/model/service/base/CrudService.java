package com.globaltech.inventory.model.service.base;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * Generic service interface for CRUD operations
 * 
 * @param <T> the entity type
 * @param <ID> the ID type
 */
public interface CrudService<T, ID extends Serializable> {
    
    /**
     * Save an entity
     * 
     * @param entity the entity to save
     * @return the saved entity
     */
    T save(T entity);
    
    /**
     * Save a list of entities
     * 
     * @param entities the entities to save
     * @return the saved entities
     */
    List<T> saveAll(List<T> entities);
    
    /**
     * Find an entity by ID
     * 
     * @param id the entity ID
     * @return optional of the found entity
     */
    Optional<T> findById(ID id);
    
    /**
     * Find all entities
     * 
     * @return list of all entities
     */
    List<T> findAll();
    
    /**
     * Find all entities sorted by the specified field
     * 
     * @param sortField the field to sort by
     * @return list of entities sorted by the specified field
     */
    List<T> findAllSorted(String sortField);
    
    /**
     * Find entities by a field value
     * 
     * @param fieldName the field name
     * @param value the field value
     * @return list of entities matching the field value
     */
    List<T> findByField(String fieldName, Object value);
    
    /**
     * Find an entity by a field value
     * 
     * @param fieldName the field name
     * @param value the field value
     * @return optional of entity matching the field value
     */
    Optional<T> findOneByField(String fieldName, Object value);
    
    /**
     * Count all entities
     * 
     * @return the number of entities
     */
    long count();
    
    /**
     * Delete an entity
     * 
     * @param entity the entity to delete
     */
    void delete(T entity);
    
    /**
     * Delete an entity by ID
     * 
     * @param id the ID of the entity to delete
     */
    void deleteById(ID id);
    
    /**
     * Check if an entity exists by ID
     * 
     * @param id the entity ID
     * @return true if an entity with the given ID exists
     */
    boolean existsById(ID id);
} 