package br.com.ifsp.anotacoes.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ifsp.anotacoes.domain.model.Palestrante;

@Repository
public interface PalestranteRepository extends JpaRepository<Palestrante, Long> {

	Palestrante findByNome(String nome);

}
