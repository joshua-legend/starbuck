package com.example.starbucks.repository;

import com.example.starbucks.model.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CoffeeRepository extends JpaRepository<Coffee, Integer> {
    List<Coffee> findByName(String name);

    @Query(value = "select * from Coffee c where c.price between :min and :max", nativeQuery = true)
    List<Coffee> findByPrice(@Param("min")int min,@Param("max")int max);

}
