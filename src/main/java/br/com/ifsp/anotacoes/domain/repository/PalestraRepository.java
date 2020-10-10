package br.com.ifsp.anotacoes.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ifsp.anotacoes.domain.model.Conta;
import br.com.ifsp.anotacoes.domain.model.Evento;
import br.com.ifsp.anotacoes.domain.model.Palestra;

@Repository
public interface PalestraRepository extends JpaRepository<Palestra, Long> {

	Palestra findByNome(String nome);

	List<Palestra> findByConta(Conta conta);
	
	List<Palestra> findByEvento(Evento evento);
	

}
