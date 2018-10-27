package com.wandson.curso.jpa2.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.wandson.curso.jpa2.dao.AluguelDAO;
import com.wandson.curso.jpa2.modelo.Aluguel;
import com.wandson.curso.jpa2.util.jpa.Transactional;

public class CadastroAluguelService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private AluguelDAO aluguelDAO;

	@Transactional
	public void salvar(Aluguel aluguel) throws NegocioException {

		if (aluguel.getCarro() == null) {
			throw new NegocioException("O carro é obrigatório");
		}

		this.aluguelDAO.salvar(aluguel);
	}

}