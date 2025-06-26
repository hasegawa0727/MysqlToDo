package com.example.demo.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class TodoResponse {
    private int id;
    private String title;
    private String status;
    private String description;
    @JsonFormat(pattern = "yy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
    @JsonFormat(pattern = "yy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;

}
