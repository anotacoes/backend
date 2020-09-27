package br.com.ifsp.anotacoes.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ifsp.anotacoes.domain.exception.ServicoException;
import br.com.ifsp.anotacoes.domain.model.Conta;
import br.com.ifsp.anotacoes.domain.model.Evento;
import br.com.ifsp.anotacoes.domain.repository.ContaRepository;
import br.com.ifsp.anotacoes.domain.repository.EventoRepository;

@Service
public class RegistroEventoService {

	@Autowired
	private ContaRepository contaRepository;

	@Autowired
	private EventoRepository eventoRepository;

	public Evento salvar(Evento evento) {

		Conta conta = contaRepository.findById(evento.getConta().getId())
				.orElseThrow(() -> new ServicoException("Conta não encontrado"));

		evento.setConta(conta);

		return eventoRepository.save(evento);
	}

}
