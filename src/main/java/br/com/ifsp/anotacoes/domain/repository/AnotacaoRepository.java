package br.com.ifsp.anotacoes.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ifsp.anotacoes.domain.model.Anotacao;

@Repository
public interface AnotacaoRepository extends JpaRepository<Anotacao, Long> {

}
