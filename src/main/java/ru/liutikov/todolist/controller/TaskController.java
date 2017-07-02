package ru.liutikov.todolist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.liutikov.todolist.model.Task;
import ru.liutikov.todolist.service.TaskService;

import java.util.List;

/**
 * Created by kirill on 28.06.2017.
 */

@RestController

public class TaskController {
    private TaskService taskService;

    @Autowired(required = true)
    @Qualifier(value = "taskService")
    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }


    @RequestMapping(value = "tasks", method = RequestMethod.GET)
    public ResponseEntity<List<Task>> listTasks() {
        List<Task> tasks = this.taskService.listTasks();
        if (tasks.isEmpty()) {
            return new ResponseEntity<List<Task>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Task>>(tasks, HttpStatus.OK);
    }


    @RequestMapping(value = "/task/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Task> getTask(@PathVariable("id") int id) {
        Task task = this.taskService.getTaskById(id);
        if (task == null) {
            return new ResponseEntity<Task>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Task>(task, HttpStatus.OK);
    }


    @RequestMapping(value = "/task", method = RequestMethod.POST)
    public ResponseEntity<Task> createTask(@RequestBody Task task) {

        if (task.getId() == 0) {
            int id = this.taskService.addTask(task);

            Task storedTask = this.taskService.getTaskById(id);
            if (storedTask == null) {
                return new ResponseEntity<Task>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<Task>(task, HttpStatus.OK);

        } else {
            this.taskService.updateTask(task);
            return new ResponseEntity<Task>(HttpStatus.CONFLICT);
        }
    }



    @RequestMapping(value = "/task/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Task> updateTask(@PathVariable("id") int id, @RequestBody Task task) {

        Task fTask = this.taskService.getTaskById(id);

        if (fTask == null) {
            return new ResponseEntity<Task>(HttpStatus.NOT_FOUND);
        }

        fTask.setDescription(task.getDescription());
        fTask.setDate(task.getDate());
        fTask.setHasDone(task.getHasDone());
        this.taskService.updateTask(fTask);

        return new ResponseEntity<Task>(fTask, HttpStatus.OK);
    }


    @RequestMapping(value = "/task/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Task> deleteTask(@PathVariable("id") int id) {

        Task fTask = this.taskService.getTaskById(id);

        if (fTask == null) {
            return new ResponseEntity<Task>(HttpStatus.NOT_FOUND);
        }

        this.taskService.removeTask(id);
        return new ResponseEntity<Task>(HttpStatus.NO_CONTENT);
    }

}
