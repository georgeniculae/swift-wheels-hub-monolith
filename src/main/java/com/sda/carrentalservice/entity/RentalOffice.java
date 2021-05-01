package com.sda.carrentalservice.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
public class RentalOffice extends BaseEntity {

    @NotEmpty(message = "Name cannot be empty!")
    private String name;

    @NotEmpty(message = "Internet domain cannot be empty!")
    private String internetDomain;

    @NotEmpty(message = "Contact address domain cannot be empty!")
    private String contactAddress;

    @NotEmpty(message = "Owner cannot be empty!")
    private String owner;

    private String logoType;

    @OneToMany(mappedBy = "rentalOffice", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Branch> branches;

    public RentalOffice(String name, String internetDomain, String contactAddress, String owner, String logoType, List<Branch> branches) {
        this.name = name;
        this.internetDomain = internetDomain;
        this.contactAddress = contactAddress;
        this.owner = owner;
        this.logoType = logoType;
        this.branches = branches;
    }

    public RentalOffice() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInternetDomain() {
        return internetDomain;
    }

    public void setInternetDomain(String internetDomain) {
        this.internetDomain = internetDomain;
    }

    public String getContactAddress() {
        return contactAddress;
    }

    public void setContactAddress(String contactAddress) {
        this.contactAddress = contactAddress;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getLogoType() {
        return logoType;
    }

    public void setLogoType(String logoType) {
        this.logoType = logoType;
    }

    public List<Branch> getBranches() {
        return branches;
    }

    public void setBranches(List<Branch> branches) {
        this.branches = branches;
    }
}
