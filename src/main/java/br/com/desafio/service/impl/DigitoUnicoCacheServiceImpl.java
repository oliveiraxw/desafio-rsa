package br.com.desafio.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import br.com.desafio.model.DigitoUnico;
import br.com.desafio.service.DigitoUnicoCacheService;

@Service("DigitoUnicoCacheService")
@Scope("singleton")
public class DigitoUnicoCacheServiceImpl implements DigitoUnicoCacheService {
	
	private List<DigitoUnico> listaCalculosDigitoUnico;
	
	DigitoUnicoCacheServiceImpl(){
		listaCalculosDigitoUnico = new ArrayList<>();
	}
	
	@Override
	public void adicionarCalculo(DigitoUnico digitoUnico) {
		if(this.listaCalculosDigitoUnico.size() == 10) {
			this.listaCalculosDigitoUnico.remove(0);
		}
		this.listaCalculosDigitoUnico.add(digitoUnico);
	}
	
	@Override
	public DigitoUnico buscarCalculo(String n, Integer k) {
		DigitoUnico digitoUnicoRetorno = null;
		if (listaCalculosDigitoUnico == null || listaCalculosDigitoUnico.isEmpty()) {
			digitoUnicoRetorno = null;
		}
		for (DigitoUnico digitoUnico : listaCalculosDigitoUnico) {
			if(digitoUnico.getN().equals(n) && digitoUnico.getK().equals(k)) {
				digitoUnicoRetorno = digitoUnico;
			}
		}
		return digitoUnicoRetorno;
	}

}