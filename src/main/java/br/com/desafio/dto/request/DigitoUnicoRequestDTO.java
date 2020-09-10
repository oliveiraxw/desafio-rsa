package br.com.desafio.dto.request;

import java.io.Serializable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import io.swagger.v3.oas.annotations.media.Schema;

public class DigitoUnicoRequestDTO implements Serializable {

	private static final long serialVersionUID = -6284657338720058477L;
	
	@Schema(description = "String que contém um número n")
	@NotBlank(message = "É necessário preencher o campo 'n'")
	private String n;
	
	@Schema(description = "Número multiplicador k")
	@NotNull(message = "É necessário preencher o campo 'k'")
	private Integer k;
	
	public DigitoUnicoRequestDTO() {
		super();
	}
	
	public DigitoUnicoRequestDTO(String n, Integer k) {
		super();
		this.n = n;
		this.k = k;
	}
	
	public String getN() {
		return n;
	}
	public void setN(String n) {
		this.n = n;
	}
	public Integer getK() {
		return k;
	}
	public void setK(Integer k) {
		this.k = k;
	}
	
}