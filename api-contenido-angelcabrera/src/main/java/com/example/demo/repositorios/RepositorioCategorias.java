package com.example.demo.repositorios;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entidades.Categoria;


public interface RepositorioCategorias extends CrudRepository<Categoria, Long>{

}
