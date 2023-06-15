package com.carrentalservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class Employee extends BaseEntity {

    @NotEmpty(message = "First name cannot be empty")
    private String firstName;

    @NotEmpty(message = "Last name cannot be empty")
    private String lastName;

    @NotEmpty(message = "Job position cannot be empty")
    private String jobPosition;

    @ManyToOne(fetch = FetchType.LAZY)
    private Branch workingBranch;

    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private ReturnCar returnCar;

}
