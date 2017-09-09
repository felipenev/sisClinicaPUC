package br.com.sisClinicaPUC.controller;

import javax.faces.context.FacesContext;

import br.com.sisClinicaPUC.entidade.Historico;
import br.com.sisClinicaPUC.entidade.Medico;
import br.com.sisClinicaPUC.entidade.Recepcionista;
import br.com.sisClinicaPUC.persistencia.HistoricoDAO;
import br.com.sisClinicaPUC.util.ValidacaoException;

public abstract class AbstractMangedBean<T> extends ValidacaoException implements Servico<T> {

	//Historico
	private HistoricoDAO historicoDAO = new HistoricoDAO();
	
	public void init() {
	}
	
	public void inserir () {}
	public void alterar(T objeto) {}
	public void excluir(T objeto) {}
	public void excluir() {}

	public Medico getMedicoSessao() {
    	try {
    		return (Medico) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("medico");
		} catch (Exception e) {
			throw e;
		}
	}
	
	public Recepcionista getRecepcionistaSessao() {
		try {
			return (Recepcionista) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("recepcionista");
		} catch (Exception e) {
			throw e;
		}
	}
	
	public void gravarHistorico(Historico historico) {
		try {
			this.getHistoricoDAO().inserir(historico);
		} catch (Exception e) {
			this.tratarMensagemErro(null, e.getMessage());
		}
		
	}

	public HistoricoDAO getHistoricoDAO() {
		return historicoDAO;
	}

	public void setHistoricoDAO(HistoricoDAO historicoDAO) {
		this.historicoDAO = historicoDAO;
	}
	
}
