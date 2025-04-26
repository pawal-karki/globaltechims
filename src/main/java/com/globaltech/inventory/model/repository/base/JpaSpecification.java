package com.globaltech.inventory.model.repository.base;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

/**
 * Utility class to help with JPA specifications for dynamic queries
 * 
 * @param <T> the entity type
 */
public class JpaSpecification<T> {
    
    /**
     * Create a specification to find entities where a field equals a value
     * 
     * @param fieldName the field name
     * @param value the field value
     * @return the specification
     */
    public static <T> Specification<T> fieldEquals(String fieldName, Object value) {
        return (Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            return builder.equal(root.get(fieldName), value);
        };
    }
    
    /**
     * Create a specification to find entities where a field contains a string value
     * 
     * @param fieldName the field name
     * @param value the string value to search for
     * @return the specification
     */
    public static <T> Specification<T> fieldContains(String fieldName, String value) {
        return (Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            return builder.like(root.get(fieldName), "%" + value + "%");
        };
    }
    
    /**
     * Create a specification to find entities where a field is greater than a value
     * 
     * @param fieldName the field name
     * @param value the field value
     * @return the specification
     */
    public static <T> Specification<T> fieldGreaterThan(String fieldName, Comparable<?> value) {
        return (Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            return builder.greaterThan(root.get(fieldName), value);
        };
    }
    
    /**
     * Create a specification to find entities where a field is less than a value
     * 
     * @param fieldName the field name
     * @param value the field value
     * @return the specification
     */
    public static <T> Specification<T> fieldLessThan(String fieldName, Comparable<?> value) {
        return (Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            return builder.lessThan(root.get(fieldName), value);
        };
    }
    
    /**
     * Create a specification to find entities where a field is in a list of values
     * 
     * @param fieldName the field name
     * @param values the list of values
     * @return the specification
     */
    public static <T> Specification<T> fieldIn(String fieldName, List<?> values) {
        return (Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            return root.get(fieldName).in(values);
        };
    }
    
    /**
     * Create a specification to find entities where a field is null
     * 
     * @param fieldName the field name
     * @return the specification
     */
    public static <T> Specification<T> fieldIsNull(String fieldName) {
        return (Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            return builder.isNull(root.get(fieldName));
        };
    }
    
    /**
     * Create a specification to find entities where a field is not null
     * 
     * @param fieldName the field name
     * @return the specification
     */
    public static <T> Specification<T> fieldIsNotNull(String fieldName) {
        return (Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            return builder.isNotNull(root.get(fieldName));
        };
    }
    
    /**
     * Create a specification by combining multiple specifications with AND
     * 
     * @param specifications the specifications to combine
     * @return the combined specification
     */
    @SafeVarargs
    public static <T> Specification<T> and(Specification<T>... specifications) {
        return (Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            List<Predicate> predicates = new ArrayList<>();
            for (Specification<T> spec : specifications) {
                if (spec != null) {
                    Predicate predicate = spec.toPredicate(root, query, builder);
                    if (predicate != null) {
                        predicates.add(predicate);
                    }
                }
            }
            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
    
    /**
     * Create a specification by combining multiple specifications with OR
     * 
     * @param specifications the specifications to combine
     * @return the combined specification
     */
    @SafeVarargs
    public static <T> Specification<T> or(Specification<T>... specifications) {
        return (Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            List<Predicate> predicates = new ArrayList<>();
            for (Specification<T> spec : specifications) {
                if (spec != null) {
                    Predicate predicate = spec.toPredicate(root, query, builder);
                    if (predicate != null) {
                        predicates.add(predicate);
                    }
                }
            }
            return builder.or(predicates.toArray(new Predicate[0]));
        };
    }
} 