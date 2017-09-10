package br.com.sisClinicaPUC.converter;


import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import br.com.sisClinicaPUC.util.Util;
 
/**
* Conversor de CPF.
*
*/
public class CpfConverter implements Converter {
	
	  public String getAsString(FacesContext context, UIComponent component, Object value) throws ConverterException {
         /*
          * Irá converter CPF não formatado para um com pontos e traço.
          * Ex.: 35524519887 torna-se 355.245.198-87.
          */
		  try {
			  
			  if(value == null) {
				  return null;
			  }
	          String cpf= (String) value;
	          if (cpf != null && cpf.length() == Util.TAMANHO_CPF)
	               cpf = Util.formatarString(Util.MASCARA_CPF, cpf);
	          
	          return cpf;
		  } catch (Exception e) {
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "");
			
			throw new ConverterException(facesMessage);
		  }
     }
	
     public Object getAsObject(FacesContext context, UIComponent component, String value) throws ConverterException {

    	 try {
        	 /*
        	  * Irá converter CPF formatado para um sem pontos e traço.
        	  * Ex.: 355.245.198-87 torna-se 35524519887.
        	  */
        	 String cpf = value;
        	 if (value!= null && !"".equals(value))
        		 cpf = Util.removeNoNumbers(value);
        	 
        	 return cpf;
        	 
		} catch (Exception e) {
			 FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR,"MSG006", "");

			 throw new ConverterException(facesMessage);
		}
     }
 
}