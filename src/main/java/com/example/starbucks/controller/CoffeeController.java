package com.example.starbucks.controller;

import com.example.starbucks.dto.ApiResponse;
import com.example.starbucks.model.Coffee;
import com.example.starbucks.service.CoffeeService;
import com.example.starbucks.status.ResponseStatus;
import com.example.starbucks.status.ResultStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/coffee")
public class CoffeeController {

    @Autowired
    CoffeeService coffeeService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<Coffee>>> getCoffeeAll() {
        List<Coffee> coffeeList = coffeeService.getAllCoffees();
        ApiResponse apiResponse = new ApiResponse(ResponseStatus.SUCCESS, "성공 했음", coffeeList);
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<String>> addCoffee(@RequestBody Coffee coffee){
        ResultStatus resultStatus = coffeeService.addCoffee(coffee);
        if(ResultStatus.FAIL.equals(resultStatus)){
            ApiResponse apiResponse = new ApiResponse(ResponseStatus.FAIL, "실패 했음", null);
            return ResponseEntity.ok(apiResponse);
        }
        ApiResponse apiResponse = new ApiResponse(ResponseStatus.SUCCESS, "성공 했음", null);
        return ResponseEntity.ok(apiResponse);
    }


}
