package br.com.ifsp.anotacoes.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ifsp.anotacoes.domain.model.Conta;
import br.com.ifsp.anotacoes.domain.model.Evento;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {

	Evento findByNome(String nome);

	List<Evento> findByConta(Conta conta);

}
