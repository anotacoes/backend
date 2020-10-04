package br.com.ifsp.anotacoes.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ifsp.anotacoes.domain.exception.ServicoException;
import br.com.ifsp.anotacoes.domain.model.Anotacao;
import br.com.ifsp.anotacoes.domain.model.Conta;
import br.com.ifsp.anotacoes.domain.model.Palestra;
import br.com.ifsp.anotacoes.domain.repository.AnotacaoRepository;
import br.com.ifsp.anotacoes.domain.repository.ContaRepository;
import br.com.ifsp.anotacoes.domain.repository.PalestraRepository;

@Service
public class RegistroAnotacaoService {

	@Autowired
	private AnotacaoRepository anotacaoRepository;

	@Autowired
	private PalestraRepository palestraRepository;

	@Autowired
	private ContaRepository contaRepository;

	public Anotacao salvar(Anotacao anotacao) {
		Palestra palestra = palestraRepository.findById(anotacao.getPalestra().getId())
				.orElseThrow(() -> new ServicoException("Palestra não encontrada"));
		Conta conta = contaRepository.findById(anotacao.getConta().getId())
				.orElseThrow(() -> new ServicoException("Conta não encontrada"));

		anotacao.setPalestra(palestra);
		anotacao.setConta(conta);

		return anotacaoRepository.save(anotacao);
	}

}
