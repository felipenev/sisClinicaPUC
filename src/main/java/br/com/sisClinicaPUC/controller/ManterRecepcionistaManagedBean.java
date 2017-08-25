package br.com.sisClinicaPUC.controller;

  import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.com.sisClinicaPUC.entidade.Recepcionista;
import br.com.sisClinicaPUC.persistencia.RecepcionistaDAO;
import br.com.sisClinicaPUC.util.Util;
import br.com.sisClinicaPUC.vo.SituacaoEnum;
   
@Named
@ViewScoped
public class ManterRecepcionistaManagedBean extends AbstractMangedBean<Recepcionista> implements Serializable{
   
	private static final long serialVersionUID = 1L;
	
	private RecepcionistaDAO recepcionistaDAO = new RecepcionistaDAO();
    private Recepcionista recepcionista = new Recepcionista();
    private Recepcionista recepcionistaExclusao = new Recepcionista();
    private List<Recepcionista> recepcionistaList = new ArrayList<Recepcionista>();
    
    public ManterRecepcionistaManagedBean() {}

    @PostConstruct
	public void init() {
    	System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!! inicializei o ManagedBean de Recepcionista !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
    	this.setRecepcionista(new Recepcionista());
		this.setRecepcionistaExclusao(new Recepcionista());
		carregarRecepcionistaAtivoList();
	}
    
    public void inserirAlterar() {
    	if(Util.isValueNotBlankOrNotEmpty(this.getRecepcionista().getId())) {
    		alterar(this.getRecepcionista());
    	}else {
    		inserir();
    	}
    }
    
    @Override
    public void inserir() {
    	if(validarCampos()) {
    		boolean inclusao = this.getRecepcionistaDAO().inserir(this.getRecepcionista());
    		if (inclusao) {
    			recepcionista = new Recepcionista();
    			carregarRecepcionistaAtivoList();
    			this.tratarMensagemSucesso("formPrincipal:growlMsgm");
    		}
    	}
	}
	
	@Override
	public void alterar(Recepcionista recepcionista) {
		if(validarCampos()) {
			boolean alteracao = this.getRecepcionistaDAO().alterar(recepcionista);
			if (alteracao) {
				this.setRecepcionista(new Recepcionista());
				carregarRecepcionistaAtivoList();
				this.tratarMensagemSucesso("formPrincipal:growlMsgm");
			}
		}
		
	}

	@Override
	public void excluir() {
		//Exclusao logica
		this.getRecepcionistaExclusao().setAtivoInaivo(SituacaoEnum.INATIVO);
		boolean exclusao = this.getRecepcionistaDAO().alterar(this.getRecepcionistaExclusao());
		if(exclusao) {
			this.setRecepcionista(new Recepcionista());
			this.setRecepcionistaExclusao(new Recepcionista());
			carregarRecepcionistaAtivoList();
			this.tratarMensagemSucesso("formPrincipal:growlMsgm");
		}
		
	}
	
	@Override
	public void excluir(Recepcionista recepcionista) {}

	public void carregarAlteracao(Recepcionista recepcionistaAlterar) {
		this.setRecepcionista(recepcionistaAlterar);
		this.getRecepcionistaList().remove(recepcionistaAlterar);
	}
	
	/**
	 * Valida os campos obrigatorios
	 */
	public boolean validarCampos() {
		boolean valid = true;

		if(!Util.isDateNotNull(this.getRecepcionista().getDataAdmissao())) {
			this.tratarMensagemErro("formPrincipal:growlMsgm");
			valid = false;
		}
		if(!Util.isStringNotBlankOrNotNull(this.getRecepcionista().getNome())) {
			this.tratarMensagemErro("formPrincipal:growlMsgm");
			valid = false;
		}
		if(!Util.isStringNotBlankOrNotNull(this.getRecepcionista().getSexo())) {
			this.tratarMensagemErro("formPrincipal:growlMsgm");
			valid = false;
		}
		if(!Util.isObjectNotNull(this.getRecepcionista().getRG())) {
			this.tratarMensagemErro("formPrincipal:growlMsgm");
			valid = false;
		}
		if(!Util.isObjectNotNull(this.getRecepcionista().getCPF())) {
			this.tratarMensagemErro("formPrincipal:growlMsgm");
			valid = false;
		}
		if(!Util.isCPFValido(this.getRecepcionista().getCPF())) {
			this.tratarMensagemErro("formPrincipal:growlMsgm", "MSG006");
			valid = false;
		}
		if(!Util.isStringNotBlankOrNotNull(this.getRecepcionista().getEndereco())) {
			this.tratarMensagemErro("formPrincipal:growlMsgm");
			valid = false;
		}
		if(!Util.isStringNotBlankOrNotNull(this.getRecepcionista().getBairro())) {
			this.tratarMensagemErro("formPrincipal:growlMsgm");
			valid = false;
		}
		if(!Util.isStringNotBlankOrNotNull(this.getRecepcionista().getCidade())) {
			this.tratarMensagemErro("formPrincipal:growlMsgm");
			valid = false;
		}
		if(!Util.isStringNotBlankOrNotNull(this.getRecepcionista().getUF())) {
			this.tratarMensagemErro("formPrincipal:growlMsgm");
			valid = false;
		}
		if(!Util.isDateNotNull(this.getRecepcionista().getDataNascimento())) {
			this.tratarMensagemErro("formPrincipal:growlMsgm");
			valid = false;
		}
		if(!Util.isObjectNotNull(this.getRecepcionista().getTelefone())) {
			this.tratarMensagemErro("formPrincipal:growlMsgm");
			valid = false;
		}
		
		return valid;
	}

	/**
	 * Carrega as recepcionistas ativos que podem ser apresentados
	 * 
	 */
	private void carregarRecepcionistaAtivoList() {
		this.setRecepcionistaList(new ArrayList<Recepcionista>());
		this.getRecepcionistaList().addAll(this.getRecepcionistaDAO().getRecepcionistaAtivoList());
	}

	public RecepcionistaDAO getRecepcionistaDAO() {
		return recepcionistaDAO;
	}

	public void setRecepcionistaDAO(RecepcionistaDAO recepcionistaDAO) {
		this.recepcionistaDAO = recepcionistaDAO;
	}

	public Recepcionista getRecepcionista() {
		return recepcionista;
	}

	public void setRecepcionista(Recepcionista recepcionista) {
		this.recepcionista = recepcionista;
	}

	public Recepcionista getRecepcionistaExclusao() {
		return recepcionistaExclusao;
	}

	public void setRecepcionistaExclusao(Recepcionista recepcionistaExclusao) {
		this.recepcionistaExclusao = recepcionistaExclusao;
	}

	public List<Recepcionista> getRecepcionistaList() {
		return recepcionistaList;
	}

	public void setRecepcionistaList(List<Recepcionista> recepcionistaList) {
		this.recepcionistaList = recepcionistaList;
	}

}