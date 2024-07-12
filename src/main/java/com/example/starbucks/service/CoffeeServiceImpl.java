package com.example.starbucks.service;

import com.example.starbucks.model.Coffee;
import com.example.starbucks.repository.CoffeeRepository;
import com.example.starbucks.status.ResultStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoffeeServiceImpl implements CoffeeService {

    @Autowired
    private CoffeeRepository coffeeRepository;

    @Override
    public List<Coffee> getAllCoffees() {
        return coffeeRepository.findAll();
    }

    @Override
    public ResultStatus addCoffee(Coffee coffee) {
        if(coffee.getName() == null || coffee.getName().isEmpty()){
            return ResultStatus.FAIL;
        }
        if(coffee.getPrice() < 0){
            return ResultStatus.FAIL;
        }
        coffeeRepository.save(coffee);
        return ResultStatus.SUCCESS;
    }
}
