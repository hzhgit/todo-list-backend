package com.example.demo.service;

import com.example.demo.entity.Todo;
import com.example.demo.exception.NoSuchDataException;
import com.example.demo.respository.TodoRepository;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
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
        assertEquals(todos.get(0), allTodos.get(0));
        assertEquals(todos.get(0).getContent(), allTodos.get(0).getContent());
        assertEquals(todos.get(0).getStatus(), allTodos.get(0).getStatus());
    }

    @Test
    void should_return_todo_when_add_todo_given_todo() {
        //given
        TodoRepository mockRepository = mock(TodoRepository.class);
        TodoService todoService = new TodoService((mockRepository));
        Todo todo = new Todo(null, "test2");
        Integer todoId = todo.getId();
        given(mockRepository.save(todo)).willReturn(todo);

        //when
        Todo newTodo = todoService.addTodo(todo);

        //then
        assertEquals(todoId, newTodo.getId());
        assertEquals(todo.getContent(), newTodo.getContent());
        assertEquals(todo.getStatus(), newTodo.getStatus());
    }

    @Test
    void should_return_todo_when_update_todos_given_todo_and_todo_id() {
        //given
        TodoRepository mockRepository = mock(TodoRepository.class);
        Todo todo = new Todo(null, "go to sleep");
        TodoService todoService = new TodoService(mockRepository);
        given(mockRepository.findById(todo.getId())).willReturn(Optional.of(todo));
        given(mockRepository.save(todo)).willReturn(todo);
        //when
        Todo todoSaved = todoService.updateTodo(todo.getId(), todo);
        //then
        assertEquals(todo, todoSaved);
    }

    @Test
    void should_return_true_when_delete_todo_given_id() {
        //given
        TodoRepository mockRepository = mock(TodoRepository.class);
        TodoService todoService = new TodoService(mockRepository);
        given(mockRepository.findById(1)).willReturn(Optional.of(new Todo()));
        //when
        Boolean isDelete = todoService.deleteTodo(1);
        //then
        assertTrue(isDelete);
    }

    @Test
    void should_return_no_such_data_exception_when_delete_todo_given_id() {
        //given
        TodoRepository mockRepository = mock(TodoRepository.class);
        TodoService todoService = new TodoService(mockRepository);
        int id = 9999;
        given(mockRepository.getOne(1)).willReturn(null);
        //when
        Throwable exception = assertThrows(NoSuchDataException.class, () -> todoService.deleteTodo(id));
        //tehn
        assertEquals(NoSuchDataException.class, exception.getClass());
    }
}
