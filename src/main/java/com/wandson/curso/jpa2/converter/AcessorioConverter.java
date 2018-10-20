package com.wandson.curso.jpa2.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import com.wandson.curso.jpa2.dao.AcessorioDAO;
import com.wandson.curso.jpa2.modelo.Acessorio;

@FacesConverter(value = "acessorioConverter", managed = true)
public class AcessorioConverter implements Converter<Acessorio> {

	@Inject
	private AcessorioDAO acessorioDAO;

	@Override
	public Acessorio getAsObject(FacesContext context, UIComponent component, String value) {
		Acessorio retorno = new Acessorio();

		if (value != null && !value.trim().equals("")) {
			retorno = this.acessorioDAO.buscarPeloCodigo(Long.parseLong(value));
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Acessorio value) {
		if (value != null) {
			Long codigo = value.getCodigo();
			return (codigo == null ? null : codigo.toString());
		}

		return "";
	}

}