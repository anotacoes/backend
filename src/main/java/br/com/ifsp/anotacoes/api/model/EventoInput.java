package br.com.ifsp.anotacoes.api.model;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventoInput {

	@NotNull
	private ContaIdInput conta;
	
	@NotBlank
	private String nome;
	
	@NotNull
	private Date dataInicio;
	
	@NotNull
	private Date dataFim;

}
