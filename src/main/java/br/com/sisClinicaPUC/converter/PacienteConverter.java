package br.com.sisClinicaPUC.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import br.com.sisClinicaPUC.controller.ManterPacienteService;
import br.com.sisClinicaPUC.entidade.Paciente;

public class PacienteConverter implements Converter {

	public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
		if(value != null && value.trim().length() > 0) {
            try {
            	ManterPacienteService service = (ManterPacienteService) fc.getExternalContext().getApplicationMap().get("manterPacienteService");
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
		if(object != null && object.toString().length() > 0) {
            return String.valueOf(((Paciente) object).getId());
        }
        else {
            return null;
        }
    }  

}