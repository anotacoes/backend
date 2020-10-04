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

import br.com.ifsp.anotacoes.api.model.EventoInput;
import br.com.ifsp.anotacoes.domain.exception.ServicoException;
import br.com.ifsp.anotacoes.domain.model.Evento;
import br.com.ifsp.anotacoes.domain.repository.EventoRepository;
import br.com.ifsp.anotacoes.domain.service.RegistroEventoService;

@RestController
@RequestMapping("/eventos")
public class EventoController {

	@Autowired
	private EventoRepository eventoRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private RegistroEventoService registroEventoService;

	@GetMapping
	public List<Evento> listar() {
		return eventoRepository.findAll();
	}

	@GetMapping("/{idEvento}")
	public ResponseEntity<Evento> buscar(@PathVariable Long idEvento) {
		Optional<Evento> evento = eventoRepository.findById(idEvento);

		if (evento.isPresent())
			return ResponseEntity.ok(evento.get());

		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Evento salvar(@Valid @RequestBody EventoInput eventoInput) {

		Evento evento = toEntity(eventoInput);

		Evento eventoExiste = eventoRepository.findByNome(evento.getNome());

		if (eventoExiste != null && !eventoExiste.equals(evento))
			throw new ServicoException("Já existe evento cadastrado com esse nome");

		return registroEventoService.salvar(evento);
	}

	private Evento toEntity(EventoInput eventoInput) {
		return modelMapper.map(eventoInput, Evento.class);
	}

}
