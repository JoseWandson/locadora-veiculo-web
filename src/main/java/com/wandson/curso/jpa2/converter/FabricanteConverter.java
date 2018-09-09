package com.wandson.curso.jpa2.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import com.wandson.curso.jpa2.dao.FabricanteDAO;
import com.wandson.curso.jpa2.modelo.Fabricante;

@FacesConverter(forClass = Fabricante.class, managed = true)
public class FabricanteConverter implements Converter<Fabricante> {

	@Inject
	private FabricanteDAO fabricanteDAO;

	@Override
	public Fabricante getAsObject(FacesContext context, UIComponent component, String value) {
		Fabricante retorno = null;

		if (value != null && !value.trim().equals("")) {
			retorno = this.fabricanteDAO.buscarPeloCodigo(Long.parseLong(value));
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Fabricante value) {
		if (value != null) {
			Long codigo = ((Fabricante) value).getCodigo();
			String retorno = (codigo == null ? null : codigo.toString());

			return retorno;
		}

		return "";
	}

}