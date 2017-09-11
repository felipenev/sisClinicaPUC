package br.com.sisClinicaPUC.controller;

  import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.com.sisClinicaPUC.entidade.Recepcionista;
import br.com.sisClinicaPUC.entidade.Usuario;
import br.com.sisClinicaPUC.persistencia.RecepcionistaDAO;
import br.com.sisClinicaPUC.persistencia.UsuarioDAO;
import br.com.sisClinicaPUC.util.Util;
import br.com.sisClinicaPUC.vo.SituacaoEnum;
   
@Named
@ViewScoped
public class ManterRecepcionistaManagedBean extends AbstractMangedBean<Recepcionista> implements Serializable{
   
	private static final long serialVersionUID = 1L;

	private UsuarioDAO usuarioDAO = new UsuarioDAO();
	
	private RecepcionistaDAO recepcionistaDAO = new RecepcionistaDAO();
    private Recepcionista recepcionista = new Recepcionista();
    private Recepcionista recepcionistaExclusao = new Recepcionista();
    private List<Recepcionista> recepcionistaList = new ArrayList<Recepcionista>();
    
    private Date dataAtual = new Date();
    
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
    		//Primeiro criando o usuario
    		this.getRecepcionista().getUsuario().setLogin(this.getRecepcionista().getUsuario().getLogin().trim());
    		Usuario usuario = this.getUsuarioDAO().inserirUsuario(this.getRecepcionista().getUsuario());
    		this.getRecepcionista().setUsuario(usuario);
    		//Recepcionista
    		Recepcionista recepInclusao = this.getRecepcionistaDAO().inserir(this.getRecepcionista());
    		//Atualiza usuario com recepcionista
    		usuario.setRecepcionista(recepInclusao);
    		this.getUsuarioDAO().atualizar(usuario);
    		
    		if (recepInclusao != null) {
    			recepcionista = new Recepcionista();
    			carregarRecepcionistaAtivoList();
    			this.tratarMensagemSucesso(null);
    		}
    	}
	}
	
	@Override
	public void alterar(Recepcionista recepcionista) {
		if(validarCampos()) {
			//Alterando o usuario
			recepcionista.getUsuario().setLogin(recepcionista.getUsuario().getLogin().trim());
			Usuario usuario = this.getUsuarioDAO().atualizar(recepcionista.getUsuario());
			this.getRecepcionista().setUsuario(usuario);
			//Recepcionista
			boolean alteracao = this.getRecepcionistaDAO().alterar(recepcionista);
			if (alteracao) {
				init();
				this.tratarMensagemSucesso(null);
			}
		}
		
	}

	@Override
	public void excluir() {
		//Exclusao logica
		//Exclusao usuario
		Usuario usr = this.getRecepcionistaExclusao().getUsuario();
		usr.setAtivoInativo(SituacaoEnum.INATIVO);
		this.getUsuarioDAO().atualizar(usr);
		//Exclusao Recepcionista
		this.getRecepcionistaExclusao().setAtivoInaivo(SituacaoEnum.INATIVO);
		boolean exclusao = this.getRecepcionistaDAO().alterar(this.getRecepcionistaExclusao());
		if(exclusao) {
			init();
			this.tratarMensagemSucesso(null);
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

		if(!Util.isStringNotBlankOrNotNull(this.getRecepcionista().getNome())) {
			this.tratarMensagemErro(null);
			valid = false;
		}
		if(!Util.isDateNotNull(this.getRecepcionista().getDataAdmissao())) {
			this.tratarMensagemErro(null);
			valid = false;
		}
		if(!Util.isStringNotBlankOrNotNull(this.getRecepcionista().getSexo())) {
			this.tratarMensagemErro(null);
			valid = false;
		}
		if(!Util.isObjectNotNull(this.getRecepcionista().getRG())) {
			this.tratarMensagemErro(null);
			valid = false;
		}
		if(Util.isObjectNotNull(this.getRecepcionista().getRG()) && this.getRecepcionista().getRG() < 0) {
			this.tratarMensagemErro(null, "MSG015");
			valid = false;
		}
		if(!Util.isObjectNotNull(this.getRecepcionista().getCPF())) {
			this.tratarMensagemErro(null);
			valid = false;
		}
		if(!Util.isCPFValido(this.getRecepcionista().getCPF())) {
			this.tratarMensagemErro(null, "MSG006");
			valid = false;
		}
		if(!Util.isStringNotBlankOrNotNull(this.getRecepcionista().getEndereco())) {
			this.tratarMensagemErro(null);
			valid = false;
		}
		if(!Util.isStringNotBlankOrNotNull(this.getRecepcionista().getBairro())) {
			this.tratarMensagemErro(null);
			valid = false;
		}
		if(!Util.isStringNotBlankOrNotNull(this.getRecepcionista().getCidade())) {
			this.tratarMensagemErro(null);
			valid = false;
		}
		if(!Util.isStringNotBlankOrNotNull(this.getRecepcionista().getUF())) {
			this.tratarMensagemErro(null);
			valid = false;
		}
		if(!Util.isDateNotNull(this.getRecepcionista().getDataNascimento())) {
			this.tratarMensagemErro(null);
			valid = false;
		}
		if(!Util.isObjectNotNull(this.getRecepcionista().getTelefone())) {
			this.tratarMensagemErro(null);
			valid = false;
		}
		if(!Util.isTelefoneValido(this.getRecepcionista().getTelefone())) {
			this.tratarMensagemErro(null);
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

	public Date getDataAtual() {
		return dataAtual;
	}

	public void setDataAtual(Date dataAtual) {
		this.dataAtual = dataAtual;
	}

	public UsuarioDAO getUsuarioDAO() {
		return usuarioDAO;
	}

	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}

}