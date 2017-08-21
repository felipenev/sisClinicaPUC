package br.com.sisClinicaPUC.controller;

import br.com.sisClinicaPUC.util.ValidacaoException;
import br.com.sisClinicaPUC.vo.OperacaoEnum;

public abstract class AbstractMangedBean<T> extends ValidacaoException implements Servico<T> {

	public OperacaoEnum operacao = OperacaoEnum.CONSULTAR;
	
	public void init() {
		this.operacao = OperacaoEnum.CONSULTAR;
	}
	
}
