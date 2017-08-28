package br.com.sisClinicaPUC.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import br.com.sisClinicaPUC.util.Util;

public class CRMConverter implements Converter {

  public String getAsString(FacesContext context, UIComponent component, Object value) {
	  
    if (value == null) {
      return null;
    }
    
    String crm = (String) value;
    if(crm != null && crm.length() == Util.TAMANHO_CRM) {
    	crm = Util.removerCaracteresEspeciais(crm);
    }

    return crm;
  }

  public Object getAsObject(FacesContext context, UIComponent component, String value) {


    try {
    	if (value == null) {
    		return null;
    	}
    	String crm = value;
    	if(value != null && !"".equals(value)) {
    		crm = Util.removerCaracteresEspeciais(crm);//de +55 (11) 2626-9415 para 5511826269415
    	}
      
    	return crm;

    } catch (Exception e) {
    	FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR,"MSG009", "");

    	throw new ConverterException(facesMessage);
    }
  }

}