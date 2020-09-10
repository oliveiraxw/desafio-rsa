package br.com.desafio.resource.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import br.com.desafio.dto.request.DescriptografiaRequestDTO;
import br.com.desafio.dto.response.ChavePrivadaPublicaResponseDTO;
import br.com.desafio.dto.response.DescriptografiaResponseDTO;
import br.com.desafio.resource.ClientSideCriptografiaResource;
import br.com.desafio.util.RSAUtil;
import br.com.desafio.util.RSAUtil.ChavePrivadaPublica;

@RestController
public class ClientSideCriptografiaResourceImpl implements ClientSideCriptografiaResource {
	
	Logger logger = LoggerFactory.getLogger(ClientSideCriptografiaResourceImpl.class);

	@Override
	public ResponseEntity<?> gerarParChavesRSA() {
		ChavePrivadaPublica chaves = RSAUtil.gerarChavesPrivadaPublica();
		return ResponseEntity.status(HttpStatus.OK).body(new ChavePrivadaPublicaResponseDTO(chaves.getChavePrivada(), chaves.getChavePublica()));
	}

	@Override
	public ResponseEntity<?> descriptografar(DescriptografiaRequestDTO descriptografiaRequest, String chavePrivada) {
		try {
			String informacaoDescriptografada = RSAUtil.descriptografarString(descriptografiaRequest.getInformacaoCriptografada(), chavePrivada);
			return ResponseEntity.status(HttpStatus.OK).body(new DescriptografiaResponseDTO(informacaoDescriptografada));
		} catch (Exception e) {
			logger.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ocorreu um erro ao descriptografar as informações. Verifique se a chave privada está correta.");
		}
	}

}