package br.com.ifsp.anotacoes.api.model;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContaAbstractLogin {

	@NotBlank
	private String login;

	@NotBlank
	private String senha;
}
