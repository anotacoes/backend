package br.com.ifsp.anotacoes.api.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
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

import br.com.ifsp.anotacoes.api.model.PalestraInput;
import br.com.ifsp.anotacoes.domain.exception.ServicoException;
import br.com.ifsp.anotacoes.domain.model.Palestra;
import br.com.ifsp.anotacoes.domain.repository.PalestraRepository;
import br.com.ifsp.anotacoes.domain.service.RegistroPalestraService;

@RestController
@RequestMapping("/eventos/{eventoId}/palestras")
public class PalestraController {

	@Autowired
	private PalestraRepository palestraRepository;

	@Autowired
	private RegistroPalestraService registroPalestraService;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping
	public List<Palestra> listar() {
		return palestraRepository.findAll();
	}

	@GetMapping("/{palestraId}")
	public ResponseEntity<Palestra> buscar(@PathVariable Long palestraId) {
		Optional<Palestra> palestra = palestraRepository.findById(palestraId);

		if (palestra.isPresent())
			return ResponseEntity.ok(palestra.get());

		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Palestra salvar(@Valid @RequestBody PalestraInput palestraInput) {

		Palestra palestra = toEntity(palestraInput);

		Palestra palestraExiste = palestraRepository.findByNome(palestra.getNome());

		if (palestraExiste != null && !palestraExiste.equals(palestra))
			throw new ServicoException("Já existe uma palestra cadastrada com esse nome");

		return registroPalestraService.salvar(palestra);
	}

	private Palestra toEntity(@Valid PalestraInput palestraInput) {
		return modelMapper.map(palestraInput, Palestra.class);
	}
}
