package com.carrentalservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Branch extends BaseEntity {

    private String name;

    private String address;

    @OneToMany(mappedBy = "workingBranch", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Employee> employees;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Car> cars;

    @ManyToOne(fetch = FetchType.LAZY)
    private RentalOffice rentalOffice;

    @OneToMany(mappedBy = "rentalBranch", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Booking> rentalBookings = new ArrayList<>();

    @OneToMany(mappedBy = "returnBranch", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Booking> returnBookings = new ArrayList<>();

    @Override
    public final boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null) {
            return false;
        }

        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();

        if (thisEffectiveClass != oEffectiveClass) {
            return false;
        }

        Branch branch = (Branch) o;

        return getId() != null && Objects.equals(getId(), branch.getId());
    }

    @Override
    public final int hashCode() {
        return getClass().hashCode();
    }

}
