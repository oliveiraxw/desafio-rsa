package br.com.desafio.resource.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import br.com.desafio.dto.response.VersaoDTO;
import br.com.desafio.resource.VersaoResource;

@RestController
public class VersaoResourceImpl implements VersaoResource {

	@Value("${versao-api}") 
	String versao;
	
	@Override
	public ResponseEntity<VersaoDTO> versao(){
		return ResponseEntity.status(HttpStatus.OK).body(new VersaoDTO(versao));
	}
	
}