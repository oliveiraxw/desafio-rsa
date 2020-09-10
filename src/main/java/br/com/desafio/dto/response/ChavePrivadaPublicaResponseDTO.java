package br.com.desafio.dto.response;

import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;

public class ChavePrivadaPublicaResponseDTO implements Serializable {

	private static final long serialVersionUID = 7645001433117763786L;
	
	@Schema(description = "Chave Privada do usuário")
	private String chavePrivada;
	
	@Schema(description = "Chave Pública do usuário")
	private String chavePublica;
	
	public ChavePrivadaPublicaResponseDTO() {
		super();
	}
	
	public ChavePrivadaPublicaResponseDTO(String chavePrivada, String chavePublica) {
		super();
		this.chavePrivada = chavePrivada;
		this.chavePublica = chavePublica;
	}
	
	public String getChavePrivada() {
		return chavePrivada;
	}
	public void setChavePrivada(String chavePrivada) {
		this.chavePrivada = chavePrivada;
	}
	public String getChavePublica() {
		return chavePublica;
	}
	public void setChavePublica(String chavePublica) {
		this.chavePublica = chavePublica;
	}
	
}