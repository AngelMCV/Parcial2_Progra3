package com.example.demo.controladores;

import java.util.Collection;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entidades.Contenido;
import com.example.demo.repositorios.RepositorioContenidos;

@RestController
@RequestMapping(value = "/contenidos")
public class ControlContenido {
	
	@Autowired
	RepositorioContenidos repository;
	
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public Collection<Contenido> getListaContenidos(){
		 Iterable<Contenido> listaContenidos = repository.findAll();
		return (Collection<Contenido>) listaContenidos;
	}
	
	@GetMapping(value = "/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public Contenido getContenido(@PathVariable(name = "id") Long id) {
		Optional<Contenido> contenido = repository.findById(id);
		Contenido result = null;
		if(contenido.isPresent()) {
			result = contenido.get();
		}
		return result;
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public Contenido createContenido(@RequestBody Contenido contenido) {
		Contenido nuevoContenido = repository.save(contenido);
		return nuevoContenido;
	}
	
	@DeleteMapping(value = "/{id}")
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public void deleteGenero(@PathVariable(name = "id") Long id) {
		repository.deleteById(id);
	}
	
	@PutMapping(value = "/{id}")
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public Contenido updateGenero(@PathVariable(name = "id")Long id, 
			@RequestBody Contenido contenido) {
		Optional<Contenido> oContenido = repository.findById(id);
		if(oContenido.isPresent()) {
			Contenido actual = oContenido.get();
			actual.setId(id);
			actual.setNombre(contenido.getNombre());
			actual.setResumen(contenido.getResumen());
			actual.setCategoria(contenido.getCategoria());
			actual.setGenero(contenido.getGenero());
			actual.setFecha(contenido.getFecha());
			Contenido updatedContenido = repository.save(actual);
			return updatedContenido;
		}
		return null;
	}
}