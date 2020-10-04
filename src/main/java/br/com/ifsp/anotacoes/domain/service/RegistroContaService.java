package br.com.ifsp.anotacoes.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ifsp.anotacoes.domain.exception.ServicoException;
import br.com.ifsp.anotacoes.domain.model.Conta;
import br.com.ifsp.anotacoes.domain.repository.ContaRepository;

@Service
public class RegistroContaService {

	@Autowired
	private ContaRepository contaRepository;

	public Conta save(Conta conta) {
		Conta contaExisteEmail = contaRepository.findByEmail(conta.getEmail());
		Conta contaExisteLogin = contaRepository.findByLogin(conta.getLogin());
		
		if (contaExisteEmail != null && !contaExisteEmail.equals(conta))
			throw new ServicoException("Já existe conta cadastrada com esse email");
		
		if (contaExisteLogin != null && !contaExisteLogin.equals(conta))
			throw new ServicoException("Já existe conta cadastrada com esse login");

		return contaRepository.save(conta);
	}

}
