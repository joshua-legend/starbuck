package com.example.starbucks.service;


import com.example.starbucks.model.Coffee;
import com.example.starbucks.status.ResultStatus;

import java.util.List;
import java.util.Optional;

public interface CoffeeService {
    List<Coffee> getAllCoffees();
    List<Coffee> getCoffeesByName(String name);
    List<Coffee> getCoffeesByPrice(int min, int max);
    Optional<Coffee> getCoffeeById(Integer id);
    ResultStatus addCoffee(Coffee coffee);
}
