package com.javidiazrve.todoapp.repository;

import com.javidiazrve.todoapp.models.Task;
import com.javidiazrve.todoapp.repositories.TaskRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataMongoTest
public class TaskRepositoryTest {

    @Autowired
    private TaskRepository taskRepository;
    Task task;
    Task task2;
    Task task3;
    List<Task> tasks;
    @Before
    public void setup() {

        task = new Task(
                "1",
                "Tarea de Matematicas",
                "Hacer la tarea de matematicas que tengo que entregar el jueves",
                LocalDateTime.now(),
                false
        );
        task2 = new Task(
                "2",
                "Hacer la cama",
                "Hacer la cama como todos los dias",
                LocalDateTime.now(),
                true
        );
        task3 = new Task(
                "3",
                "Hacer El Desayuno",
                "Prepararme un desayuno de campeones",
                LocalDateTime.now(),
                true
        );
        tasks = Arrays.asList(task, task2, task3);
        taskRepository.saveAll(tasks);
    }

    @After
    public void teardown() {
        task = null;
        task2 = null;
        task3 = null;
        taskRepository.deleteAll();
    }

    @Test
    public void testGetAllTasks(){

        List<Task> tasksResponse = taskRepository.findAll();

        assertThat(tasksResponse.get(0).getName()).isEqualTo(tasks.get(0).getName());
        assertThat(tasksResponse.size()).isEqualTo(tasks.size());

    }

    @Test
    public void testGetAllTasksByName(){

        String name = "Tarea de Matematicas";

        List<Task> tasksResponse = taskRepository.findTasksByName(name);

        assertThat(tasksResponse.get(0).getName()).isEqualTo(name);
        assertThat(tasksResponse.size()).isEqualTo(1);

    }

    @Test
    public void testGetTaskById(){
        String id = "2";

        Optional<Task> tasksResponse = taskRepository.findById(id);

        assertThat(tasksResponse.get().getName()).isEqualTo(tasks.get(1).getName());

    }

    @Test
    public void testGetTaskByFinishedTrue(){
        Boolean finished = true;
        List<Task> tasksResponseTrue = taskRepository.findTaskByFinished(finished);

        finished = false;
        List<Task> tasksResponseFalse = taskRepository.findTaskByFinished(finished);

        assertThat(tasksResponseTrue.size()).isEqualTo(2);
        assertThat(tasksResponseFalse.size()).isEqualTo(1);

    }

    @Test
    public void testCreateTask(){

        Task task = task2;
        task.setId("4");
        task.setName("Cocinar almuerzo");
        task.setDescription("Cocinar el almuerzo para todos hoy, hacer pasta bolognesa");
        task.setCreationDate(LocalDateTime.now());
        task.setFinished(false);

        Task taskResponse = taskRepository.save(task);
        List<Task> tasksResponse = taskRepository.findAll();

        assertThat(taskResponse.getDescription()).isEqualTo(task.getDescription());
        assertThat(tasksResponse.size()).isEqualTo(4);

    }

    @Test
    public void testPutTask(){

        Task task = tasks.get(1);
        task.setName("task name changed");

        Task taskResponse = taskRepository.save(task);
        List<Task> tasksResponse = taskRepository.findAll();

        assertThat(taskRepository.findById("2").get().getName()).isEqualTo(task.getName());
        assertThat(tasksResponse.size()).isEqualTo(tasks.size());
    }

    @Test
    public void testDeleteTask(){

        taskRepository.deleteById("2");

        List<Task> tasksResponse = taskRepository.findAll();

        assertThat(tasksResponse.size()).isEqualTo(tasks.size() - 1);

    }

    // Failures

    @Test
    public void testGetByIdFail(){

        String id = "5";
        Optional<Task> task = taskRepository.findById(id);

        assertThat(task.isPresent()).isFalse();

    }


}
