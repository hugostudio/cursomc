package com.hugo.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.hugo.cursomc.domain.Categoria;
import com.hugo.cursomc.domain.Produto;
import com.hugo.cursomc.repositories.CategoriaRepository;
import com.hugo.cursomc.repositories.ProdutoRepository;
import com.hugo.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepo;
	
	@Autowired 
	private CategoriaRepository categoriaRepository; 

	public Produto buscar(Integer id) {
		Optional<Produto> obj = produtoRepo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Produto.class.getName()));
	}

	public List<Produto> listar() {
		List<Produto> obj = produtoRepo.findAll();
		if (obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado ! , Tipo: " + Produto.class.getName());
		}
		return obj;
	}

	public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy,
			String direction) {

		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);

		List<Categoria> categorias = categoriaRepository.findAllById(ids);

		return produtoRepo.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageRequest);

	}
}
