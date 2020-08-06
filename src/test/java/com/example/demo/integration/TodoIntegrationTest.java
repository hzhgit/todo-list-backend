package com.example.demo.integration;

import com.example.demo.entity.Todo;
import com.example.demo.respository.TodoRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TodoIntegrationTest {
    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private MockMvc mockMvc;

    List<Todo> testTodo = Arrays.asList(
            new Todo(1, "111"),
            new Todo(2, "222"),
            new Todo(3, "333"),
            new Todo(4, "444")
    );

    @BeforeEach
    private void initData() {
        todoRepository.saveAll(testTodo);
    }

    @AfterEach
    private void deleteData() {
        todoRepository.deleteAll();
    }

    @Test
    void should_return_todos_when_get_all_todos_given_none() throws Exception {
        mockMvc.perform(get("/todos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(4)))
                .andExpect(jsonPath("$[0].content").value("111"))
                .andExpect(jsonPath("$[1].content").value("222"))
                .andExpect(jsonPath("$[2].content").value("333"));
    }

    @Test
    void should_return_todo_when_add_todo_given_todo() throws Exception {
        // given
        String todoInfo = "{\n" +
                "    \"content\":\"go to bed\"\n" +
                "}";
        // then
        mockMvc.perform(post("/todos").contentType((MediaType.APPLICATION_JSON)).content(todoInfo))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.content").value("go to bed"));
    }

    @Test
    void should_return_todo_when_update_todo_given_todo() throws Exception {
        // given
        Todo todo = new Todo(1, "test");
        Integer id = todoRepository.save(todo).getId();
        String todoInfo = "{\n" +
                "    \"content\": \"test\"\n" +
                "    \"status\": \"true\"\n" +
                "}";
        // then
        mockMvc.perform(put("/todos/" + id).contentType((MediaType.APPLICATION_JSON)).content(todoInfo))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.status").value(true));
    }
}
