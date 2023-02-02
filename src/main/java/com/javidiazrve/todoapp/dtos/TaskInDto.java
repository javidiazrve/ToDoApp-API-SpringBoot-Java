package com.javidiazrve.todoapp.dtos;

public class TaskInDto {
    private String name;

    public TaskInDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
