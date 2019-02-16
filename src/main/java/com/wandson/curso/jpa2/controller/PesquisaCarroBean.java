package com.wandson.curso.jpa2.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.wandson.curso.jpa2.dao.CarroDAO;
import com.wandson.curso.jpa2.modelo.Carro;
import com.wandson.curso.jpa2.service.NegocioException;
import com.wandson.curso.jpa2.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaCarroBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CarroDAO carroDAO;

	private List<Carro> carros = new ArrayList<>();

	private Carro carroSelecionado;

	public List<Carro> getCarros() {
		return carros;
	}

	public void excluir() {
		try {
			carroDAO.excluir(carroSelecionado);
			this.carros.remove(carroSelecionado);
			FacesUtil.addSuccessMessage("Carro placa " + carroSelecionado.getPlaca() + " excluído com sucesso.");
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	public Carro getCarroSelecionado() {
		return carroSelecionado;
	}

	public void setCarroSelecionado(Carro carroSelecionado) {
		this.carroSelecionado = carroSelecionado;
	}

	@PostConstruct
	public void inicializar() {
		carros = carroDAO.buscarTodos();
	}

}