package br.com.sisClinicaPUC.view;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public abstract class AbstractMangedBean {

	
	
	public void tratarMensagemSucesso(String id) {
		FacesContext.getCurrentInstance().addMessage(id, new FacesMessage(FacesMessage.SEVERITY_INFO, "Operacao realizada com sucesso", ""));
	}
	
	public void tratarMensagemSucesso(String id, String mensagemSucesso, String detalheMensagemSucesso) {
		FacesContext.getCurrentInstance().addMessage(id, new FacesMessage(FacesMessage.SEVERITY_INFO, mensagemSucesso, detalheMensagemSucesso));
	}
	public void tratarMensagemErro(String id) {
		FacesContext.getCurrentInstance().addMessage(id, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Campo Obrigatorio", ""));
	}
	
	public void tratarMensagemErro(String id, String mensagemErro, String detalheMensagemErro) {
		FacesContext.getCurrentInstance().addMessage(id, new FacesMessage(FacesMessage.SEVERITY_INFO, mensagemErro, detalheMensagemErro));
	}
}
