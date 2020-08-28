package br.com.ifsp.anotacoes.api.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@JsonInclude(Include.NON_NULL)
public class Problema {

	private Integer status;
	private OffsetDateTime dataHora;
	private String titulo;
	private List<Campo> campos;
	
	@AllArgsConstructor @Getter @Setter
	public static class Campo {
		
		private String nome;
		private String mensagem;
	}
	
}
