package br.com.desafio.resource.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import br.com.desafio.dto.request.AtualizarEmailRequestDTO;
import br.com.desafio.dto.request.AtualizarNomeRequestDTO;
import br.com.desafio.dto.request.UsuarioRequestDTO;
import br.com.desafio.dto.response.UsuarioResponseDTO;
import br.com.desafio.model.Usuario;
import br.com.desafio.resource.UsuarioResource;
import br.com.desafio.service.UsuarioService;

@RestController
public class UsuarioResourceImpl implements UsuarioResource {
	
	Logger logger = LoggerFactory.getLogger(UsuarioResourceImpl.class);
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Override
	public ResponseEntity<?> buscarTodos() {
		List<Usuario> listaUsuario = usuarioService.buscarTodos();
		if(listaUsuario == null || listaUsuario.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum usuário encontrado.");
		} else {
			List<UsuarioResponseDTO> listaUsuariosDTO = new ArrayList<>();
			listaUsuario.stream().forEach(u -> {
				listaUsuariosDTO.add(new UsuarioResponseDTO(u));
			});
			return ResponseEntity.status(HttpStatus.OK).body(listaUsuariosDTO);
		}
	}
	
	@Override
	public ResponseEntity<?> buscarPorId(Long id) {
		Usuario usuario = usuarioService.buscarPorId(id);
		if(usuario == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum usuário encontrado para o ID informado.");
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(new UsuarioResponseDTO(usuario));
		} 
	}
	
	@Override
	public ResponseEntity<?> cadastrar(UsuarioRequestDTO usuarioRequest, String chavePublica) {
		try {
			Usuario usuario = usuarioService.cadastrar(usuarioRequest.getEmail(), usuarioRequest.getNome(), chavePublica);
			return ResponseEntity.status(HttpStatus.CREATED).body(new UsuarioResponseDTO(usuario));
		} catch (Exception e) {
			logger.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ocorreu um erro ao criptografar e cadastrar os dados do usuário.");
		}
	}
	
	@Override
	public ResponseEntity<?> atualizar(Long id, UsuarioRequestDTO usuarioRequest) {
		Usuario usuario = usuarioService.buscarPorId(id);
		if(usuario == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum usuário encontrado para o ID informado.");
		} else {
			try {
				usuario = usuarioService.atualizarEmailENome(usuario, usuarioRequest.getEmail(), usuarioRequest.getNome());
			} catch (Exception e) {
				logger.error(e.getMessage());
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ocorreu um erro ao criptografar e atualizar os dados do usuário.");
			}
			return ResponseEntity.status(HttpStatus.OK).body(new UsuarioResponseDTO(usuario));
		}
	}

	@Override
	public ResponseEntity<?> atualizarEmail(Long id, AtualizarEmailRequestDTO atualizarEmailRequest) {
		Usuario usuario = usuarioService.buscarPorId(id);
		if(usuario == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum usuário encontrado para o ID informado.");
		} else {
			try {
				usuario = usuarioService.atualizarEmail(usuario, atualizarEmailRequest.getNovoEmail());
			} catch (Exception e) {
				logger.error(e.getMessage());
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ocorreu um erro ao criptografar e atualizar os dados do usuário.");
			}
			return ResponseEntity.status(HttpStatus.OK).body(new UsuarioResponseDTO(usuario));
		}
	}
	
	@Override
	public ResponseEntity<?> atualizarNome(Long id, AtualizarNomeRequestDTO atualizarNomeRequest) {
		Usuario usuario = usuarioService.buscarPorId(id);
		if(usuario == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum usuário encontrado para o ID informado.");
		} else {
			try {
				usuario = usuarioService.atualizarNome(usuario, atualizarNomeRequest.getNovoNome());
			} catch (Exception e) {
				logger.error(e.getMessage());
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ocorreu um erro ao criptografar e atualizar os dados do usuário.");
			}
			return ResponseEntity.status(HttpStatus.OK).body(new UsuarioResponseDTO(usuario));
		}
	}

	@Override
	public ResponseEntity<?> deletarPorId(Long id) {
		Usuario usuario = usuarioService.buscarPorId(id);
		if(usuario == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum usuário encontrado para o ID informado.");
		} else {
			usuarioService.deletar(id);
			return ResponseEntity.status(HttpStatus.OK).body("Usuário deletado com sucesso");
		}
	}

}