package com.wandson.curso.jpa2.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import com.wandson.curso.jpa2.dao.ModeloCarroDAO;
import com.wandson.curso.jpa2.modelo.ModeloCarro;

@FacesConverter(forClass = ModeloCarro.class, managed = true)
public class ModeloCarroConverter implements Converter<ModeloCarro> {

	@Inject
	private ModeloCarroDAO modeloCarroDAO;

	@Override
	public ModeloCarro getAsObject(FacesContext context, UIComponent component, String value) {
		ModeloCarro retorno = new ModeloCarro();

		if (value != null && !value.trim().equals("")) {
			retorno = this.modeloCarroDAO.buscarPeloCodigo(Long.parseLong(value));
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, ModeloCarro value) {
		if (value != null) {
			Long codigo = value.getCodigo();
			return codigo == null ? null : codigo.toString();
		}
		return "";
	}

}