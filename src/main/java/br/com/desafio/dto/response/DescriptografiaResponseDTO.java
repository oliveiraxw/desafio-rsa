package br.com.desafio.dto.response;

import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;

public class DescriptografiaResponseDTO implements Serializable {

	private static final long serialVersionUID = 8808224240670396730L;
	
	@Schema(description = "Informação descriptografada")
	private String informacaoDescriptografada;

	public DescriptografiaResponseDTO() {
		super();
	}
	
	public DescriptografiaResponseDTO(String informacaoDescriptografada) {
		super();
		this.informacaoDescriptografada = informacaoDescriptografada;
	}

	public String getInformacaoDescriptografada() {
		return informacaoDescriptografada;
	}

	public void setInformacaoDescriptografada(String informacaoDescriptografada) {
		this.informacaoDescriptografada = informacaoDescriptografada;
	}
	
}