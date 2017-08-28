package br.com.sisClinicaPUC.controller;

  import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.com.sisClinicaPUC.entidade.Medico;
import br.com.sisClinicaPUC.persistencia.MedicoDAO;
import br.com.sisClinicaPUC.util.Util;
import br.com.sisClinicaPUC.vo.SituacaoEnum;
   
@Named
@ViewScoped
public class ManterMedicoManagedBean extends AbstractMangedBean<Medico> implements Serializable{
   
	private static final long serialVersionUID = 1L;
	
	private MedicoDAO medicoDAO = new MedicoDAO();
    private Medico medico = new Medico();
    private Medico medicoExclusao = new Medico();
    private List<Medico> medicoList = new ArrayList<Medico>();
    
    private Date dataAtual = new Date();
    
    public ManterMedicoManagedBean() {}

    @PostConstruct
	public void init() {
    	System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!! inicializei o ManagedBean de Medico !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
    	this.setMedico(new Medico());
		this.setMedicoExclusao(new Medico());
		carregarMedicoAtivoList();
	}
    
    public void inserirAlterar() {
    	if(Util.isValueNotBlankOrNotEmpty(this.getMedico().getId())) {
    		alterar(this.getMedico());
    	}else {
    		inserir();
    	}
    }
    
    @Override
    public void inserir() {
    	if(validarCampos()) {
    		boolean inclusao = this.getMedicoDAO().inserir(this.getMedico());
    		if (inclusao) {
    			init();
    			this.tratarMensagemSucesso("formPrincipal:growlMsgm");
    		}
    	}
	}
	
	@Override
	public void alterar(Medico medico) {
		if(validarCampos()) {
			boolean alteracao = this.getMedicoDAO().alterar(medico);
			if (alteracao) {
				init();
				this.tratarMensagemSucesso("formPrincipal:growlMsgm");
			}
		}
		
	}

	@Override
	public void excluir() {
		//Exclusao logica
		this.getMedicoExclusao().setAtivoInaivo(SituacaoEnum.INATIVO);
		boolean exclusao = this.getMedicoDAO().alterar(this.getMedicoExclusao());
		if(exclusao) {
			init();
			this.tratarMensagemSucesso("formPrincipal:growlMsgm");
		}
		
	}
	
	@Override
	public void excluir(Medico med) {}

	public void carregarAlteracao(Medico medicoAlterar) {
		this.setMedico(medicoAlterar);
		this.getMedicoList().remove(medicoAlterar);
	}
	
	/**
	 * Valida os campos obrigatorios
	 */
	public boolean validarCampos() {
		boolean valid = true;

		if(!Util.isStringNotBlankOrNotNull(this.getMedico().getNome())) {
			this.tratarMensagemErro("formPrincipal:growlMsgm");
			valid = false;
		}
		if(!Util.isStringNotBlankOrNotNull(this.getMedico().getCRM())) {
			this.tratarMensagemErro("formPrincipal:growlMsgm");
			valid = false;
		}
		if(!Util.isCRMValido(this.getMedico().getCRM())) {
			this.tratarMensagemErro("formPrincipal:growlMsgm");
			valid = false;
		}
		if(!Util.isStringNotBlankOrNotNull(this.getMedico().getSexo())) {
			this.tratarMensagemErro("formPrincipal:growlMsgm");
			valid = false;
		}
		if(!Util.isObjectNotNull(this.getMedico().getRG())) {
			this.tratarMensagemErro("formPrincipal:growlMsgm");
			valid = false;
		}
		if(!Util.isObjectNotNull(this.getMedico().getCPF())) {
			this.tratarMensagemErro("formPrincipal:growlMsgm");
			valid = false;
		}
		if(!Util.isCPFValido(this.getMedico().getCPF())) {
			this.tratarMensagemErro("formPrincipal:growlMsgm", "MSG006");
			valid = false;
		}
		if(!Util.isStringNotBlankOrNotNull(this.getMedico().getEndereco())) {
			this.tratarMensagemErro("formPrincipal:growlMsgm");
			valid = false;
		}
		if(!Util.isStringNotBlankOrNotNull(this.getMedico().getBairro())) {
			this.tratarMensagemErro("formPrincipal:growlMsgm");
			valid = false;
		}
		if(!Util.isStringNotBlankOrNotNull(this.getMedico().getCidade())) {
			this.tratarMensagemErro("formPrincipal:growlMsgm");
			valid = false;
		}
		if(!Util.isStringNotBlankOrNotNull(this.getMedico().getUF())) {
			this.tratarMensagemErro("formPrincipal:growlMsgm");
			valid = false;
		}
		if(!Util.isDateNotNull(this.getMedico().getDataNascimento())) {
			this.tratarMensagemErro("formPrincipal:growlMsgm");
			valid = false;
		}
		if(!Util.isObjectNotNull(this.getMedico().getTelefone())) {
			this.tratarMensagemErro("formPrincipal:growlMsgm");
			valid = false;
		}
		if(!Util.isTelefoneValido(this.getMedico().getTelefone())) {
			this.tratarMensagemErro("formPrincipal:growlMsgm");
			valid = false;
		}
		
		return valid;
	}

	/**
	 * Carrega os medicos ativos que podem ser apresentados
	 * 
	 */
	private void carregarMedicoAtivoList() {
		this.setMedicoList(new ArrayList<Medico>());
		this.getMedicoList().addAll(this.getMedicoDAO().getMedicoAtivoList());
	}

	public MedicoDAO getMedicoDAO() {
		return medicoDAO;
	}

	public void setMedicoDAO(MedicoDAO medicoDAO) {
		this.medicoDAO = medicoDAO;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public Medico getMedicoExclusao() {
		return medicoExclusao;
	}

	public void setMedicoExclusao(Medico medicoExclusao) {
		this.medicoExclusao = medicoExclusao;
	}

	public List<Medico> getMedicoList() {
		return medicoList;
	}

	public void setMedicoList(List<Medico> medicoList) {
		this.medicoList = medicoList;
	}

	public Date getDataAtual() {
		return dataAtual;
	}

	public void setDataAtual(Date dataAtual) {
		this.dataAtual = dataAtual;
	}
	
//	public String getCPFFormatado() {
//		try {
//			return Util.formatarString(Util.MASCARA_CPF, String.valueOf(this.getMedico().getCPF()));
//		} catch (ParseException e) {
//			this.tratarMensagemErro(null, e.getMessage());
//		}
//		return "";
//	}
//	
//	public void setCPFFormatado(String cpfFormatado) {
//		try {
//			String retorno = Util.desformatarString(Util.MASCARA_CPF, cpfFormatado);
//			this.getMedico().setCPF(Integer.valueOf(retorno));
//		} catch (ParseException e) {
//			this.tratarMensagemErro(null, e.getMessage());
//		}
//	}

}