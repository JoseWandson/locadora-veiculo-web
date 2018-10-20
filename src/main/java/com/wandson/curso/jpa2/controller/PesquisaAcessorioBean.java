package com.wandson.curso.jpa2.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.wandson.curso.jpa2.dao.AcessorioDAO;
import com.wandson.curso.jpa2.modelo.Acessorio;
import com.wandson.curso.jpa2.service.NegocioException;
import com.wandson.curso.jpa2.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaAcessorioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	AcessorioDAO acessorioDAO;

	private transient List<Acessorio> acessorios = new ArrayList<>();

	private transient Acessorio acessorioSelecionado;

	public List<Acessorio> getAcessorios() {
		return acessorios;
	}

	public void excluir() {
		try {
			acessorioDAO.excluir(acessorioSelecionado);
			this.acessorios.remove(acessorioSelecionado);
			FacesUtil.addSuccessMessage("Acessório " + acessorioSelecionado.getDescricao() + " excluído com sucesso.");
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	public Acessorio getAcessorioSelecionado() {
		return acessorioSelecionado;
	}

	public void setAcessorioSelecionado(Acessorio acessorioSelecionado) {
		this.acessorioSelecionado = acessorioSelecionado;
	}

	@PostConstruct
	public void inicializar() {
		acessorios = acessorioDAO.buscarTodos();
	}
}