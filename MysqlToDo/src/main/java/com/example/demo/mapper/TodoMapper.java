package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.entity.Todo;


@Mapper
public interface TodoMapper {
    Todo findById(int id);
    List<Todo> findAll();
    List<Todo> sortTodo();
    List<Todo> searchTodo(String body);
    int insert(@Param("todo") Todo todo);
    int update(@Param("todo") Todo todo);
    boolean delete(int id);

}
