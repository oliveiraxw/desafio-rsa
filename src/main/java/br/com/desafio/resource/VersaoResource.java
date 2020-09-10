package br.com.desafio.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.desafio.dto.response.VersaoDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RequestMapping("/digitoUnico")
@Tag(name = "Versão Resource", description = "Disponibiliza operação relacionada a versão da API")
public interface VersaoResource {
	
	@GetMapping
	@Operation(summary = "Retornar a versão da API", responses = {
		      @ApiResponse(responseCode = "200", description = "Versão da API retornada com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = VersaoDTO.class))),
		      @ApiResponse(responseCode = "400", description = "Erro na consulta da versão da API", content = @Content),
		      @ApiResponse(responseCode = "500", description = "Erro interno no servidor", content = @Content) 
		})
	public ResponseEntity<VersaoDTO> versao();
	
}
