package com.example.demo.service;

import com.example.demo.entity.Todo;
import com.example.demo.respository.TodoRepository;

import java.util.List;

public class TodoService {
    private  TodoRepository repository;
    public TodoService(TodoRepository repository) {
        this.repository = repository;
    }

    public List<Todo> getAllTodos() {
        return repository.findAll();
    }
}
