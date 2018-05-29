package com.nelioalves.cursomc.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.Servlet;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.nelioalves.cursomc.domain.Categoria;
import com.nelioalves.cursomc.dto.CategoriaDTO;
import com.nelioalves.cursomc.services.CategoriaService;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService service;
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseEntity<Categoria> find(@PathVariable Integer id) {		
		Categoria cat = service.find(id);
		return ResponseEntity.ok().body(cat);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody CategoriaDTO cat){
		Categoria obj = service.fromDTO(cat);
		obj  = service.insert(obj);
		URI uri = ServletUriComponentsBuilder
					.fromCurrentRequest()
					.path("/{id}")
					.buildAndExpand(cat.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody CategoriaDTO dto,@PathVariable Integer id)
	{
		Categoria cat = service.fromDTO(dto);
		cat.setId(id);
		cat = service.update(cat);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {		
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<CategoriaDTO>> findAll() {		
		List<Categoria> listaCategoria = service.findAll();
		List<CategoriaDTO> lista = listaCategoria.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(lista);
	}
	
	@RequestMapping(value="/page", method=RequestMethod.GET)
	public ResponseEntity<Page<CategoriaDTO>> findPage(
			@RequestParam(name="page", defaultValue="0") Integer page,
			@RequestParam(name="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(name="orderBy", defaultValue="nome") String orderBy, 
			@RequestParam(name="direction", defaultValue="ASC") String direction){		
		Page<Categoria> listaCategoria = service.findPage(page, linesPerPage,orderBy,direction);
		Page<CategoriaDTO> lista = listaCategoria.map(obj -> new CategoriaDTO(obj));
		return ResponseEntity.ok().body(lista);
	}
}
