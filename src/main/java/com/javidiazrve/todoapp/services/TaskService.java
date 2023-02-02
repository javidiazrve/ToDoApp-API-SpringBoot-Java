package com.javidiazrve.todoapp.services;

import com.javidiazrve.todoapp.dtos.TaskInDto;
import com.javidiazrve.todoapp.models.Task;
import com.javidiazrve.todoapp.repositories.TaskRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskService{

    private TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository)
    {
        this.taskRepository = taskRepository;
    }
    public List<Task> getAll(){
        return taskRepository.findAll();
    }

    public Task createTask(Task task){

        return this.taskRepository.insert(task);

    }
}
