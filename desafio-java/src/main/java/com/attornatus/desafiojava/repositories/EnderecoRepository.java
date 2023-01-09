package com.attornatus.desafiojava.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.attornatus.desafiojava.models.Endereco;
import com.attornatus.desafiojava.models.Pessoa;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
	
	public List<Endereco> findByPessoa(Pessoa pessoa);
}
