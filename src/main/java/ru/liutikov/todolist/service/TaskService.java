package ru.liutikov.todolist.service;

import ru.liutikov.todolist.model.Task;

import java.util.List;

/**
 * Created by kirill on 28.06.2017.
 */
public interface TaskService {
    public int addTask(Task task);
    public void updateTask(Task task);
    public void removeTask(int id);
    public Task getTaskById(int id);
    public List<Task> listTasks();
}
