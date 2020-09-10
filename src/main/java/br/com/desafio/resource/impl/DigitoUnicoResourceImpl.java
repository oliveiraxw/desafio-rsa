package br.com.desafio.resource.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import br.com.desafio.dto.request.DigitoUnicoRequestDTO;
import br.com.desafio.dto.response.DigitoUnicoResponseDTO;
import br.com.desafio.model.DigitoUnico;
import br.com.desafio.model.Usuario;
import br.com.desafio.resource.DigitoUnicoResource;
import br.com.desafio.service.DigitoUnicoService;
import br.com.desafio.service.UsuarioService;

@RestController
public class DigitoUnicoResourceImpl implements DigitoUnicoResource {
	
	@Autowired
	private DigitoUnicoService digitoUnicoService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Override
	public ResponseEntity<?> recuperarDigitoUnico(Long idUsuario) {
		Usuario usuario = usuarioService.buscarPorId(idUsuario);
		if(usuario == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum usuário encontrado para o ID informado.");
		}else {
			List<DigitoUnico> listaCalculosRecuperados = digitoUnicoService.recuperarCalculosDigitoUnico(usuario);
			if (listaCalculosRecuperados == null || listaCalculosRecuperados.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum resultado de cálculo de dígito único encontrado o ID de usuário informado.");
			} else {
				List<DigitoUnicoResponseDTO> listaCalculosDTO = new ArrayList<>();
				listaCalculosRecuperados.stream().forEach(u -> {
					listaCalculosDTO.add(new DigitoUnicoResponseDTO(u));
				});
				return ResponseEntity.status(HttpStatus.OK).body(listaCalculosDTO);
			}
		}
	}
	
	@Override
	public ResponseEntity<?> calcularDigitoUnico(Long idUsuario, DigitoUnicoRequestDTO digitoUnicoRequest) {
		Usuario usuario = usuarioService.buscarPorId(idUsuario);
		if(usuario == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum usuário encontrado para o ID informado.");
		}else {
			DigitoUnico digitoUnico = digitoUnicoService.calcularDigitoUnico(usuario, digitoUnicoRequest.getN(), digitoUnicoRequest.getK());
			DigitoUnicoResponseDTO digitoUnicoResponse = new DigitoUnicoResponseDTO(digitoUnico);
			return ResponseEntity.status(HttpStatus.OK).body(digitoUnicoResponse);
		}
	}

}