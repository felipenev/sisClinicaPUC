package br.com.sisClinicaPUC.controller;

  import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
   
   
@Named
@SessionScoped
public class NavigationManagedBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String pagina="bemVindo.xhtml";

	public String getPagina() {
		return pagina;
	}

	public void setPagina(String pagina) {
		this.pagina = pagina;
	}

	public void paginaManterMedico() {
	
		pagina = "manterMedico.xhtml";
	}
	
	public void paginaManterRecepcionista() {
		
		pagina = "manterRecepcionista.xhtml";
	}
	
	public void paginaAgendaMedico() {

		pagina = "agendaMedico.xhtml";
	}
	
	public void paginaManterMedicamento() {
		
		pagina = "medicamento.xhtml";
	}
	
	public void paginaSolicitarExame() {
		
		pagina = "solicitarExame.xhtml";
	}
	
	public void paginaManterPaciente() {
		
		pagina = "manterPaciente.xhtml";
	}
	
	public void paginaManterConsulta() {
		
		pagina = "manterConsulta.xhtml";
	}

	public void paginaReceitaMedica() {

		pagina = "receitaMedica.xhtml";
	}
}