package br.com.desafio.dao;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.desafio.model.Usuario;

@Transactional
public interface UsuarioDao extends JpaRepository<Usuario, Long> {
	
}