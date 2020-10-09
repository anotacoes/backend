package br.com.ifsp.anotacoes.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ifsp.anotacoes.domain.model.Anotacao;
import br.com.ifsp.anotacoes.domain.model.Conta;

@Repository
public interface AnotacaoRepository extends JpaRepository<Anotacao, Long> {

	List<Anotacao> findByConta(Conta conta);
	
}
