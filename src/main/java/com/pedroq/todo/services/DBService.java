package com.pedroq.todo.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pedroq.todo.domain.Todo;
import com.pedroq.todo.repositories.TodoRepository;

@Service
public class DBService {
	
	@Autowired
	private TodoRepository todoRepository;

	public void instanciaBaseDeDados() throws ParseException  {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		Todo t1 = new Todo(null, "Estudar", "Estudar Spring Boot 2 e Angular 11", sdf.parse("25/01/2023"), false);
		Todo t2 = new Todo(null, "Fazer exercícios", "Me matricular na aula de patinação", sdf.parse("19/02/2023"), false);
		//Todo t3 = new Todo(null, "Exercícios", "Ir hoje na aula de natação", sdf.parse("21/03/2023"), false);
		//Todo t4 = new Todo(null, "Meditar", "Meditar durante 30 minutos pela manhã", sdf.parse("27/03/2021"), true);
		
		todoRepository.saveAll(Arrays.asList(t1, t2));
		
	}
}
