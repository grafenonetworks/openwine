package com.grafenonet.openwine.maestros.controller.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.grafenonet.openwine.maestros.domain.Provincia;

@Component("provinciaValidator")
public class ProvinciaValidator implements Validator {
	private static final Logger LOG = LoggerFactory.getLogger(ProvinciaValidator.class);

	@Override
	public boolean supports(Class<?> clazz) {
		return Provincia.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Provincia provincia = (Provincia) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "codigo", "web.validate.campo_obligatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombre", "web.validate.campo_obligatorio");
		
		if (provincia.getCodigo().length() > 2) {
			LOG.debug(" - codigo excede maximo");
			errors.rejectValue("codigo", "web.validate.tamano_campo_excede_maximo_permitido");
		}
		
		if (provincia.getNombre().length() > 250) {
			LOG.debug(" - nombre excede maximo");
			errors.rejectValue("nombre", "web.validate.tamano_campo_excede_maximo_permitido");
		}
		
		if (provincia.getPais() == null || provincia.getPais().getId() == null) {
			LOG.debug(" - pais null");
			errors.rejectValue("pais", "web.validate.valor_no_valido");
		}
	}

}
