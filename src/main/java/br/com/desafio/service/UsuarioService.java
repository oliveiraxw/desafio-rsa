package br.com.desafio.service;

import java.util.List;

import br.com.desafio.model.Usuario;

/**
* Serviço que disponibiliza operações relacionadas aos dados do usuário
* 
* @author      Gersonn Oliveira
*/
public interface UsuarioService {
	
	/**
	* Retorna todos os usuários armazenados na base de dados.
	* Os dados foram criptografados com a chave pública enviada pelo cliente, no momento do cadastro.
	*
	* @return      Uma lista de entidades do tipo Usuario, que contém as informações criptografadas do usuário
	*/
	List<Usuario> buscarTodos();
	
	/**
	* Retorna a entidade do usuário armazenada na base de dados.
	* Os dados foram criptografados com a chave pública enviada pelo cliente, no momento do cadastro.
	*
	* @param  id   Identificador do usuário
	* @return      A entidade que contém as informações criptografadas do usuário
	*/
	Usuario buscarPorId(Long id);
	
	/**
	* Criptografa e cadastra os dados de um usuário na base de dados.
	* Os dados serão criptografados com a chave pública enviada pelo cliente.
	*
	* @param  email		 	Email do usuário
	* @param  nome		 	Nome do usuário
	* @param  chavePublica	Chave pública enviada pelo cliente
	* @return      		 	A entidade que contém as informações criptografadas do usuário
	* @throws Exception  	Dispara uma exceção genérica, caso ocorra uma falha no momento do cadastro
	*/
	Usuario cadastrar(String email, String nome, String chavePublica) throws Exception;
	
	/**
	* Atualiza o email e o nome de um usuário na base de dados.
	* Os dados serão criptografados com a chave pública enviada pelo cliente, no momento do cadastro.
	*
	* @param  usuario	  A entidade do usuário
	* @param  novoEmail	  Novo email do usuário
	* @param  novoNome	  Novo nome do usuário
	* @return      		  A entidade que contém as informações criptografadas do usuário
	* @throws Exception   Dispara uma exceção genérica, caso ocorra uma falha no momento da atualização dos dados
	*/
	Usuario atualizarEmailENome(Usuario usuario, String novoEmail, String novoNome) throws Exception;
	
	/**
	* Atualiza o email de um usuário na base de dados.
	* Os dados serão criptografados com a chave pública enviada pelo cliente, no momento do cadastro.
	*
	* @param  usuario	  A entidade do usuário
	* @param  novoEmail	  O novo email do usuário
	* @return      		  A entidade que contém as informações criptografadas do usuário
	* @throws Exception   Dispara uma exceção genérica, caso ocorra uma falha no momento da atualização dos dados
	*/
	Usuario atualizarEmail(Usuario usuario, String novoEmail) throws Exception;
	
	/**
	* Atualiza o nome de um usuário na base de dados.
	* Os dados serão criptografados com a chave pública enviada pelo cliente, no momento do cadastro.
	*
	* @param  usuario	 A entidade do usuário
	* @param  novoNome	 O novo nome do usuário
	* @return      		 A entidade que contém as informações criptografadas do usuário
	* @throws Exception  Dispara uma exceção genérica, caso ocorra uma falha no momento da atualização dos dados
	*/
	Usuario atualizarNome(Usuario usuario, String novoNome) throws Exception;
	
	/**
	* Deleta o usuário armazenado na base de dados.
	*
	* @param  id   Identificador do usuário
	*/
	void deletar(Long id);
	
}