package br.com.desafio.resource;

import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.desafio.dto.request.DescriptografiaRequestDTO;
import br.com.desafio.dto.response.ChavePrivadaPublicaResponseDTO;
import br.com.desafio.dto.response.DescriptografiaResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RequestMapping("/cliente")
@Tag(name = "Client-Side Criptografia Resource", description = "Disponibiliza operações para simulação de um client-side")
public interface ClientSideCriptografiaResource {
	
	@GetMapping("/parChaves")
	@Operation(summary = "Retornar uma par de chaves RSA (privada e pública)", responses = {
	      @ApiResponse(responseCode = "200", description = "Par de chaves RSA gerados com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ChavePrivadaPublicaResponseDTO.class))),
	      @ApiResponse(responseCode = "400", description = "Falha ao gerar par de chaves RSA", content = @Content),
	      @ApiResponse(responseCode = "500", description = "Erro interno no servidor", content = @Content) 
		})
	ResponseEntity<?> gerarParChavesRSA();
	
	@PostMapping("/descriptografia")
	@Operation(summary = "Descriptografar uma informação", responses = {
	      @ApiResponse(responseCode = "200", description = "Informação descriptografada com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DescriptografiaResponseDTO.class))),
	      @ApiResponse(responseCode = "400", description = "Falha na descriptografia da informação", content = @Content),
	      @ApiResponse(responseCode = "500", description = "Erro interno no servidor", content = @Content) 
		})
	ResponseEntity<?> descriptografar(@Valid @RequestBody DescriptografiaRequestDTO descriptografiaRequest,
			    					  @Parameter(description = "Chave Privada do usuário") @RequestHeader(value = "ChavePrivada", required = true) String chavePrivada);
	
}