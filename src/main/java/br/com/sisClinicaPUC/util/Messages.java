package br.com.sisClinicaPUC.util;


import java.util.ResourceBundle;

import javax.faces.context.FacesContext;


/**
 * Classe para mostrar as mensagens do arquivo messages.properties
 */
public class Messages {
	private static ResourceBundle resources = null;
	
	private Messages() {}
	
	static {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		resources = facesContext.getApplication().getResourceBundle(facesContext, "msgs");
	}
	
	/**
	 * Retorna a mensagem associada ao msgId informado
	 * @param msgId
	 * @return String A mensagem associada
	 */
	public static String getMessage(String msgId) {
		String message = "";
		message = resources.getString(msgId);
		return message;
	}
	
	//TODO: terminar 
//	String mensagemParametrizada = "Isto foi um post sobre {0} e {1}.";
//	String mensagem =
//	  MessageFormat.format(mensagemParametrizada, "i18n", "formatação");
//	System.out.println(mensagem);
}
