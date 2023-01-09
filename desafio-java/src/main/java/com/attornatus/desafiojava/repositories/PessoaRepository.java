package com.attornatus.desafiojava.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.attornatus.desafiojava.models.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
	
	Pessoa findByNome (String nome);
	
}
