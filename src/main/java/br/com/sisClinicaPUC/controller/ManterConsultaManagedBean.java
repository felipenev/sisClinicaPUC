package br.com.sisClinicaPUC.controller;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.com.sisClinicaPUC.entidade.Consulta;
import br.com.sisClinicaPUC.entidade.Medico;
import br.com.sisClinicaPUC.entidade.Paciente;
import br.com.sisClinicaPUC.persistencia.ConsultaDAO;
import br.com.sisClinicaPUC.persistencia.MedicoDAO;
import br.com.sisClinicaPUC.persistencia.PacienteDAO;
import br.com.sisClinicaPUC.util.Util;
import br.com.sisClinicaPUC.vo.SituacaoConsultaEnum;
   
@Named
@ViewScoped
public class ManterConsultaManagedBean extends AbstractMangedBean<Consulta> implements Serializable{
   
private static final long serialVersionUID = 1L;
	
	private ConsultaDAO consultaDAO = new ConsultaDAO();
	private PacienteDAO pacienteDAO = new PacienteDAO();
	private MedicoDAO medicoDAO = new MedicoDAO();
    private Consulta consulta = new Consulta();
    private Consulta consultaExclusao = new Consulta();
    private List<Consulta> consultaList = new ArrayList<Consulta>();
    private List<Paciente> pacienteList = new ArrayList<Paciente>();
    private List<Medico> medicoList = new ArrayList<Medico>();
    
    public ManterConsultaManagedBean() {
    	//TODO:
		System.out.println("Construtor ManterConsultaManagedBean");
	}

    @PostConstruct
	public void init() {
    	System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!! inicializei o ManagedBean de Manter Consulta !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
    	this.setConsulta(new Consulta(this.getRecepcionistaSessao()));
		carregarConsultaList();
		carregarPacienteList();
		carregarMedicoAtivoList();
	}
    
	public void inserirAlterar() {
    	if(Util.isValueNotBlankOrNotEmpty(this.getConsulta().getId())) {
    		alterar(this.getConsulta());
    	}else {
    		inserir();
    	}
    }
    
    @Override
    public void inserir() {
    	if(validarCampos()) {
//    		boolean inclusao = this.getExameDAO().inserir(this.getExame());
    		boolean inclusao = this.getConsultaDAO().alterar(this.getConsulta());
    		if (inclusao) {
    			init();
    			this.tratarMensagemSucesso("formPrincipal:growlMsgm");
    		}
    	}
	}
	
	@Override
	public void alterar(Consulta consulta) {
		if(validarCampos()) {
			boolean alteracao = this.getConsultaDAO().alterar(consulta);
			if (alteracao) {
				init();
				this.tratarMensagemSucesso("formPrincipal:growlMsgm");
			}
		}
		
	}

	@Override
	public void excluir() {}
	
	@Override
	public void excluir(Consulta consulta) {}

	public void carregarAlteracao(Consulta consultaAlterar) {
		this.setConsulta(new Consulta(this.getRecepcionistaSessao()));
		carregarConsultaList();
		Consulta consAlterar = Util.cloneSerializable(consultaAlterar);
		this.setConsulta(consAlterar);
//		this.getExameList().remove(exaAlterar);
	}
	
	public void cancelarConsulta() {
		//Exclusao logica
		this.getConsultaExclusao().setSituacaoConsulta(SituacaoConsultaEnum.CANCELADA);
		boolean consultaCancelada = this.getConsultaDAO().alterar(this.getConsultaExclusao());
		if(consultaCancelada) {
			init();
			this.tratarMensagemSucesso("formPrincipal:growlMsgm");
		}
	}
	
	/**
	 * Valida os campos obrigatorios
	 */
	public boolean validarCampos() {
		boolean valid = true;

		if(!Util.isObjectNotNull(this.getConsulta().getPaciente())) {
			this.tratarMensagemErro("formPrincipal:growlMsgm", "MSG999");
			valid = false;
		}
		if(!Util.isObjectNotNull(this.getConsulta().getMedico())) {
			this.tratarMensagemErro("formPrincipal:growlMsgm");
			valid = false;
		}
		if(!Util.isDateNotNull(this.getConsulta().getDataConsulta())){
			this.tratarMensagemErro("formPrincipal:growlMsgm");
			valid = false;
		}
		
		return valid;
	}

	public void alterarCalendario() {
		System.out.println("Alterei o medico " + this.getConsulta().getMedico().getId());
	}
	
	/**
	 * Carrega as consultas
	 */
	private void carregarConsultaList() {
		this.setConsultaList(new ArrayList<Consulta>());
		this.getConsultaList().addAll(this.getConsultaDAO().getList());
	}
	
	/**
	 * Carrega os pacientes.
	 */
	private void carregarPacienteList() {
		this.setPacienteList(new ArrayList<Paciente>());
		this.getPacienteList().addAll(this.getPacienteDAO().getPacienteAtivoList());
	}

	/**
	 * Carrega os medicos com agenda para a data selecionada no exame.
	 */
	private void carregarMedicoAtivoList() {
		this.setMedicoList(new ArrayList<Medico>());
		this.getMedicoList().addAll(this.getMedicoDAO().getMedicoAtivoList());
	}

	//GETTERS AND SETTERS
	public ConsultaDAO getConsultaDAO() {
		return consultaDAO;
	}

	public void setConsultaDAO(ConsultaDAO consultaDAO) {
		this.consultaDAO = consultaDAO;
	}

	public PacienteDAO getPacienteDAO() {
		return pacienteDAO;
	}

	public void setPacienteDAO(PacienteDAO pacienteDAO) {
		this.pacienteDAO = pacienteDAO;
	}

	public MedicoDAO getMedicoDAO() {
		return medicoDAO;
	}

	public void setMedicoDAO(MedicoDAO medicoDAO) {
		this.medicoDAO = medicoDAO;
	}

	public Consulta getConsulta() {
		return consulta;
	}

	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}

	public List<Consulta> getConsultaList() {
		return consultaList;
	}

	public void setConsultaList(List<Consulta> consultaList) {
		this.consultaList = consultaList;
	}

	public List<Paciente> getPacienteList() {
		return pacienteList;
	}

	public void setPacienteList(List<Paciente> pacienteList) {
		this.pacienteList = pacienteList;
	}

	public List<Medico> getMedicoList() {
		return medicoList;
	}

	public void setMedicoList(List<Medico> medicoList) {
		this.medicoList = medicoList;
	}

	public Consulta getConsultaExclusao() {
		return consultaExclusao;
	}

	public void setConsultaExclusao(Consulta consultaExclusao) {
		this.consultaExclusao = consultaExclusao;
	}

	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	//teste
	public String[] getSpecialDays() {
		  
		String[] result = new String[3];


		Calendar cal1 = Calendar.getInstance();
		// yesterday
		cal1.add(Calendar.DATE, -15);
		result[0] = String.format("'%s'", sdf.format(cal1.getTime()));


		Calendar cal2 = Calendar.getInstance();
		// Today
		cal2.add(Calendar.DATE, -13);
		result[1] = String.format("'%s'", sdf.format(cal2.getTime()));

		Calendar cal3 = Calendar.getInstance();
		// Tomorrow
		cal3.add(Calendar.DATE, -11);
		result[2] = String.format("'%s'", sdf.format(cal3.getTime()));

		return result;
	}
	
}