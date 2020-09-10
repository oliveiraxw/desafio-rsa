package br.com.desafio;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.desafio.dto.request.DescriptografiaRequestDTO;
import br.com.desafio.dto.request.UsuarioRequestDTO;
import br.com.desafio.dto.response.ChavePrivadaPublicaResponseDTO;
import br.com.desafio.dto.response.DescriptografiaResponseDTO;
import br.com.desafio.dto.response.UsuarioResponseDTO;
import br.com.desafio.resource.ClientSideCriptografiaResource;
import br.com.desafio.resource.UsuarioResource;

@SpringBootTest
public class FluxoCadastroCriptografiaTests {

	@Autowired
	private ClientSideCriptografiaResource clienteTesteCriptografiaResource;
	
	@Autowired
	private UsuarioResource usuarioResource;
	
	@Test
	public ChavePrivadaPublicaResponseDTO testeGerarParChavesRSA() {
		ChavePrivadaPublicaResponseDTO parChaves = null;
		ResponseEntity<?> response = clienteTesteCriptografiaResource.gerarParChavesRSA();
		assertEquals(HttpStatus.OK, response.getStatusCode());
		if(response.getStatusCode() == HttpStatus.OK) {
			parChaves = (ChavePrivadaPublicaResponseDTO) response.getBody();
			assertThat(parChaves.getChavePrivada()).isNotBlank();
			assertThat(parChaves.getChavePublica()).isNotBlank();
		}
		return parChaves;
	}
	
	@Test
	public Map<String, UsuarioResponseDTO> cadastrarUsuario() {
		UsuarioRequestDTO usuarioRequest = new UsuarioRequestDTO("João", "joao@email.com");
		UsuarioResponseDTO usuarioResponse = null;
		ChavePrivadaPublicaResponseDTO parChaves = testeGerarParChavesRSA();
		
		ResponseEntity<?> response = usuarioResource.cadastrar(usuarioRequest, parChaves.getChavePublica());
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		if(response.getStatusCode() == HttpStatus.CREATED) {
			usuarioResponse = (UsuarioResponseDTO) response.getBody();
			assertThat(usuarioResponse.getNome()).isNotBlank();
			assertThat(usuarioResponse.getEmail()).isNotBlank();
		}
		Map<String, UsuarioResponseDTO> chaveUsuario = new HashMap<>();
		chaveUsuario.put(parChaves.getChavePrivada(), usuarioResponse);
		return chaveUsuario;
	}
	
	@Test
	public void descriptografarUsuario() {
		Map<String, UsuarioResponseDTO> chaveUsuario = cadastrarUsuario();
	    Map.Entry<String,UsuarioResponseDTO> entry = chaveUsuario.entrySet().iterator().next();
		String chavePrivada = entry.getKey();
		UsuarioResponseDTO usuarioResponse = entry.getValue();
		
		//Descriptografa o email
		ResponseEntity<?> emailResponse = clienteTesteCriptografiaResource.descriptografar(new DescriptografiaRequestDTO(usuarioResponse.getEmail()), chavePrivada);
		assertEquals(HttpStatus.OK, emailResponse.getStatusCode());
		if(emailResponse.getStatusCode() == HttpStatus.OK) {
			DescriptografiaResponseDTO descriptografiaEmailResponse = (DescriptografiaResponseDTO) emailResponse.getBody();
			assertThat(descriptografiaEmailResponse.getInformacaoDescriptografada()).isNotBlank();
		}
		
		//Descriptografa o nome
		ResponseEntity<?> nomeResponse = clienteTesteCriptografiaResource.descriptografar(new DescriptografiaRequestDTO(usuarioResponse.getNome()), chavePrivada);
		assertEquals(HttpStatus.OK, nomeResponse.getStatusCode());
		if(nomeResponse.getStatusCode() == HttpStatus.OK) {
			DescriptografiaResponseDTO descriptografiaNomeResponse = (DescriptografiaResponseDTO) nomeResponse.getBody();
			assertThat(descriptografiaNomeResponse.getInformacaoDescriptografada()).isNotBlank();
		}
	}
	
}