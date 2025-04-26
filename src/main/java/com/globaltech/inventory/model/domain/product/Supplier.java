package com.globaltech.inventory.model.domain.product;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.globaltech.inventory.model.domain.base.AuditableEntity;

/**
 * Entity representing a product supplier
 */
@Entity
@Table(name = "suppliers")
public class Supplier extends AuditableEntity {
    
    private static final long serialVersionUID = 1L;
    
    @NotBlank(message = "Company name is required")
    @Size(max = 100, message = "Company name must be less than 100 characters")
    @Column(name = "company_name", nullable = false)
    private String companyName;
    
    @NotBlank(message = "Contact name is required")
    @Size(max = 100, message = "Contact name must be less than 100 characters")
    @Column(name = "contact_name", nullable = false)
    private String contactName;
    
    @Email(message = "Email should be valid")
    @Column(name = "email", unique = true)
    private String email;
    
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be 10 digits")
    @Column(name = "phone")
    private String phone;
    
    @Size(max = 255, message = "Address must be less than 255 characters")
    @Column(name = "address")
    private String address;
    
    @Size(max = 100, message = "City must be less than 100 characters")
    @Column(name = "city")
    private String city;
    
    @Size(max = 100, message = "Country must be less than 100 characters")
    @Column(name = "country")
    private String country;
    
    public Supplier() {
    }
    
    public Supplier(String companyName, String contactName, String email) {
        this.companyName = companyName;
        this.contactName = contactName;
        this.email = email;
    }
    
    public String getCompanyName() {
        return companyName;
    }
    
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    
    public String getContactName() {
        return contactName;
    }
    
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getCity() {
        return city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }
    
    public String getCountry() {
        return country;
    }
    
    public void setCountry(String country) {
        this.country = country;
    }
    
    @Override
    public String toString() {
        return "Supplier [id=" + getId() + ", companyName=" + companyName + ", contactName=" + contactName + "]";
    }
} 