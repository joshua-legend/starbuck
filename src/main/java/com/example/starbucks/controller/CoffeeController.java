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
import java.util.Optional;

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
    public ResponseEntity<ApiResponse<String>> addCoffee(@RequestBody Coffee coffee) {
        ResultStatus resultStatus = coffeeService.addCoffee(coffee);
        if (ResultStatus.FAIL.equals(resultStatus)) {
            return ResponseEntity.ok(new ApiResponse(ResponseStatus.FAIL, "실패 했음", null));
        }
        return ResponseEntity.ok(new ApiResponse(ResponseStatus.SUCCESS, "성공 했음", null));
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<Coffee>>> getCoffeesByName(@RequestParam String name){
        List<Coffee> coffeeList = coffeeService.getCoffeesByName(name);
        return ResponseEntity.ok(new ApiResponse<>(ResponseStatus.SUCCESS, "성공 했음", coffeeList));
    }

    @GetMapping("/price")
    public ResponseEntity<ApiResponse<List<Coffee>>> getCoffeesByPrice(@RequestParam int min,@RequestParam int max){
        List<Coffee> coffeeList = coffeeService.getCoffeesByPrice(min,max);
        return ResponseEntity.ok(new ApiResponse<>(ResponseStatus.SUCCESS, "성공 했음", coffeeList));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Optional<Coffee>>> getCoffeeById(@PathVariable Integer id){
        Optional<Coffee> coffee = coffeeService.getCoffeeById(id);
        return ResponseEntity.ok(new ApiResponse<>(ResponseStatus.SUCCESS, "성공 했음", coffee));
    }

    // ? wildcard -> any
    public ApiResponse<?> validateApiResponse(ResultStatus status) {
        ResponseStatus resultStatus = ResultStatus.FAIL.equals(status) ? ResponseStatus.FAIL : ResponseStatus.SUCCESS;
        String message = ResultStatus.FAIL.equals(status) ? "실패 했음" : "성공 했음";
        return new ApiResponse(resultStatus, message, null);
    }


}
