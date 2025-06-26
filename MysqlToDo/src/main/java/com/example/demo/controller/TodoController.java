package com.example.demo.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.dto.TodoCreateRequest;
import com.example.demo.dto.TodoResponse;
import com.example.demo.dto.TodoUpdateRequest;
import com.example.demo.entity.Todo;
import com.example.demo.mapper.TodoMapper;


@RestController
@RequestMapping("/todos")
public class TodoController {
    
    @Autowired
    TodoMapper todoMapper;
    
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TodoResponse findById(@PathVariable int id) {
        Todo todo = todoMapper.findById(id);
        
        if(todo == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "指定されたIDのtodoは存在しません");
        }
        
        TodoResponse todoResponse = new TodoResponse();
        BeanUtils.copyProperties(todo, todoResponse);
        
        return todoResponse;
    }
    
    
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TodoResponse> getTodos(){
        List<Todo> todos = todoMapper.findAll();
        
        List<TodoResponse> todoResponseList = todos.stream()
                .map(todo -> {
                    TodoResponse todoResponse = new TodoResponse();
                    BeanUtils.copyProperties(todo, todoResponse);
                    return todoResponse;
                }).toList();
        
        
        return todoResponseList;
    }
    
    
    @GetMapping("/sort")
    @ResponseStatus(HttpStatus.OK)
    public List<TodoResponse> sortTodo() {
        List<Todo> todos = todoMapper.sortTodo();
        
        return todos.stream()
                .map(todo -> {
                    TodoResponse todoResponse = new TodoResponse();
                    BeanUtils.copyProperties(todo, todoResponse);
                    return todoResponse;
                    }).toList();
    }
    
    
    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public List<TodoResponse> searchTodo(@RequestParam String body) {
        List<Todo> todos = todoMapper.searchTodo(body);
        
        return todos.stream()
                .map(todo -> {
                    TodoResponse todoResponse = new TodoResponse();
                    BeanUtils.copyProperties(todo, todoResponse);
                    return todoResponse;
                    }).toList();
    }
    
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TodoResponse doPost(@RequestBody TodoCreateRequest todoCreateRequest) {
        Todo todo = new Todo();
        BeanUtils.copyProperties(todoCreateRequest, todo);
        todo.setStatus("未着手");
        LocalDateTime now = LocalDateTime.now();
        todo.setCreatedAt(now);
        todo.setUpdatedAt(now);
        
        int ret = todoMapper.insert(todo);
        
        TodoResponse todoResponse = new TodoResponse();
        BeanUtils.copyProperties(todo, todoResponse);
        
        return todoResponse;
    }
    
    
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TodoResponse doPut(@PathVariable int id, @RequestBody TodoUpdateRequest todoUpdateRequest) {
        Todo todo = todoMapper.findById(id);
        
        if(todo == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "指定されたIDのtodoは存在しません");
        }
        
        if(todoUpdateRequest.getTitle() != null) {
            todo.setTitle(todoUpdateRequest.getTitle());
        }
        if(todoUpdateRequest.getStatus() != null) {
            todo.setStatus(todoUpdateRequest.getStatus());
        }
        if(todoUpdateRequest.getDescription() != null) {
            todo.setDescription(todoUpdateRequest.getDescription());
        }
        
        todo.setId(id);
        LocalDateTime now = LocalDateTime.now();
        todo.setUpdatedAt(now);
        todoMapper.update(todo);
        
        TodoResponse todoResponse = new TodoResponse();
        BeanUtils.copyProperties(todo, todoResponse);
        
        return todoResponse;
        
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void doDelete(@PathVariable int id) {
        boolean ret = todoMapper.delete(id);
        
        if(!ret) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "指定されたIDのtodoは存在しません");
        }
    }

}
