package br.com.desafio.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {
	
	@Value("${versao-api}") 
	String versao;
	
	@Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
	        .components(new Components())
	        .info(info());
    }
	
	private Info info() {
		return new Info()
			.title("Desafio - Chave RSA assimétrica")
			.description("Autor: Gersonn Oliveira")
			.version(versao);
	}

}