package com.example.demo.service;

import com.example.demo.entity.Todo;
import com.example.demo.exception.NoSuchDataException;
import com.example.demo.respository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TodoService {
    private TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    public Todo addTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    public Todo updateTodo(Integer id, Todo todo) {
        if (todoRepository.findById(id).isPresent()) {
            todo.setId(id);
            return todoRepository.save(todo);
        }
        return null;
    }

    public Boolean deleteTodo(int id) {
        if (todoRepository.findById(id).isPresent()) {
            todoRepository.deleteById(id);
            return true;
        }
        throw new NoSuchDataException();
    }
}
