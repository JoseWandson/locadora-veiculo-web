package com.wandson.curso.jpa2.controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.wandson.curso.jpa2.dao.FabricanteDAO;
import com.wandson.curso.jpa2.modelo.Categoria;
import com.wandson.curso.jpa2.modelo.Fabricante;
import com.wandson.curso.jpa2.modelo.ModeloCarro;
import com.wandson.curso.jpa2.service.CadastroModeloCarroService;
import com.wandson.curso.jpa2.service.NegocioException;
import com.wandson.curso.jpa2.util.jsf.FacesUtil;

import lombok.Getter;
import lombok.Setter;

@Named
@ViewScoped
public class CadastroModeloCarroBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	private ModeloCarro modeloCarro;

	@Getter
	private List<Fabricante> fabricantes;

	@Getter
	private List<Categoria> categorias;

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
		categorias = Arrays.asList(Categoria.values());
	}

	public void limpar() {
		modeloCarro = new ModeloCarro();
	}

}
