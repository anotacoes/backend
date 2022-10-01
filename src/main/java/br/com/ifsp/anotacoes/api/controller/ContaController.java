package br.com.ifsp.anotacoes.api.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifsp.anotacoes.api.model.ContaAbstractLogin;
import br.com.ifsp.anotacoes.domain.exception.ServicoException;
import br.com.ifsp.anotacoes.domain.model.Conta;
import br.com.ifsp.anotacoes.domain.model.Evento;
import br.com.ifsp.anotacoes.domain.repository.ContaRepository;
import br.com.ifsp.anotacoes.domain.repository.EventoRepository;
import br.com.ifsp.anotacoes.domain.service.RegistroContaService;

@RestController
@RequestMapping("/contas")
public class ContaController {

	@Autowired
	private ContaRepository contaRepository;

	@Autowired
	private EventoRepository eventoRepository;

	@Autowired
	private RegistroContaService registroConta;

	@GetMapping
	public List<Conta> listar() {
		return contaRepository.findAll();
	}

	@GetMapping("/{contaId}")
	public ResponseEntity<Conta> buscar(@PathVariable Long contaId) {
		Optional<Conta> conta = contaRepository.findById(contaId);

		if (conta.isPresent())
			return ResponseEntity.ok(conta.get());

		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Conta salvar(@Valid @RequestBody Conta conta) {
		return registroConta.salvar(conta);
	}

	@PutMapping("/{contaId}")
	public ResponseEntity<Conta> atualizar(@PathVariable Long contaId, @Valid @RequestBody Conta conta) {

		if (!contaRepository.existsById(contaId)) {
			return ResponseEntity.notFound().build();
		}

		conta.setId(contaId);
		conta = registroConta.salvar(conta);

		return ResponseEntity.ok(conta);
	}

	@DeleteMapping("/{contaId}")
	public ResponseEntity<Void> delete(@PathVariable Long contaId) {

		if (!contaRepository.existsById(contaId)) {
			return ResponseEntity.notFound().build();
		}

		contaRepository.deleteById(contaId);

		return ResponseEntity.noContent().build();
	}

	@GetMapping("/login")
	public ResponseEntity<Conta> login(@RequestBody ContaAbstractLogin abstractLogin) {
		Conta conta = contaRepository.findByLogin(abstractLogin.getLogin());

		if (conta != null) {
			if (conta.getSenha().equals(abstractLogin.getSenha()))
				return ResponseEntity.ok(conta);
			else
				throw new ServicoException("Senha errada!");
		}

		return ResponseEntity.notFound().build();
	}

	@GetMapping("/{contaId}/eventos")
	public List<Evento> listarEventosConta(@PathVariable Long contaId) {
		Conta conta = contaRepository.findById(contaId).orElseThrow(() -> new ServicoException("Conta não encontrada"));

		return eventoRepository.findByConta(conta);
	}

}
