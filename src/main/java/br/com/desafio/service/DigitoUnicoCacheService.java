package br.com.desafio.service;

import br.com.desafio.model.DigitoUnico;

/**
* Serviço que disponibiliza operações relacionadas ao cache do cálculo do dígito único
* 
* @author Gersonn Oliveira
*/
public interface DigitoUnicoCacheService {

	/**
	* Adiciona em cache o cálculo de dígito único.
	*
	* @param digitoUnico A entidade que contém os dados do cálculo do dígito único
	*/
	void adicionarCalculo(DigitoUnico digitoUnico);
	
	/**
	* Recupera do cache o cálculo do dígito último já realizado previamente.
	*
	* @param  n 		  O primeiro parâmetro do cálculo do dígito único
	* @param  k 		  O segundo parâmetro do cálculo do dígito único
	* @return DigitoUnico A entidade que contém os dados do cálculo do dígito único
	*/
	DigitoUnico buscarCalculo(String n, Integer k);
	
}