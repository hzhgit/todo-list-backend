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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
