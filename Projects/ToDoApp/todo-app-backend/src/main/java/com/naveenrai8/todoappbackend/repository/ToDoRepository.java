package com.naveenrai8.todoappbackend.repository;

import com.naveenrai8.todoappbackend.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToDoRepository extends JpaRepository<ToDo, Integer> {
    List<ToDo> findAllByUserName(String userName);
}
