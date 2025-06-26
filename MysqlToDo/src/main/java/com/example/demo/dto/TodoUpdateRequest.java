package com.example.demo.dto;

import lombok.Data;


@Data
public class TodoUpdateRequest {
    String title;
    String status;
    String description;

}
