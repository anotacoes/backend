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

import br.com.ifsp.anotacoes.domain.model.Conta;
import br.com.ifsp.anotacoes.domain.repository.ContaRepository;
import br.com.ifsp.anotacoes.domain.service.RegistroContaService;

@RestController
@RequestMapping("/contas")
public class ContaController {

	@Autowired
	private ContaRepository contaRepository;
	
	@Autowired
	private RegistroContaService registroConta;
	
	@GetMapping
	public List<Conta> listar() {
		return contaRepository.findAll();
	}
	
	@GetMapping("/{contaId}")
	public ResponseEntity<Conta> buscar(@PathVariable Long contaId) {
		Optional<Conta> conta = contaRepository.findById(contaId);
		
		if(conta.isPresent())
			return ResponseEntity.ok(conta.get());
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Conta salvar(@Valid @RequestBody Conta conta) {
		return registroConta.save(conta);
	}
	
}