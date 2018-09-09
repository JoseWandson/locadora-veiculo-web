package com.wandson.curso.jpa2.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.wandson.curso.jpa2.modelo.Fabricante;
import com.wandson.curso.jpa2.service.NegocioException;
import com.wandson.curso.jpa2.util.jpa.Transactional;

public class FabricanteDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager em;

	public void salvar(Fabricante fabricante) {
		em.merge(fabricante);
	}

	@SuppressWarnings("unchecked")
	public List<Fabricante> buscarTodos() {
		return em.createQuery("from Fabricante").getResultList();
	}

	@Transactional
	public void excluir(Fabricante fabricante) throws NegocioException {
		fabricante = em.find(Fabricante.class, fabricante.getCodigo());
		em.remove(fabricante);
		em.flush();
	}

	public Fabricante buscarPeloCodigo(Long codigo) {
		return em.find(Fabricante.class, codigo);
	}

}
