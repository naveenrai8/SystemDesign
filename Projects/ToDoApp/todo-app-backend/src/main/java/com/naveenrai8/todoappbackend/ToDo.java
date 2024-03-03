package com.naveenrai8.todoappbackend;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity(name = "ToDo")
public class ToDo {
    @Id
    @GeneratedValue
    public int id;
    public String description;
    public LocalDate completedBy;
    public boolean isCompleted = false;

    public String userName;

    public ToDo() {
    }

    public ToDo(int id, String description, String userName) {
        this.id = id;
        this.description = description;
        this.completedBy = LocalDate.now();
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public LocalDate getCompletedBy() {
        return completedBy;
    }

    public void setCompletedBy(LocalDate completedBy) {
        this.completedBy = completedBy;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
