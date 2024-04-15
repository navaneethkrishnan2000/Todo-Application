package com.todo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todo.model.Todo;
import com.todo.repository.TodoRepository;

@Service
public class TodoService {
	
	@Autowired
	TodoRepository todoRepository;
	
	public List<Todo> getAllTodoItems() {
		ArrayList<Todo> todoList = new ArrayList<>();
		todoRepository.findAll().forEach(todo -> todoList.add(todo));
		
		return todoList;
		
	}
	
	public Todo getTodoItemById(Long id) {
		return todoRepository.findById(id).get();
	}
	
	public boolean saveOrUpdateTodoItem(Todo todo) {
		Todo updatedObj = todoRepository.save(todo);
		
		if (getTodoItemById(updatedObj.getId()) != null) {
			return true;
		}
		return false;
	}
	
	public boolean updateStatus(Long id) {
		Todo todo = getTodoItemById(id);
		todo.setStatus("Completed");
		
		return saveOrUpdateTodoItem(todo);
	}
	
	public boolean deleteTodoItem(Long id) {
		todoRepository.deleteById(id);
		
		if(todoRepository.findById(id).isEmpty()) {
			return true;
		}
		return false;
	}
}
