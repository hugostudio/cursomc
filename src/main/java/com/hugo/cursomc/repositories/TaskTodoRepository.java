package com.hugo.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hugo.cursomc.domain.TaskTodo;

@Repository
public interface TaskTodoRepository extends JpaRepository<TaskTodo, Integer> {

}
