package br.com.desafio.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import br.com.desafio.dao.DigitoUnicoDao;
import br.com.desafio.model.DigitoUnico;
import br.com.desafio.model.Usuario;
import br.com.desafio.service.DigitoUnicoCacheService;
import br.com.desafio.service.DigitoUnicoService;

@Service("DigitoUnicoService")
public class DigitoUnicoServiceImpl implements DigitoUnicoService {
	
	@Autowired
	private DigitoUnicoDao digitoUnicoDao;
	
	@Autowired
	private DigitoUnicoCacheService digitoUnicoCacheService;
	
	@Override
	public List<DigitoUnico> recuperarCalculosDigitoUnico(Usuario usuario) {
		return digitoUnicoDao.findByUsuario(usuario);
	}
	
	@Override
	public DigitoUnico calcularDigitoUnico(Usuario usuario, String n, Integer k) {
		DigitoUnico digitoUnicoEmCache = digitoUnicoCacheService.buscarCalculo(n,k);
		if(digitoUnicoEmCache != null) {
			return digitoUnicoEmCache;
		}
		int resultadoDigitoUnico = calcularDigitoUnico(n, k);
		DigitoUnico digitoUnicoNovo = new DigitoUnico(n, k, resultadoDigitoUnico, usuario);
		digitoUnicoCacheService.adicionarCalculo(digitoUnicoNovo);
		return digitoUnicoDao.save(digitoUnicoNovo);
		
	}
	
	private int calcularDigitoUnico(String n, Integer k) {
		String nk = n;
		if(k != null) {
			for(int i = 1; i < k; i++) {
				nk += n;
			}
		}
		if(!StringUtils.hasLength(nk)) {
			return 0;
		} else if(nk.length() == 1 && Character.isDigit(nk.charAt(0))) {
			return Integer.valueOf(nk);
		} else {
			n = String.valueOf(nk.chars()
					.filter(c -> Character.isDigit(c))
					.map(c -> Character.getNumericValue(c))
					.sum());
			return calcularDigitoUnico(n, null);
		}
	}

}