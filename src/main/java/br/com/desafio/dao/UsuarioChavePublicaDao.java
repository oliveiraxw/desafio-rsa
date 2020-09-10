package br.com.desafio.dao;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.desafio.model.Usuario;
import br.com.desafio.model.UsuarioChavePublica;

@Transactional
public interface UsuarioChavePublicaDao extends JpaRepository<UsuarioChavePublica, Long> {
	
	UsuarioChavePublica findByUsuario(Usuario usuario);
	
}