package com.globaltech.inventory.model.domain.product;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.globaltech.inventory.model.domain.base.AuditableEntity;

/**
 * Entity representing a product category
 */
@Entity
@Table(name = "product_categories")
public class Category extends AuditableEntity {
    
    private static final long serialVersionUID = 1L;
    
    @NotBlank(message = "Category name is required")
    @Size(max = 100, message = "Category name must be less than 100 characters")
    @Column(name = "name", nullable = false, unique = true)
    private String name;
    
    @Size(max = 500, message = "Description must be less than 500 characters")
    @Column(name = "description")
    private String description;
    
    public Category() {
    }
    
    public Category(String name) {
        this.name = name;
    }
    
    public Category(String name, String description) {
        this.name = name;
        this.description = description;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    @Override
    public String toString() {
        return "Category [id=" + getId() + ", name=" + name + "]";
    }
} 