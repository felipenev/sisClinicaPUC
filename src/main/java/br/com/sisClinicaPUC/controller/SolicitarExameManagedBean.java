package br.com.sisClinicaPUC.controller;

  import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.com.sisClinicaPUC.entidade.Exame;
import br.com.sisClinicaPUC.entidade.ExameSolicitado;
import br.com.sisClinicaPUC.entidade.Paciente;
import br.com.sisClinicaPUC.persistencia.ExameDAO;
import br.com.sisClinicaPUC.persistencia.ExameSolicitadoDAO;
import br.com.sisClinicaPUC.persistencia.PacienteDAO;
import br.com.sisClinicaPUC.persistencia.TipoExameDAO;
import br.com.sisClinicaPUC.util.Util;
   
@Named
@ViewScoped
public class SolicitarExameManagedBean extends AbstractMangedBean<Exame> implements Serializable{
   
private static final long serialVersionUID = 1L;
	
	private ExameDAO exameDAO = new ExameDAO();
	private PacienteDAO pacienteDAO = new PacienteDAO();
	private ExameSolicitadoDAO exameSolicitadoDAO = new ExameSolicitadoDAO();
	private TipoExameDAO tipoExameDAO = new TipoExameDAO();
    private Exame exame = new Exame();
    private List<Exame> exameList = new ArrayList<Exame>();
    private List<Paciente> pacienteList = new ArrayList<Paciente>();
    private List<ExameSolicitado> exameSolicitadoList = new ArrayList<ExameSolicitado>();
    
    private Date dataAtual = new Date();

    public SolicitarExameManagedBean() {
    	//TODO:
		System.out.println("Construtor SolicitarExameManagedBean");
	}

    @PostConstruct
	public void init() {
    	System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!! inicializei o ManagedBean de Solicitacao de Exame !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
    	this.setExame(new Exame(this.getMedicoSessao()));
		carregarSolicitacoesExamePorMedicoList();
		carregarPacienteMedicoList();
		carregarExameSolicitadoList();
	}
    
	public void inserirAlterar() {
    	if(Util.isValueNotBlankOrNotEmpty(this.getExame().getId())) {
    		alterar(this.getExame());
    	}else {
    		inserir();
    	}
    }
    
    @Override
    public void inserir() {
    	if(validarCampos()) {
    		boolean inclusao = this.getExameDAO().inserir(this.getExame());
    		if (inclusao) {
    			init();
    			this.tratarMensagemSucesso("formPrincipal:growlMsgm");
    		}
    	}
	}
	
	@Override
	public void alterar(Exame exame) {
		if(validarCampos()) {
			boolean alteracao = this.getExameDAO().alterar(exame);
			if (alteracao) {
				init();
				this.tratarMensagemSucesso("formPrincipal:growlMsgm");
			}
		}
		
	}

	@Override
	public void excluir() {}
	
	@Override
	public void excluir(Exame exame) {}

	public void carregarAlteracao(Exame exameAlterar) {
		this.setExame(exameAlterar);
		this.getExameList().remove(exameAlterar);
	}
	
	/**
	 * Valida os campos obrigatorios
	 */
	public boolean validarCampos() {
		boolean valid = true;

		if(!Util.isObjectNotNull(this.getExame().getMedico())) {
			this.tratarMensagemErro("formPrincipal:growlMsgm", "MSG999");
			valid = false;
		}
		if(!Util.isObjectNotNull(this.getExame().getPaciente())) {
			this.tratarMensagemErro("formPrincipal:growlMsgm");
			valid = false;
		}
		if(!Util.isColecaoVazia(this.getExame().getExameSolicitadoList())){
			this.tratarMensagemErro("formPrincipal:growlMsgm");
			valid = false;
		}
		
		return valid;
	}

	
	/**
	 * Carrega as solicitacoes de exames que foram feitas pelo medico
	 */
	private void carregarSolicitacoesExamePorMedicoList() {
		// TODO Auto-generated method stub
		
	}
	
	//TODO:Carregar pacientes da consulta do medico
	/**
	 * Carrega os pacientes que possuem consulta marcada com o medico no dia
	 */
	private void carregarPacienteMedicoList() {
		this.setPacienteList(new ArrayList<Paciente>());
		this.getPacienteList().addAll(this.getPacienteDAO().getPacienteAtivoList());
	}

	/**
	 * Carrega os exames que podem ser solicitados pelo medico
	 */
	private void carregarExameSolicitadoList() {
		// TODO Auto-generated method stub
		this.setExameSolicitadoList(new ArrayList<ExameSolicitado>());
		this.getExameSolicitadoList().addAll(this.getExameSolicitadoDAO().getExameSolicitadoDisponivelList(tipoExameDAO));
	}
	
	//GETTERS AND SETTERS
	public ExameDAO getExameDAO() {
		return exameDAO;
	}

	public void setExameDAO(ExameDAO exameDAO) {
		this.exameDAO = exameDAO;
	}

	public PacienteDAO getPacienteDAO() {
		return pacienteDAO;
	}

	public void setPacienteDAO(PacienteDAO pacienteDAO) {
		this.pacienteDAO = pacienteDAO;
	}

	public ExameSolicitadoDAO getExameSolicitadoDAO() {
		return exameSolicitadoDAO;
	}

	public void setExameSolicitadoDAO(ExameSolicitadoDAO exameSolicitadoDAO) {
		this.exameSolicitadoDAO = exameSolicitadoDAO;
	}

	public Exame getExame() {
		return exame;
	}

	public void setExame(Exame exame) {
		this.exame = exame;
	}

	public List<Exame> getExameList() {
		return exameList;
	}

	public void setExameList(List<Exame> exameList) {
		this.exameList = exameList;
	}

	public List<Paciente> getPacienteList() {
		return pacienteList;
	}

	public void setPacienteList(List<Paciente> pacienteList) {
		this.pacienteList = pacienteList;
	}

	public List<ExameSolicitado> getExameSolicitadoList() {
		return exameSolicitadoList;
	}

	public void setExameSolicitadoList(List<ExameSolicitado> exameSolicitadoList) {
		this.exameSolicitadoList = exameSolicitadoList;
	}

	public Date getDataAtual() {
		return dataAtual;
	}

	public void setDataAtual(Date dataAtual) {
		this.dataAtual = dataAtual;
	}

}