package br.com.desafio.service;

import java.util.List;

import br.com.desafio.model.DigitoUnico;
import br.com.desafio.model.Usuario;

/**
* Serviço que disponibiliza operações relacionadas ao cálculo do dígito único
* 
* @author      Gersonn Oliveira
*/
public interface DigitoUnicoService {

	/**
	* Calcula o dígito único e vincula ao usuário.
	* Armazena os 10 últimos cálculos realizados independente do usuário.
	* Se o cálculo já tiver sido realizado previamente por algum usuário, o cálculo não será realizado, pois já estará armazenado em cache.
	*
	* @param  usuario	 A entidade que contém os dados do usuário
	* @param  n		 	 O primeiro parâmetro para o cálculo do dígito único
	* @param  k		 	 O segundo parâmetro para o cálculo do dígito único
	* @return      		 A entidade que contém as informações do dígito único
	*/
	DigitoUnico calcularDigitoUnico(Usuario usuario, String n, Integer k);
	
	/**
	* Recupera os cálculos de dígito único realizados por um determinado usuário
	*
	* @param  usuario	 A entidade que contém os dados do usuário
	* @return      		 Uma lista de entidades que contém as informações do dígito único
	*/
	List<DigitoUnico> recuperarCalculosDigitoUnico(Usuario usuario);
	
}