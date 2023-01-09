package com.attornatus.desafiojava.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.attornatus.desafiojava.models.Pessoa;
import com.attornatus.desafiojava.repositories.PessoaRepository;

@RestController
@RequestMapping(value = "/pessoa")
public class PessoaController {
	
	@Autowired
	private PessoaRepository repository;
	
	@PostMapping
	public Pessoa criarPessoa(@RequestBody Pessoa pessoa) {
		return repository.save(pessoa);
	}
	
	@GetMapping(value = "/{id}")
	public Pessoa buscarPorId(@PathVariable Long id) {
		return repository.findById(id).get();
	}
	
	@GetMapping
	public List<Pessoa> listarPessoas(){
		List<Pessoa> result = repository.findAll();
		return result;
	}
	
	@PutMapping(value = "/{id}")
	public Optional<Object> editarPessoa(@RequestBody Pessoa newPessoa, @PathVariable Long id) {
		return repository.findById(id).map(pessoa -> {
			pessoa.setNome(newPessoa.getNome());
			pessoa.setDataNascimento(newPessoa.getDataNascimento());
			return repository.save(pessoa);
		});
	}
}	
	

