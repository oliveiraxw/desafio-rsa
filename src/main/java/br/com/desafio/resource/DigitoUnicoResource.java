package br.com.desafio.resource;

import javax.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.desafio.dto.request.DigitoUnicoRequestDTO;
import br.com.desafio.dto.response.DigitoUnicoResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RequestMapping("/digitoUnico")
@Tag(name = "Dígito Único Resource", description = "Disponibiliza operações relacionadas ao cálculo do dígito único")
public interface DigitoUnicoResource {
	
	@GetMapping("/{idUsuario}")
	@Operation(summary = "Recuperar os cálculos de dígito único realizados por um determinado usuário", responses = {
		      @ApiResponse(responseCode = "200", description = "Cálculos do dígito único recuperados com sucesso", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = DigitoUnicoResponseDTO.class)))),
		      @ApiResponse(responseCode = "400", description = "Erro na consulta dos cálculos do dígito único de um determinado usuário", content = @Content),
		      @ApiResponse(responseCode = "404", description = "Nenhum usuário ou cálculo de dígito único foi encontrado para o ID informado.", content = @Content),
		      @ApiResponse(responseCode = "500", description = "Erro interno no servidor", content = @Content)
		})
	ResponseEntity<?> recuperarDigitoUnico(@Parameter(description = "Identificador do usuário") @NotNull @PathVariable("idUsuario") Long idUsuario);
	
	@PostMapping("/{idUsuario}")
	@Operation(summary = "Calcular o dígito único dos números inteiros de uma string. Armazena os 10 últimos cálculos realizados independente do usuário. Se o cálculo já tiver sido realizado previamente por algum usuário, o cálculo não será realizado, pois já estará armazenado em cache.", 
			responses = {
		      @ApiResponse(responseCode = "200", description = "Dígito único calculado com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DigitoUnicoResponseDTO.class))),
		      @ApiResponse(responseCode = "400", description = "Erro no cálculo do dígito único", content = @Content),
		      @ApiResponse(responseCode = "404", description = "Nenhum usuário foi encontrado para o ID informado.", content = @Content),
		      @ApiResponse(responseCode = "500", description = "Erro interno no servidor", content = @Content) 
		})
	ResponseEntity<?> calcularDigitoUnico(@Parameter(description = "Identificador do usuário") @NotNull @PathVariable("idUsuario") Long idUsuario,
								  		  @RequestBody DigitoUnicoRequestDTO digitoUnicoRequest);
	
}