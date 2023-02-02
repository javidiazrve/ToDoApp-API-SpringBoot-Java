package com.javidiazrve.todoapp.dtos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.javidiazrve.todoapp.models.Task;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskInDto {
    @NotNull
    @NotEmpty
    private String name;
    @NotNull
    @NotEmpty
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Task toTask(){
        Task task = new Task();
        task.setName(name);
        task.setDescription(description);
        task.setFinished(false);
        task.setCreationDate(LocalDateTime.now());
        return task;
    }

}
