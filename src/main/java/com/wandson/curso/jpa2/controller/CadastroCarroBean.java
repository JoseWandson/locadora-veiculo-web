package com.wandson.curso.jpa2.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.wandson.curso.jpa2.dao.AcessorioDAO;
import com.wandson.curso.jpa2.dao.ModeloCarroDAO;
import com.wandson.curso.jpa2.modelo.Acessorio;
import com.wandson.curso.jpa2.modelo.Carro;
import com.wandson.curso.jpa2.modelo.ModeloCarro;
import com.wandson.curso.jpa2.service.CadastroCarroService;
import com.wandson.curso.jpa2.service.NegocioException;
import com.wandson.curso.jpa2.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroCarroBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	private transient Carro carro;

	private transient List<ModeloCarro> modelosCarros;

	private transient List<Acessorio> acessorios;

	@Inject
	private CadastroCarroService cadastroCarroService;

	@Inject
	private AcessorioDAO acessorioDAO;

	@Inject
	private ModeloCarroDAO modeloCarroDAO;

	@PostConstruct
	public void inicializar() {
		limpar();

		acessorios = acessorioDAO.buscarTodos();
		modelosCarros = modeloCarroDAO.buscarTodos();
	}

	public void salvar() {
		try {
			cadastroCarroService.salvar(carro);
			FacesUtil.addSuccessMessage("Carro salvo com sucesso!");
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e.getCause());
			FacesUtil.addErrorMessage("Erro desconhecido. Contatar o administrador");
		}

		limpar();
	}

	public void limpar() {
		carro = new Carro();
		carro.setAcessorios(new ArrayList<Acessorio>());
	}

	public Carro getCarro() {
		return carro;
	}

	public void setCarro(Carro carro) {
		this.carro = carro;
	}

	public List<Acessorio> getAcessorios() {
		return acessorios;
	}

	public List<ModeloCarro> getModelosCarros() {
		return modelosCarros;
	}

}