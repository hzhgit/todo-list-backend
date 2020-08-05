package com.example.demo.entity;

public class Todo {
    private Integer id;
    private String content;
    private Boolean status = false;

    public Todo(Integer id, String content) {
        this.id = id;
        this.content = content;
    }

    public Todo(){

    }
}
