package com.wandson.curso.jpa2.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import com.wandson.curso.jpa2.dao.CarroDAO;
import com.wandson.curso.jpa2.modelo.Carro;

@FacesConverter(forClass = Carro.class)
public class CarroConverter implements Converter<Carro> {

	@Inject
	private CarroDAO carroDAO;

	@Override
	public Carro getAsObject(FacesContext context, UIComponent component, String value) {
		Carro retorno = new Carro();

		if (value != null && !value.trim().equals("")) {
			retorno = carroDAO.buscarPeloCodigo(Long.parseLong(value));
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Carro value) {
		if (value != null) {
			Long codigo = value.getCodigo();
			return (codigo == null ? null : codigo.toString());
		}

		return "";
	}

}
