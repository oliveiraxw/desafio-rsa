package br.com.desafio.dto.response;

import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;

public class VersaoDTO implements Serializable {
	
	private static final long serialVersionUID = 2449572217228988981L;
	
	@Schema(description = "Versão da API")
	private String versao;

	public VersaoDTO() {
		super();
	}

	public VersaoDTO(String versao) {
		super();
		this.versao = versao;
	}

	public String getVersao() {
		return versao;
	}

	public void setVersao(String versao) {
		this.versao = versao;
	}
	
}