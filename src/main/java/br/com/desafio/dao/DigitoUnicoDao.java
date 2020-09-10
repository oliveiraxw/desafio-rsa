package br.com.desafio.dao;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.desafio.model.DigitoUnico;
import br.com.desafio.model.Usuario;

@Transactional
public interface DigitoUnicoDao extends JpaRepository<DigitoUnico, Long> {
	
	List<DigitoUnico> findByUsuario(Usuario usuario);
	
}