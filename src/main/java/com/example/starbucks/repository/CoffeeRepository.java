package com.example.starbucks.repository;

import com.example.starbucks.model.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeRepository extends JpaRepository<Coffee, Integer> {


}
