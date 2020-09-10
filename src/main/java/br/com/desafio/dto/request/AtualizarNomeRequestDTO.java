package br.com.desafio.dto.request;

import java.io.Serializable;
import javax.validation.constraints.NotBlank;
import io.swagger.v3.oas.annotations.media.Schema;

public class AtualizarNomeRequestDTO implements Serializable {

	private static final long serialVersionUID = -8839709831958662580L;
	
	@Schema(description = "Novo nome do usuário")
	@NotBlank(message = "É necessário preencher o campo 'novoNome'")
	private String novoNome;

	public AtualizarNomeRequestDTO() {
		super();
	}
	
	public AtualizarNomeRequestDTO(String novoNome) {
		super();
		this.novoNome = novoNome;
	}

	public String getNovoNome() {
		return novoNome;
	}

	public void setNovoNome(String novoNome) {
		this.novoNome = novoNome;
	}
	
}