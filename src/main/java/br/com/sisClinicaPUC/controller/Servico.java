package br.com.sisClinicaPUC.controller;

/**
 * 
 * 
 * @author felipe.nmatos
 * @param <T>
 */
public interface Servico<T> {
	

	public void inserir();
	public void alterar(T objeto);
	public void excluir(T objeto);

}
