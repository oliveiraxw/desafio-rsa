package br.com.desafio.resource;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.desafio.dto.request.AtualizarEmailRequestDTO;
import br.com.desafio.dto.request.AtualizarNomeRequestDTO;
import br.com.desafio.dto.request.UsuarioRequestDTO;
import br.com.desafio.dto.response.UsuarioResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RequestMapping("/usuario")
@Tag(name = "Usuário Resource", description = "Disponibiliza operações relacionadas aos dados do usuário")
public interface UsuarioResource {
	
	@GetMapping
	@Operation(summary = "Retornar todos os usuários armazenados na base de dados. Os dados foram criptografados com a chave pública enviada pelo cliente, no momento do cadastro.", 
		responses = {
	      @ApiResponse(responseCode = "200", description = "Consulta realizada com sucesso", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = UsuarioResponseDTO.class)))),
	      @ApiResponse(responseCode = "400", description = "Falha na consulta dos usuários", content = @Content),
	      @ApiResponse(responseCode = "404", description = "Nenhum usuário foi encontrado.", content = @Content),
	      @ApiResponse(responseCode = "500", description = "Erro interno no servidor", content = @Content) 
		})
	ResponseEntity<?> buscarTodos();
	
	@GetMapping("/{id}")
	@Operation(summary = "Retornar o usuário armazenado na base de dados. Os dados foram criptografados com a chave pública enviada pelo cliente, no momento do cadastro.", 
		responses = {
	      @ApiResponse(responseCode = "200", description = "Consulta realizada com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioResponseDTO.class))),
	      @ApiResponse(responseCode = "400", description = "Falha na consulta do usuário", content = @Content),
	      @ApiResponse(responseCode = "404", description = "Nenhum usuário foi encontrado para o ID informado.", content = @Content),
	      @ApiResponse(responseCode = "500", description = "Erro interno no servidor", content = @Content) 
		})
	ResponseEntity<?> buscarPorId(@Parameter(description = "Identificador do usuário") @NotBlank @PathVariable("id") Long id);
	
	@PostMapping
	@Operation(summary = "Criptografar e cadastrar os dados de um usuário na base de dados. Os dados serão criptografados com a chave pública enviada pelo cliente.", 
		responses = {
	      @ApiResponse(responseCode = "201", description = "Cadastro realizado com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioResponseDTO.class))),
	      @ApiResponse(responseCode = "400", description = "Falha no cadastro do usuário", content = @Content),
	      @ApiResponse(responseCode = "500", description = "Erro interno no servidor", content = @Content) 
		})
	ResponseEntity<?> cadastrar(@Valid @RequestBody UsuarioRequestDTO usuarioRequest,
			  				    @Parameter(description = "Chave Pública do usuário") @RequestHeader(value = "ChavePublica", required = true) String chavePublica);
	
	@PutMapping
	@Operation(summary = "Atualizar o email e o nome de um usuário na base de dados. Os dados serão criptografados com a chave pública enviada pelo cliente, no momento do cadastro.", 
		responses = {
	      @ApiResponse(responseCode = "201", description = "Atualização realizada com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioResponseDTO.class))),
	      @ApiResponse(responseCode = "400", description = "Falha no cadastro do usuário", content = @Content),
	      @ApiResponse(responseCode = "404", description = "Nenhum usuário foi encontrado para o ID informado.", content = @Content),
	      @ApiResponse(responseCode = "500", description = "Erro interno no servidor", content = @Content) 
		})
	ResponseEntity<?> atualizar(@Parameter(description = "Identificador") @NotBlank @PathVariable("id") Long id,
								@Valid @RequestBody UsuarioRequestDTO usuarioRequest);
		
	@PatchMapping("/{id}/email")
	@Operation(summary = "Atualizar o email de um usuário na base de dados. Os dados serão criptografados com a chave pública enviada pelo cliente, no momento do cadastro.", 
		responses = {
		      @ApiResponse(responseCode = "200", description = "Email atualizado com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioResponseDTO.class))),
		      @ApiResponse(responseCode = "400", description = "Falha na atualização do email do usuário", content = @Content),
		      @ApiResponse(responseCode = "404", description = "Nenhum usuário foi encontrado para o ID informado.", content = @Content),
		      @ApiResponse(responseCode = "500", description = "Erro interno no servidor", content = @Content) 
		})
	ResponseEntity<?> atualizarEmail(@Parameter(description = "Identificador") @NotBlank @PathVariable("id") Long id,
			 						 @Valid @RequestBody AtualizarEmailRequestDTO atualizarEmailRequest);
	
	@PatchMapping("/{id}/nome")
	@Operation(summary = "Atualizar o nome de um usuário na base de dados. Os dados serão criptografados com a chave pública enviada pelo cliente, no momento do cadastro.", 
		responses = {
		      @ApiResponse(responseCode = "200", description = "Nome do usuário atualizado com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioResponseDTO.class))),
		      @ApiResponse(responseCode = "400", description = "Falha na atualização do nome do usuário", content = @Content),
		      @ApiResponse(responseCode = "404", description = "Nenhum usuário foi encontrado para o ID informado.", content = @Content),
		      @ApiResponse(responseCode = "500", description = "Erro interno no servidor", content = @Content) 
		})
	ResponseEntity<?> atualizarNome(@Parameter(description = "Identificador") @NotBlank @PathVariable("id") Long id,
									@Valid @RequestBody AtualizarNomeRequestDTO atualizarNomeRequest);
	
	@DeleteMapping("/{id}")
	@Operation(summary = "Deletar o usuário armazenado na base de dados.", responses = {
	      @ApiResponse(responseCode = "200", description = "Deletado com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
	      @ApiResponse(responseCode = "400", description = "Falha ao deletar o usuário", content = @Content),
	      @ApiResponse(responseCode = "404", description = "Nenhum usuário foi encontrado para o ID informado.", content = @Content),
	      @ApiResponse(responseCode = "500", description = "Erro interno no servidor", content = @Content) 
		})
	ResponseEntity<?> deletarPorId(@Parameter(description = "Identificador do usuário") @NotBlank @PathVariable("id") Long id);
	
}