package br.com.ifsp.anotacoes.api.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifsp.anotacoes.domain.exception.ServicoException;
import br.com.ifsp.anotacoes.domain.model.Palestrante;
import br.com.ifsp.anotacoes.domain.repository.PalestranteRepository;

@RestController
@RequestMapping("/palestrantes")
public class PalestranteController {

	@Autowired
	private PalestranteRepository palestranteRepository;

	@GetMapping
	public List<Palestrante> listar() {
		return palestranteRepository.findAll();
	}

	@GetMapping("/{palestranteId}")
	public ResponseEntity<Palestrante> buscar(@PathVariable Long palestranteId) {
		Optional<Palestrante> palestrante = palestranteRepository.findById(palestranteId);

		if (palestrante.isPresent())
			return ResponseEntity.ok(palestrante.get());

		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Palestrante salvar(@Valid @RequestBody Palestrante palestrante) {
		Palestrante palestranteExiste = palestranteRepository.findByNome(palestrante.getNome());

		if (palestranteExiste != null && !palestranteExiste.equals(palestrante))
			throw new ServicoException("Já existe um palestrante cadastrado com esse nome");

		return palestranteRepository.save(palestrante);
	}
}
