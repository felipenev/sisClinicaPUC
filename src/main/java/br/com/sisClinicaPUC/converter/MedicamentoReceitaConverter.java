package br.com.sisClinicaPUC.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import br.com.sisClinicaPUC.controller.ManterMedicamentoService;
import br.com.sisClinicaPUC.entidade.Medicamento;

public class MedicamentoReceitaConverter implements Converter {

	public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
		if(value != null && value.trim().length() > 0) {
            try {
            	ManterMedicamentoService service = (ManterMedicamentoService) fc.getExternalContext().getApplicationMap().get("manterMedicamentoService");
                return service.getMedicamentosDisponivelPorId(Long.parseLong(value));
            } catch(NumberFormatException e) {
                FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "");
                throw new ConverterException(facesMessage);
            }
        }
        else {
            return null;
        }
    }
	 
	public String getAsString(FacesContext fc, UIComponent uic, Object object) {
		if(object != null) {
            return String.valueOf(((Medicamento) object).getId());
        }
        else {
            return null;
        }
    }  

}