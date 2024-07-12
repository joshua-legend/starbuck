package com.example.starbucks.service;


import com.example.starbucks.model.Coffee;
import com.example.starbucks.status.ResultStatus;

import java.util.List;

public interface CoffeeService {
    List<Coffee> getAllCoffees();
    ResultStatus addCoffee(Coffee coffee);
}
