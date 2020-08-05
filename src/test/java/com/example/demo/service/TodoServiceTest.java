package com.example.demo.service;

import com.example.demo.entity.Todo;
import com.example.demo.respository.TodoRepository;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class TodoServiceTest {
    @Test
    void should_return_todo_list_when_get_todo_list_given_none() {
        // given
        TodoRepository mockRepository = mock(TodoRepository.class);
        List<Todo> todos = new ArrayList<>();
        todos.add(new Todo());
        todos.add(new Todo());
        TodoService todoService = new TodoService(mockRepository);
        given(mockRepository.findAll()).willReturn(todos);

        //when
        List<Todo> allTodos = todoService.getAllTodos();

        //then
        assertEquals(todos,allTodos);
    }
}
