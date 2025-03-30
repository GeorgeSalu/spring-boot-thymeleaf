package com.example.htmx.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.htmx.model.Todo;

@Repository
public interface ToDoRepository extends JpaRepository<Todo, Long>{

}
