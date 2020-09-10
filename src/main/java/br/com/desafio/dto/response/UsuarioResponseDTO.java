package br.com.desafio.dto.response;

import java.io.Serializable;

import br.com.desafio.model.Usuario;
import io.swagger.v3.oas.annotations.media.Schema;

public class UsuarioResponseDTO implements Serializable {
	
	private static final long serialVersionUID = 6319453264149090676L;
	
	@Schema(description = "Identificador do usuário")
	private Long id;
	
	@Schema(description = "Email do usuário")
	private String email;
	
	@Schema(description = "Nome do usuário")
	private String nome;
	
	public UsuarioResponseDTO() {
		super();
	}
	
	public UsuarioResponseDTO(Usuario usuario) {
		super();
		this.id = usuario.getId();
		this.email = usuario.getEmail();
		this.nome = usuario.getNome();
	}

	public UsuarioResponseDTO(Long id, String email, String nome) {
		super();
		this.id = id;
		this.email = email;
		this.nome = nome;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
}