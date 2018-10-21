package com.hugo.cursomc.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hugo.cursomc.domain.Categoria;
import com.hugo.cursomc.services.CategoriaService;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaService categoriaService;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		Categoria obj = categoriaService.buscar(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public ResponseEntity<?> get() {
		List<Categoria> obj = categoriaService.listar();
		return ResponseEntity.ok().body(obj);
	}
}
