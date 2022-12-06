package com.pedroq.todo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pedroq.todo.domain.Todo;
import com.pedroq.todo.repositories.TodoRepository;
import com.pedroq.todo.services.exceptions.ObjectNotFoundException;

@Service
public class TodoService {
	
	@Autowired
	private TodoRepository repository;
	
	public List<Todo> findAll() {
		return repository.findAll();
	}
	
	public Todo findById(Integer id) {
		Optional<Todo> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Object not found! Id:" + id + ", Tipo: " + Todo.class.getName()));
	}
	
	public List<Todo> findAllOpen() {
		return repository.findAllOpen();
	}

	public List<Todo> findAllClose() {
		return repository.findAllClose();
	}

	public Todo create(Todo obj) {
		obj.setId(null);
		return repository.save(obj);
	}

	public void delete(Integer id) {
		repository.deleteById(id);
	}

	public Todo update(Integer id, Todo obj) {
		Todo newObj = findById(id);
		newObj.setTitulo(obj.getTitulo());
		newObj.setDescricao(obj.getDescricao());
		newObj.setDataParaFinalizar(obj.getDataParaFinalizar());
		newObj.setFinalizado(obj.getFinalizado());
		return repository.save(newObj);
	}


}
