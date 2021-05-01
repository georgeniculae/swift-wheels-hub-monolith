package com.sda.carrentalservice.dto;

import javax.validation.constraints.NotEmpty;
import java.util.List;

public class RentalOfficeDTO extends BaseEntityDTO {

    @NotEmpty(message = "Name cannot be empty!")
    private String name;

    @NotEmpty(message = "Internet domain cannot be empty!")
    private String internetDomain;

    @NotEmpty(message = "Contact address cannot be empty!")
    private String contactAddress;

    @NotEmpty(message = "Owner cannot be empty!")
    private String owner;

    private String logoType;
    private List<BranchDTO> branchesDTO;

    public RentalOfficeDTO(Long id, String name, String internetDomain, String contactAddress, String owner, String logoType, List<BranchDTO> branchesDTO) {
        super(id);
        this.name = name;
        this.internetDomain = internetDomain;
        this.contactAddress = contactAddress;
        this.owner = owner;
        this.logoType = logoType;
        this.branchesDTO = branchesDTO;
    }

    public RentalOfficeDTO() {
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

    public List<BranchDTO> getBranchesDTO() {
        return branchesDTO;
    }

    public void setBranchesDTO(List<BranchDTO> branchesDTO) {
        this.branchesDTO = branchesDTO;
    }
}
