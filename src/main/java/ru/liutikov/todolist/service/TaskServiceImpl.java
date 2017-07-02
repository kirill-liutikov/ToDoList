package ru.liutikov.todolist.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.liutikov.todolist.dao.TaskDao;
import ru.liutikov.todolist.model.Task;

import java.util.List;

/**
 * Created by kirill on 28.06.2017.
 */

@Service
public class TaskServiceImpl implements TaskService{
    private TaskDao taskDao;

    public void setTaskDao(TaskDao bookDao) {
        this.taskDao = bookDao;
    }

    @Transactional
    public int addTask(Task task) {
        return this.taskDao.addTask(task);
    }

    @Transactional
    public void updateTask(Task task) {
        this.taskDao.updateTask(task);

    }

    @Transactional
    public void removeTask(int id) {
        this.taskDao.removeTask(id);

    }

    @Transactional
    public Task getTaskById(int id) {
        return this.taskDao.getTaskById(id);
    }

    @Transactional
    public List<Task> listTasks() {
        return this.taskDao.listTasks();
    }
}
