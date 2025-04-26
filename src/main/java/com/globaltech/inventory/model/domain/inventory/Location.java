package com.globaltech.inventory.model.domain.inventory;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.globaltech.inventory.model.domain.base.AuditableEntity;

/**
 * Entity representing a storage location in the warehouse
 */
@Entity
@Table(name = "locations")
public class Location extends AuditableEntity {
    
    private static final long serialVersionUID = 1L;
    
    @NotBlank(message = "Location name is required")
    @Size(max = 100, message = "Location name must be less than 100 characters")
    @Column(name = "name", nullable = false, unique = true)
    private String name;
    
    @Size(max = 500, message = "Description must be less than 500 characters")
    @Column(name = "description")
    private String description;
    
    @Column(name = "zone")
    private String zone;
    
    @Column(name = "aisle")
    private String aisle;
    
    @Column(name = "rack")
    private String rack;
    
    @Column(name = "shelf")
    private String shelf;
    
    @Column(name = "bin")
    private String bin;
    
    @Column(name = "active")
    private boolean active = true;
    
    public Location() {
    }
    
    public Location(String name) {
        this.name = name;
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
    
    public String getZone() {
        return zone;
    }
    
    public void setZone(String zone) {
        this.zone = zone;
    }
    
    public String getAisle() {
        return aisle;
    }
    
    public void setAisle(String aisle) {
        this.aisle = aisle;
    }
    
    public String getRack() {
        return rack;
    }
    
    public void setRack(String rack) {
        this.rack = rack;
    }
    
    public String getShelf() {
        return shelf;
    }
    
    public void setShelf(String shelf) {
        this.shelf = shelf;
    }
    
    public String getBin() {
        return bin;
    }
    
    public void setBin(String bin) {
        this.bin = bin;
    }
    
    public boolean isActive() {
        return active;
    }
    
    public void setActive(boolean active) {
        this.active = active;
    }
    
    @Override
    public String toString() {
        return "Location [id=" + getId() + ", name=" + name + "]";
    }
} 