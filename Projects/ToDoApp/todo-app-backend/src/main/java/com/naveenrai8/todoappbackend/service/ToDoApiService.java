package com.naveenrai8.todoappbackend.service;

import com.naveenrai8.todoappbackend.ToDo;
import com.naveenrai8.todoappbackend.repository.ToDoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToDoApiService {
    private final ToDoRepository toDoRepository;

    public ToDoApiService(ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }

    public List<ToDo> getToDos(String userName) {
        return toDoRepository.findAllByUserName(userName);
    }

    public void deleteById(int id) {
        toDoRepository.deleteById(id);
    }

    public void UpdateToDo(String userName, int id, ToDo toDo) {
        ToDo existingToDo = getToDoById(id, userName);
        existingToDo.setDescription(toDo.getDescription());
        existingToDo.setCompletedBy(toDo.getCompletedBy());
        existingToDo.setCompleted(toDo.isCompleted());
        toDoRepository.deleteById(id);
        create(toDo, userName);
    }

    public ToDo getToDoById(int id, String userName) {
        return toDoRepository.findById(id).orElse(null);
    }

    public void create(ToDo toDo, String userName) {
        toDo.setUserName(userName);
        toDoRepository.save(toDo);
    }
}
