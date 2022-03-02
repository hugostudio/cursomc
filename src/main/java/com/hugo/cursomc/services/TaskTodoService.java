package com.hugo.cursomc.services;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.hugo.cursomc.domain.TaskTodo;
import com.hugo.cursomc.repositories.TaskTodoRepository;
import com.hugo.cursomc.services.exceptions.DataIntegrityException;
import com.hugo.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class TaskTodoService {

	@Autowired
	private TaskTodoRepository TaskTodoRepository;
	
	public TaskTodo find(Integer id) {		
		Optional<TaskTodo> obj = TaskTodoRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException( 
				"Object not found! Id: " + id + ", type: " + TaskTodo.class.getName()));
	}
	
	public List<TaskTodo> list() {		
		List<TaskTodo> obj = TaskTodoRepository.findAll();
		if(obj == null) {
			 throw new ObjectNotFoundException( "Object not found! , type: " + TaskTodo.class.getName());
		}
		return obj;
	}
	
	public TaskTodo insert(TaskTodo obj) {		
		obj.setId(null);
		return TaskTodoRepository.save(obj);
	}
	
	public TaskTodo update(TaskTodo obj) {
		TaskTodo newObj = find(obj.getId());
		updateData(newObj, obj);
		return TaskTodoRepository.save(newObj);
	}
	
	private void updateData(TaskTodo newObj, TaskTodo obj) {
		newObj.setDescription(obj.getDescription());
		newObj.setTsUpdate(Calendar.getInstance().getTime());
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			TaskTodoRepository.deleteById(id);
		} catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Cannot delete TaskTodo object!");
		}		
	}
	
	public Page<TaskTodo> listPage(Integer page, Integer size, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, size, Direction.valueOf(direction), orderBy);
		return TaskTodoRepository.findAll(pageRequest);
	}
	
}
