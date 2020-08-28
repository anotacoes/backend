package br.com.ifsp.anotacoes.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ifsp.anotacoes.domain.model.Conta;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {

	Conta findByEmail(String email);
	
}
