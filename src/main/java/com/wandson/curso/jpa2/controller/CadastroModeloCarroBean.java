package com.wandson.curso.jpa2.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.wandson.curso.jpa2.dao.FabricanteDAO;
import com.wandson.curso.jpa2.modelo.Fabricante;
import com.wandson.curso.jpa2.modelo.ModeloCarro;
import com.wandson.curso.jpa2.service.CadastroModeloCarroService;
import com.wandson.curso.jpa2.service.NegocioException;
import com.wandson.curso.jpa2.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroModeloCarroBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private ModeloCarro modeloCarro;
	private List<Fabricante> fabricantes;

	@Inject
	private CadastroModeloCarroService cadastroModeloCarroService;

	@Inject
	private FabricanteDAO fabricanteDAO;

	public void salvar() {
		try {
			cadastroModeloCarroService.salvar(modeloCarro);
			FacesUtil.addSuccessMessage("Modelo carro salvo com sucesso!");
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}

		limpar();
	}

	@PostConstruct
	public void inicializar() {
		limpar();
		fabricantes = fabricanteDAO.buscarTodos();
	}

	public void limpar() {
		modeloCarro = new ModeloCarro();
	}

	public ModeloCarro getModeloCarro() {
		return modeloCarro;
	}

	public void setModeloCarro(ModeloCarro modeloCarro) {
		this.modeloCarro = modeloCarro;
	}

	public List<Fabricante> getFabricantes() {
		return fabricantes;
	}

}
