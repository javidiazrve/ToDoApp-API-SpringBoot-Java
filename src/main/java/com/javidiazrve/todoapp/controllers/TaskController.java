package com.javidiazrve.todoapp.controllers;

import com.javidiazrve.todoapp.dtos.TaskInDto;
import com.javidiazrve.todoapp.models.Task;
import com.javidiazrve.todoapp.services.TaskService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/task")
    public List<Task> getTasks(){
        return this.taskService.getAll();
    }

    @PostMapping()
    public Task createTask(@RequestBody @Valid TaskInDto taskDto) {
        return taskService.createTask(taskDto.toTask());
    }

}
