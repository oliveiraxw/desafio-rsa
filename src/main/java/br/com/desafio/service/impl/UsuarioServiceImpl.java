package br.com.desafio.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.desafio.dao.UsuarioChavePublicaDao;
import br.com.desafio.dao.UsuarioDao;
import br.com.desafio.model.Usuario;
import br.com.desafio.model.UsuarioChavePublica;
import br.com.desafio.service.UsuarioService;
import br.com.desafio.util.RSAUtil;

@Service("UsuarioService")
public class UsuarioServiceImpl implements UsuarioService {
	
	@Autowired
	private UsuarioDao usuarioDao;
	
	@Autowired 
	private UsuarioChavePublicaDao usuarioChavePublicaDao;
	
	@Override
	public List<Usuario> buscarTodos() {
		return usuarioDao.findAll();
	}
	
	@Override
	public Usuario buscarPorId(Long id) {
		return usuarioDao.findById(id).orElse(null);
	}
	
	@Override
	public Usuario cadastrar(String email, String nome, String chavePublica) throws Exception {
		String emailCriptografado = RSAUtil.criptografarString(email, chavePublica);
		String nomeCriptografado = RSAUtil.criptografarString(nome, chavePublica);
		return usuarioChavePublicaDao.save(new UsuarioChavePublica(new Usuario(emailCriptografado,nomeCriptografado), chavePublica)).getUsuario();
	}
	
	@Override
	public Usuario atualizarEmailENome(Usuario usuario, String novoEmail, String novoNome) throws Exception {
		String chavePublica = usuarioChavePublicaDao.findByUsuario(usuario).getChavePublica();
		usuario.setEmail(RSAUtil.criptografarString(novoEmail, chavePublica));
		usuario.setNome(RSAUtil.criptografarString(novoNome, chavePublica));
		return usuarioDao.save(usuario);
	}
	
	@Override
	public Usuario atualizarEmail(Usuario usuario, String novoEmail) throws Exception {
		String chavePublica = usuarioChavePublicaDao.findByUsuario(usuario).getChavePublica();
		usuario.setEmail(RSAUtil.criptografarString(novoEmail, chavePublica));
		return usuarioDao.save(usuario);
	}
	
	@Override
	public Usuario atualizarNome(Usuario usuario, String novoNome) throws Exception {
		String chavePublica = usuarioChavePublicaDao.findByUsuario(usuario).getChavePublica();
		usuario.setNome(RSAUtil.criptografarString(novoNome, chavePublica));
		return usuarioDao.save(usuario);
	}

	@Override
	public void deletar(Long id) {
		usuarioDao.deleteById(id);
	}

}