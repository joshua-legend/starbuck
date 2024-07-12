package com.example.starbucks.dto;

import com.example.starbucks.status.ResponseStatus;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class ApiResponse <T> {
    private ResponseStatus responseStatus;
    private String message;
    private T data;
}
