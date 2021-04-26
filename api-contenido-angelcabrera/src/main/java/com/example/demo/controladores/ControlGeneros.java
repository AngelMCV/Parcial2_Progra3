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

import com.example.demo.entidades.Genero;
import com.example.demo.repositorios.RepositorioGeneros;

@RestController
@RequestMapping(value = "/generos")
public class ControlGeneros {
	
	@Autowired
	RepositorioGeneros repository;
	
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public Collection<Genero> getListaGeneros(){
		 Iterable<Genero> listaGeneros = repository.findAll();
		return (Collection<Genero>) listaGeneros;
	}
	
	@GetMapping(value = "/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public Genero getGenero(@PathVariable(name = "id") Long id) {
		Optional<Genero> genero = repository.findById(id);
		Genero result = null;
		if(genero.isPresent()) {
			result = genero.get();
		}
		return result;
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public Genero createGenero(@RequestBody Genero genero) {
		Genero nuevoGenero = repository.save(genero);
		return nuevoGenero;
	}
	
	@DeleteMapping(value = "/{id}")
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public void deleteGenero(@PathVariable(name = "id") Long id) {
		repository.deleteById(id);
	}
	
	@PutMapping(value = "/{id}")
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public Genero updateGenero(@PathVariable(name = "id")Long id, 
			@RequestBody Genero genero) {
		Optional<Genero> oGenero = repository.findById(id);
		if(oGenero.isPresent()) {
			Genero actual = oGenero.get();
			actual.setId(id);
			actual.setNombre(genero.getNombre());
			actual.setDescripcion(genero.getDescripcion());
			actual.setFecha(genero.getFecha());
			Genero updatedGenero = repository.save(actual);
			return updatedGenero;
		}
		return null;
	}
}
