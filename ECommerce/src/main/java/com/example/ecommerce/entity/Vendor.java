package com.example.ecommerce.entity;

import org.hibernate.annotations.FilterJoinTable;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tbl_vendor")
public class Vendor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String vendorName;
    private LocalDate createdAt;
    @Column(name = "admin_id", insertable = false, updatable = false)
    private Long adminId;
    @ManyToOne()
    @JoinColumn(name="admin_id", referencedColumnName = "id", updatable = false)
    private User user;
    @Column(name = "country_code",insertable = false, updatable = false)
    private Long countryCode;
    @ManyToOne()
    @JoinColumn(name="country_code", referencedColumnName = "id", updatable = false)
    private Country country;

    public Long getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(Long countryCode) {
        this.countryCode = countryCode;
    }

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
