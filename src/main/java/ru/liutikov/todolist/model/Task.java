package ru.liutikov.todolist.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import javax.persistence.*;

/**
 * Created by kirill on 28.06.2017.
 */
@Entity
@Table (name = "tasks")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Task {

    @Id
    @Column (name="id")
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @JsonProperty ("id")
    private int id;

    @Column (name ="create_dt")
    @JsonProperty("date")
    private Date date;

    @Column (name="description")
    @JsonProperty("description")
    private String Description;

    @Column (name="hasDone")
    @JsonProperty ("hasdone")
    private Boolean hasDone;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Boolean getHasDone() {
        return hasDone;
    }

    public void setHasDone(Boolean hasDone) {
        this.hasDone = hasDone;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", date=" + date +
                ", Description='" + Description + '\'' +
                ", hasDone=" + hasDone +
                '}';
    }
}
