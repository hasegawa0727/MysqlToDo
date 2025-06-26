package com.example.demo.dto;

import lombok.Data;


@Data
public class TodoCreateRequest {
    private String title;
    private String description;

}
