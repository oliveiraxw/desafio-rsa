package br.com.desafio.dto.request;

import java.io.Serializable;
import javax.validation.constraints.NotBlank;
import io.swagger.v3.oas.annotations.media.Schema;

public class DescriptografiaRequestDTO implements Serializable {

	private static final long serialVersionUID = -9169661549045669185L;
	
	@Schema(description = "Informação que será descriptografada")
	@NotBlank(message = "É necessário preencher o campo 'informacaoCriptografada'")
	private String informacaoCriptografada;

	public DescriptografiaRequestDTO() {
		super();
	}
	
	public DescriptografiaRequestDTO(String informacaoCriptografada) {
		super();
		this.informacaoCriptografada = informacaoCriptografada;
	}

	public String getInformacaoCriptografada() {
		return informacaoCriptografada;
	}

	public void setInformacaoCriptografada(String informacaoCriptografada) {
		this.informacaoCriptografada = informacaoCriptografada;
	}

}