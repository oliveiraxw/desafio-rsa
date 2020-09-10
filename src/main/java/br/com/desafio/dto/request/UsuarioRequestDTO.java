package br.com.desafio.dto.request;

import java.io.Serializable;
import javax.validation.constraints.NotBlank;
import io.swagger.v3.oas.annotations.media.Schema;

public class UsuarioRequestDTO implements Serializable {
	
	private static final long serialVersionUID = 5959537110965576253L;
	
	@Schema(description = "Nome do usuário")
	@NotBlank(message = "É necessário preencher o campo 'nome'")
	private String nome;
	
	@Schema(description = "Email do usuário")
	@NotBlank(message = "É necessário preencher o campo 'email'")
	private String email;
	
	public UsuarioRequestDTO() {
		super();
	}
	
	public UsuarioRequestDTO(String nome, String email) {
		super();
		this.nome = nome;
		this.email = email;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}