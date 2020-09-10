package br.com.desafio;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import br.com.desafio.dto.response.VersaoDTO;
import br.com.desafio.resource.VersaoResource;

@SpringBootTest
public class VersaoTests {
	
	@Autowired
	private VersaoResource versaoResource;
	
	@Test
    public void testeVersao() {
        ResponseEntity<VersaoDTO> result = versaoResource.versao();
        assertThat(result.getBody().getVersao()).isNotBlank();
    }
	
}