package br.com.desafio.dto.request;

import java.io.Serializable;
import javax.validation.constraints.NotBlank;
import io.swagger.v3.oas.annotations.media.Schema;

public class AtualizarEmailRequestDTO implements Serializable {
	
	private static final long serialVersionUID = -7767610597009758663L;
	
	@Schema(description = "Novo email do usuário")
	@NotBlank(message = "É necessário preencher o campo 'novoEmail'")
	private String novoEmail;

	public AtualizarEmailRequestDTO() {
		super();
	}
	
	public AtualizarEmailRequestDTO(String novoEmail) {
		super();
		this.novoEmail = novoEmail;
	}

	public String getNovoEmail() {
		return novoEmail;
	}

	public void setNovoEmail(String email) {
		this.novoEmail = email;
	}
	
}