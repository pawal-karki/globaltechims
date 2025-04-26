package com.globaltech.inventory.model.repository.base;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Base repository interface that provides common CRUD operations
 * 
 * @param <T> the entity type
 * @param <ID> the ID type
 */
@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {
    
    /**
     * Find all entities sorted by the specified field
     * 
     * @param sortField the field to sort by
     * @return list of entities sorted by the specified field
     */
    List<T> findAllOrderBy(String sortField);
    
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
} 