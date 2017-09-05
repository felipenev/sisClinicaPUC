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
	
	public void paginaManterMedicamento() {
		
		pagina = "medicamento.xhtml";
	}
	
	public void paginaSolicitarExame() {
		
		pagina = "solicitarExame.xhtml";
	}
	
}