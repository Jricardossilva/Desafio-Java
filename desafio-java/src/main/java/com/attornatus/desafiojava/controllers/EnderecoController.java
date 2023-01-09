package com.attornatus.desafiojava.controllers;

import java.util.List;
import java.util.Optional;

import com.attornatus.desafiojava.models.Pessoa;
import com.attornatus.desafiojava.repositories.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.attornatus.desafiojava.exceptions.Avisos;
import com.attornatus.desafiojava.models.Endereco;
import com.attornatus.desafiojava.repositories.EnderecoRepository;

@RestController
@RequestMapping(value = "/endereco")
public class EnderecoController {
	
	@Autowired
	private EnderecoRepository repository;
	
	@Autowired
	private PessoaRepository pessoaRepository;

	@GetMapping
	public List<Endereco> listarEndereco(){
		List<Endereco> result = repository.findAll();
		return result;
	}

	@PostMapping
	public ResponseEntity criarEndereco(@RequestBody Endereco endereco, @RequestParam("pessoaId") Long pessoaId){
		if (pessoaId == null)
			return ResponseEntity.badRequest().build();
		Optional<Pessoa> pessoa = pessoaRepository.findById(pessoaId);
		if (pessoa.isEmpty())
			return ResponseEntity.badRequest().body(Avisos.PessoaNaoEncontrada);
		endereco.setPessoa(pessoa.get());
		repository.saveAndFlush(endereco);
		return ResponseEntity.noContent().build();
	}

	@PostMapping(value = "/marcarPrincipal")
	public ResponseEntity marcarPrincipal(@RequestParam("pessoaId") Long pessoaId, @RequestParam("enderecoId") Long enderecoId){
		if (pessoaId == null || enderecoId == null)
			return ResponseEntity.badRequest().build();
		Optional<Pessoa> pessoa = pessoaRepository.findById(pessoaId);
		if (pessoa.isEmpty())
			return ResponseEntity.badRequest().body(Avisos.PessoaNaoEncontrada);
		Optional<Endereco> opt = pessoa.get().getEnderecos().stream().filter(end -> end.getId().equals(enderecoId)).findFirst();
		if (opt.isEmpty())
			return ResponseEntity.badRequest().body(Avisos.EnderecoNaoEncontrado);
		Endereco endereco = opt.get();
		endereco.setPrincipal(true);
		repository.saveAndFlush(endereco);
		return ResponseEntity.noContent().build();
	}
}
