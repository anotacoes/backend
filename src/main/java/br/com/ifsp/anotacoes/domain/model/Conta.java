package br.com.ifsp.anotacoes.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @EqualsAndHashCode(of = "id")
@Entity public class Conta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank @Size(max = 60)
	private String nome;
	
	@NotBlank @Email @Size(max = 255)
	private String email;
	
	@NotBlank @Size(max = 20)
	private String login;

	@NotBlank @Size(max = 20)
	private String senha;
	
}
