package com.carrentalservice.repository;

import com.carrentalservice.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    @Query("""
            From Car car
            where lower(car.make) like '%:filter%'
            or lower(car.model) like '%:filter%'""")
    Car findCarByFilter(@Param("filter") String filter);

    List<Car> findCarsByMake(String make);

}
