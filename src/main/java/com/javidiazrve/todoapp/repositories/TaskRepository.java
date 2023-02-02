package com.javidiazrve.todoapp.repositories;

import com.javidiazrve.todoapp.models.Task;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TaskRepository extends MongoRepository<Task, String> {

    List<Task> findTasksByName(String name);

    List<Task> findTaskByFinished(Boolean finished);

}
