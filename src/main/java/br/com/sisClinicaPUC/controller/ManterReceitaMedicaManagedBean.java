package br.com.sisClinicaPUC.controller;

  import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.com.sisClinicaPUC.entidade.Paciente;
import br.com.sisClinicaPUC.entidade.ReceitaMedica;
import br.com.sisClinicaPUC.persistencia.PacienteDAO;
import br.com.sisClinicaPUC.persistencia.ReceitaMedicaDAO;
import br.com.sisClinicaPUC.util.Util;
   
@Named
@ViewScoped
public class ManterReceitaMedicaManagedBean extends AbstractMangedBean<ReceitaMedica> implements Serializable{
   
	private static final long serialVersionUID = 1L;
	
	private ReceitaMedicaDAO receitaMedicaDAO = new ReceitaMedicaDAO();
	private PacienteDAO pacienteDAO = new PacienteDAO();
    private ReceitaMedica receitaMedica = new ReceitaMedica();
    private List<ReceitaMedica> receitaMedicaList = new ArrayList<ReceitaMedica>();
    private List<Paciente> pacienteList = new ArrayList<Paciente>();
    
    private Date dataAtual = new Date();
    
    public ManterReceitaMedicaManagedBean() {}

    @PostConstruct
	public void init() {
    	System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!! inicializei o ManagedBean de ReceitaMedica !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
    	this.setReceitaMedica(new ReceitaMedica(this.getMedicoSessao()));
		carregarReceitaMedicaPorMedicoList();
		carregarPacienteList();
	}
    
	public void inserirAlterar() {
    	if(Util.isValueNotBlankOrNotEmpty(this.getReceitaMedica().getId())) {
    		alterar(this.getReceitaMedica());
    	}else {
    		inserir();
    	}
    }
    
    @Override
    public void inserir() {
    	if(validarCampos()) {
    		boolean inclusao = this.getReceitaMedicaDAO().inserir(this.getReceitaMedica());
    		if (inclusao) {
    			init();
    			this.tratarMensagemSucesso("formPrincipal:growlMsgm");
    		}
    	}
	}
	
	@Override
	public void alterar(ReceitaMedica ReceitaMedica) {
		if(validarCampos()) {
			boolean alteracao = this.getReceitaMedicaDAO().alterar(ReceitaMedica);
			if (alteracao) {
				init();
				this.tratarMensagemSucesso("formPrincipal:growlMsgm");
			}
		}
		
	}

	@Override
	public void excluir() {}
	
	@Override
	public void excluir(ReceitaMedica ReceitaMedica) {}

	public void carregarAlteracao(ReceitaMedica ReceitaMedicaAlterar) {
		this.setReceitaMedica(ReceitaMedicaAlterar);
		this.getReceitaMedicaList().remove(ReceitaMedicaAlterar);
	}
	
	/**
	 * Valida os campos obrigatorios
	 */
	public boolean validarCampos() {
		boolean valid = true;

		if(!Util.isObjectNotNull(this.getReceitaMedica().getMedico())) {
			this.tratarMensagemErro("formPrincipal:growlMsgm", "MSG999");
			valid = false;
		}
		if(!Util.isObjectNotNull(this.getReceitaMedica().getPaciente())) {
			this.tratarMensagemErro("formPrincipal:growlMsgm");
			valid = false;
		}
		if(!Util.isStringNotBlankOrNotNull(this.getReceitaMedica().getDescricaoReceita())) {
			this.tratarMensagemErro("formPrincipal:growlMsgm");
			valid = false;
		}
		if(!Util.isColecaoVazia(this.getReceitaMedica().getMedicamentoList())) {
			this.tratarMensagemErro("formPrincipal:growlMsgm");
			valid = false;
		}
		
		return valid;
	}

	/**
	 * Carrega as receitas dos medicos
	 * 
	 */
	private void carregarReceitaMedicaPorMedicoList() {
		this.setReceitaMedicaList(new ArrayList<ReceitaMedica>());
		this.getReceitaMedicaList().addAll(this.getReceitaMedicaDAO().getReceitasPorMedicoList(this.getMedicoSessao()));
	}
	
	private void carregarPacienteList() {
		this.setPacienteList(new ArrayList<>());
		this.getPacienteList().addAll(this.getPacienteDAO().getPacienteAtivoList());
	}

	public ReceitaMedicaDAO getReceitaMedicaDAO() {
		return receitaMedicaDAO;
	}

	public void setReceitaMedicaDAO(ReceitaMedicaDAO receitaMedicaDAO) {
		this.receitaMedicaDAO = receitaMedicaDAO;
	}

	public ReceitaMedica getReceitaMedica() {
		return receitaMedica;
	}

	public void setReceitaMedica(ReceitaMedica receitaMedica) {
		this.receitaMedica = receitaMedica;
	}

	public List<ReceitaMedica> getReceitaMedicaList() {
		return receitaMedicaList;
	}

	public void setReceitaMedicaList(List<ReceitaMedica> receitaMedicaList) {
		this.receitaMedicaList = receitaMedicaList;
	}

	public Date getDataAtual() {
		return dataAtual;
	}

	public void setDataAtual(Date dataAtual) {
		this.dataAtual = dataAtual;
	}

	public List<Paciente> getPacienteList() {
		return pacienteList;
	}

	public void setPacienteList(List<Paciente> pacienteList) {
		this.pacienteList = pacienteList;
	}

	public PacienteDAO getPacienteDAO() {
		return pacienteDAO;
	}

	public void setPacienteDAO(PacienteDAO pacienteDAO) {
		this.pacienteDAO = pacienteDAO;
	}

}