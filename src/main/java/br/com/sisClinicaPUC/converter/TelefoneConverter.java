package br.com.sisClinicaPUC.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import br.com.sisClinicaPUC.util.Util;

public class TelefoneConverter implements Converter {

	public String getAsString(FacesContext context, UIComponent component, Object value) {
	  
		if (value == null) {
			return null;
		}
		
		try {
			String telefone = (String) value;
		    if(telefone != null && telefone.length() == Util.TAMANHO_TELEFONE) {
		    	telefone = Util.formatarString(Util.MASCARA_TELEFONE, telefone);
		    }

		    return telefone;
			
		} catch (Exception e) {
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "");
			
			throw new ConverterException(facesMessage);
		}
    
  }

  public Object getAsObject(FacesContext context, UIComponent component, String value) {


    try {
    	if (value == null) {
    		return null;
    	}
    	String numero = value;
    	if(value != null && !"".equals(value)) {
    		numero = Util.removeNoNumbers(value);//de +55 (11) 2626-9415 para 5511826269415
    	}
      
    	return numero;

    } catch (Exception e) {
    	FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR,"MSG008", "");

    	throw new ConverterException(facesMessage);
    }
  }

}