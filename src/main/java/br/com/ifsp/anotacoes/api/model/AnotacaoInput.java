package br.com.ifsp.anotacoes.api.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnotacaoInput {

	@NotNull
	private ContaAbstractModel conta;

	@NotNull
	private PalestraAbstractModel palestra;

	@NotBlank
	private String texto;

}
