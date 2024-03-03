package com.naveenrai8.todoappbackend.controller;

import com.naveenrai8.todoappbackend.ToDo;
import com.naveenrai8.todoappbackend.service.ToDoApiService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"}, allowCredentials = "true")
public class TodoController {

    private final ToDoApiService toDoApiService;

    public TodoController(ToDoApiService toDoApiService) {
        this.toDoApiService = toDoApiService;
    }

    @GetMapping("users/{userName}/todos")
    public List<ToDo> getAllTodos(@PathVariable String userName) {
        return toDoApiService.getToDos(userName);
    }

    @GetMapping("users/{userName}/todos/{id}")
    public ToDo getTodosById(@PathVariable String userName, @PathVariable int id) {
        return toDoApiService.getToDoById(id, userName);
    }

    @PutMapping("/users/{userName}/todos/{id}")
    public void updateById(@PathVariable String userName, @PathVariable int id, @RequestBody ToDo toDo) {
        toDoApiService.UpdateToDo(userName, id, toDo);
    }

    @PostMapping("/users/{userName}/todos")
    public void createToDo(@PathVariable String userName, @RequestBody ToDo todo) {
        toDoApiService.create(todo, userName);
    }

    @DeleteMapping("users/{userName}/todos/{id}")
    public ResponseEntity<Void> deleteTodosById(@PathVariable String userName, @PathVariable int id) {
        toDoApiService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
