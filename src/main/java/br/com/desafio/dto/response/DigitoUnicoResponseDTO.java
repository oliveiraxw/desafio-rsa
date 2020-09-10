package br.com.desafio.dto.response;

import java.io.Serializable;

import br.com.desafio.model.DigitoUnico;
import io.swagger.v3.oas.annotations.media.Schema;

public class DigitoUnicoResponseDTO implements Serializable {
	
	private static final long serialVersionUID = -6881727032519692594L;
	
	@Schema(description = "Identificador do cálculo do dígito único")
	private Long id;
	
	@Schema(description = "String que contém um número n")
	private String n;
	
	@Schema(description = "Número multiplicador k")
	private Integer k;
	
	@Schema(description = "Resultado do dígito único calculado")
	private Integer resultado;
	
	public DigitoUnicoResponseDTO() {
		super();
	}
	
	public DigitoUnicoResponseDTO(DigitoUnico digitoUnico) {
		this.id = digitoUnico.getId();
		this.n = digitoUnico.getN();
		this.k = digitoUnico.getK();
		this.resultado = digitoUnico.getResultado();
	}

	public DigitoUnicoResponseDTO(Long id, String n, Integer k, Integer resultado) {
		super();
		this.id = id;
		this.n = n;
		this.k = k;
		this.resultado = resultado;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Integer getResultado() {
		return resultado;
	}

	public void setResultado(Integer resultado) {
		this.resultado = resultado;
	}

}