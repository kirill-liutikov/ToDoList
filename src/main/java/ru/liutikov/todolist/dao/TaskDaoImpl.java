package ru.liutikov.todolist.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.liutikov.todolist.model.Task;


import java.util.List;

/**
 * Created by kirill on 28.06.2017.
 */
@Repository
public class TaskDaoImpl implements TaskDao {
    private static final Logger logger = LoggerFactory.getLogger(TaskDaoImpl.class);
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public int addTask(Task task) {
        Session session = this.sessionFactory.getCurrentSession();
        logger.info("Task saved: " + task);
        session.persist(task);
        session.flush();
        return task.getId();

    }

    public void updateTask(Task task) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(task);
        logger.info("Task successfully update: " + task);
        //logger.info("Task update: " + task);
    }

    public void removeTask(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Task task = (Task) session.load(Task.class, new Integer(id));

        if (task != null)
            session.delete(task);

        logger.info("Task " + id + " deleted: " + task);

    }

    public Task getTaskById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Task task = (Task) session.load(Task.class, new Integer(id));
        logger.info("Get task by " + id + " successfully. Task details: " + task);

        return task;
    }

    @SuppressWarnings("unchecked")
    public List<Task> listTasks() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Task> tasks = session.createQuery("FROM Task").list();

        for(Task task: tasks){
            logger.info("Task list: " + task);
        }

        return tasks;
    }
}
