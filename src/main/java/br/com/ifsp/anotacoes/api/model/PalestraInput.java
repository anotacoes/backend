package br.com.ifsp.anotacoes.api.model;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PalestraInput {
	
	@NotNull
	private ContaAbstractModel conta;

	@NotNull
	private EventoAbstractModel evento;
	
	@NotNull
	private PalestranteAbstractModel palestrante;
	
	@NotBlank
	private String nome;
	
	@NotNull
	private Date data;
}
