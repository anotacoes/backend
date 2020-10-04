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

import br.com.ifsp.anotacoes.api.model.AnotacaoInput;
import br.com.ifsp.anotacoes.domain.model.Anotacao;
import br.com.ifsp.anotacoes.domain.repository.AnotacaoRepository;
import br.com.ifsp.anotacoes.domain.service.RegistroAnotacaoService;

@RestController
@RequestMapping("/anotacoes")
public class AnotacaoController {

	@Autowired
	private AnotacaoRepository anotacaoRepository;

	@Autowired
	private RegistroAnotacaoService registroAnotacaoService;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping
	public List<Anotacao> listar() {
		return anotacaoRepository.findAll();
	}

	@GetMapping("/{anotacaoId}")
	public ResponseEntity<Anotacao> buscar(@PathVariable Long anotacaoId) {
		Optional<Anotacao> anotacao = anotacaoRepository.findById(anotacaoId);

		if (anotacao.isPresent())
			return ResponseEntity.ok(anotacao.get());

		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Anotacao salvar(@Valid @RequestBody AnotacaoInput anotacaoInput) {

		Anotacao anotacao = toEntity(anotacaoInput);

		return registroAnotacaoService.salvar(anotacao);
	}

	private Anotacao toEntity(@Valid AnotacaoInput anotacaoInput) {
		return modelMapper.map(anotacaoInput, Anotacao.class);
	}

}
