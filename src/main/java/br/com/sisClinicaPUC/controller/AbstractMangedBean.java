package br.com.sisClinicaPUC.controller;

import javax.faces.context.FacesContext;

import br.com.sisClinicaPUC.entidade.Medico;
import br.com.sisClinicaPUC.util.ValidacaoException;
import br.com.sisClinicaPUC.vo.OperacaoEnum;

public abstract class AbstractMangedBean<T> extends ValidacaoException implements Servico<T> {

	public OperacaoEnum operacao = OperacaoEnum.CONSULTAR;
	
	public void init() {
		this.operacao = OperacaoEnum.CONSULTAR;
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
}
