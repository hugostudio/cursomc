package com.hugo.cursomc.resources;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hugo.cursomc.domain.TaskTodo;
import com.hugo.cursomc.services.TaskTodoService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value="/tasks")
public class TaskTodoResource {

	@Autowired
	private TaskTodoService TaskTodoService;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<TaskTodo> find(@PathVariable Integer id) {
		TaskTodo obj = TaskTodoService.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<TaskTodo>> list() {
		List<TaskTodo> lista = TaskTodoService.list();		
		return ResponseEntity.ok().body(lista);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody TaskTodo obj) {		
		obj = TaskTodoService.insert(obj);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody TaskTodo obj) {		
		obj = TaskTodoService.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		TaskTodoService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/page", method=RequestMethod.GET)
	public ResponseEntity<Page<TaskTodo>> listPage(
			@RequestParam(value="page", defaultValue="0") Integer page,
			@RequestParam(value="size", defaultValue="24") Integer size,
			@RequestParam(value="orderBy", defaultValue="nome") String orderBy,
			@RequestParam(value="direction", defaultValue="ASC") String direction ) {
		Page<TaskTodo> lista = TaskTodoService.listPage(page, size, orderBy, direction);		
		return ResponseEntity.ok().body(lista);
	}
}
