package com.carrentalservice.dto;

import javax.validation.constraints.NotEmpty;
import java.util.List;

public class RentalOfficeDto extends BaseEntityDto {

    @NotEmpty(message = "Name cannot be empty!")
    private String name;

    @NotEmpty(message = "Internet domain cannot be empty!")
    private String internetDomain;

    @NotEmpty(message = "Contact address cannot be empty!")
    private String contactAddress;

    @NotEmpty(message = "Owner cannot be empty!")
    private String owner;

    private String logoType;
    private List<BranchDto> branchesDTO;

    public RentalOfficeDto(Long id, String name, String internetDomain, String contactAddress, String owner, String logoType, List<BranchDto> branchesDTO) {
        super(id);
        this.name = name;
        this.internetDomain = internetDomain;
        this.contactAddress = contactAddress;
        this.owner = owner;
        this.logoType = logoType;
        this.branchesDTO = branchesDTO;
    }

    public RentalOfficeDto() {
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

    public List<BranchDto> getBranchesDTO() {
        return branchesDTO;
    }

    public void setBranchesDTO(List<BranchDto> branchesDTO) {
        this.branchesDTO = branchesDTO;
    }
}
