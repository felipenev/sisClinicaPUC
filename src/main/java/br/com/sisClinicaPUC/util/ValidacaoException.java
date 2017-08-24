package br.com.sisClinicaPUC.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public abstract class ValidacaoException {

	public void tratarMensagemSucesso(String id) {
		FacesContext.getCurrentInstance().addMessage(id, new FacesMessage(FacesMessage.SEVERITY_INFO, Messages.getMessage("MSG001"), ""));
	}

	public void tratarMensagemSucesso(String id, String mensagemSucesso, String detalheMensagemSucesso) {
		FacesContext.getCurrentInstance().addMessage(id, new FacesMessage(FacesMessage.SEVERITY_INFO, mensagemSucesso, detalheMensagemSucesso));
	}
	public void tratarMensagemErro(String id) {
		FacesContext.getCurrentInstance().addMessage(id, new FacesMessage(FacesMessage.SEVERITY_FATAL, Messages.getMessage("MSG002"), ""));
	}

	public void tratarMensagemErro(String id, String mensagemErro) {
		FacesContext.getCurrentInstance().addMessage(id, new FacesMessage(FacesMessage.SEVERITY_FATAL, Messages.getMessage(mensagemErro), ""));
	}
	
	public void tratarMensagemErro(String id, String mensagemErro, String detalheMensagemErro) {
		FacesContext.getCurrentInstance().addMessage(id, new FacesMessage(FacesMessage.SEVERITY_FATAL, Messages.getMessage(mensagemErro), detalheMensagemErro));
	}
	
	//TODO: Terminar
//	public void tratarMensagemErro(String id, String... campoParametroObrigatorio) {
//		FacesContext.getCurrentInstance().addMessage(id, new FacesMessage(FacesMessage.SEVERITY_FATAL, Messages. ));
//	}
}
