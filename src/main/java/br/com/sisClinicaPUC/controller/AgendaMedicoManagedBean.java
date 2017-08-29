package br.com.sisClinicaPUC.controller;

  import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.com.sisClinicaPUC.entidade.AgendaMedico;
import br.com.sisClinicaPUC.entidade.DataAtendimento;
import br.com.sisClinicaPUC.entidade.Medico;
import br.com.sisClinicaPUC.persistencia.AgendaMedicoDAO;
import br.com.sisClinicaPUC.util.Util;
import br.com.sisClinicaPUC.vo.SituacaoEnum;
   
@Named
@ViewScoped
public class AgendaMedicoManagedBean extends AbstractMangedBean<AgendaMedico> implements Serializable{
   
	private static final long serialVersionUID = 1L;
	
//	@ManagedProperty(name="em", value="#{requestScope.em}")
//	private EntityManager em;
	
	private AgendaMedicoDAO agendaMedicoDAO = new AgendaMedicoDAO();
    private AgendaMedico agendaMedico = new AgendaMedico();
    private DataAtendimento dataAtendimento = new DataAtendimento();
    private AgendaMedico agendaMedicoExclusao = new AgendaMedico();
    private List<AgendaMedico> agendaMedicoList = new ArrayList<AgendaMedico>();
    
    private Date dataMinima = new Date();
    
    public AgendaMedicoManagedBean() {}

    @PostConstruct
	public void init() {
    	//TODO: COLOCAR NOME DO MEDICO NA TELA
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!Inicializei o AgendaMedicamentoManagedBean!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		this.setAgendaMedico(new AgendaMedico());
		this.setAgendaMedicoExclusao(new AgendaMedico());
		carregarAgendaMedicoAtivoList();
	}
    
    public void inserirAlterar() {
    	
    	//TODO: testeMedico
    	this.getAgendaMedico().setMedico(new Medico());
    	
    	this.getAgendaMedico().setDataAtendimento(new HashSet<>(Arrays.asList(this.getDataAtendimento())));
    	
    	if(Util.isValueNotBlankOrNotEmpty(this.getAgendaMedico().getId())) {
    		alterar(this.getAgendaMedico());
    	}else {
    		inserir();
    	}
    }
    
    @Override
    public void inserir() {
    	if(validarCampos() && validarData()) {
    		boolean inclusao = this.getAgendaMedicoDAO().inserir(this.getAgendaMedico());
    		if (inclusao) {
    			this.setAgendaMedico(new AgendaMedico());
    			carregarAgendaMedicoAtivoList();
    			this.tratarMensagemSucesso("formPrincipal:growlMsgm");
    		}
    	}
	}
	
	@Override
	public void alterar(AgendaMedico objeto) {
		if(validarCampos() && validarData()) {
			boolean alteracao = this.getAgendaMedicoDAO().alterar(objeto);
			if (alteracao) {
				this.setAgendaMedico(new AgendaMedico());
				carregarAgendaMedicoAtivoList();
				this.tratarMensagemSucesso("formPrincipal:growlMsgm");
			}
		}
		
	}

	@Override
	public void excluir() {
		//Exclusao logica
		this.getAgendaMedicoExclusao().setAtivoInaivo(SituacaoEnum.INATIVO);
		boolean exclusao = this.getAgendaMedicoDAO().alterar(this.getAgendaMedicoExclusao());
		if(exclusao) {
			agendaMedico = new AgendaMedico();
			agendaMedicoExclusao = new AgendaMedico();
			carregarAgendaMedicoAtivoList();
			this.tratarMensagemSucesso("formPrincipal:growlMsgm");
		}
		
	}
	
	@Override
	public void excluir(AgendaMedico med) {}

	public void carregarAlteracao(AgendaMedico agendaMedicoAlterar) {
		this.setAgendaMedico(agendaMedicoAlterar);
		this.getAgendaMedicoList().remove(agendaMedicoAlterar);
	}
	
	/**
	 * Valida os campos obrigatorios
	 */
	public boolean validarCampos() {
		boolean valid = true;
		
		if(!Util.isDateNotNull(this.getDataAtendimento().getData())) {
			this.tratarMensagemErro("formPrincipal:idDataAgenda_input");
			valid = false;
		}
		if(!Util.isDateNotNull(this.getDataAtendimento().getHorarioInicioAtendimento())) {
			this.tratarMensagemErro("formPrincipal:idHoraInicio_input");
			valid = false;
		}
		if(!Util.isDateNotNull(this.getDataAtendimento().getHorarioFimAtendimento())) {
			this.tratarMensagemErro("formPrincipal:idHoraFim_input");
			valid = false;
		}
		
		return valid;
	}
	
	/**
	 * Verifica se a data informada é válida.
	 * 
	 * @return
	 */
	private boolean validarData() {
		boolean valid = true;
		
		if(this.getDataAtendimento().getHorarioFimAtendimento().before(this.getDataAtendimento().getHorarioInicioAtendimento())) {
			this.tratarMensagemErro("formPrincipal:idHoraFim_input", "MSG005");
			valid = false;
		}
		
		if(this.getDataAtendimento().getHorarioFimAtendimento().compareTo(this.getDataAtendimento().getHorarioInicioAtendimento()) == 0) {
			this.tratarMensagemErro("formPrincipal:idHoraFim_input", "MSG005");
			valid = false;
		}
		
		return valid;
	}
	

//	private void carregarMedicamentoList() {
//		this.setMedicamentoList(new ArrayList<Medicamento>());
//    	this.getMedicamentoList().addAll(this.getMedicamentoDAO().getList());
//	}
	
	/**
	 * Carrega os medicamentos ativos que podem ser apresentados
	 * 
	 */
	private void carregarAgendaMedicoAtivoList() {
		this.setAgendaMedicoList(new ArrayList<AgendaMedico>());
		this.getAgendaMedicoList().addAll(this.getAgendaMedicoDAO().getAgendaMedicamentoAtivoList());
	}

	//Get and Setter
	
	public AgendaMedicoDAO getAgendaMedicoDAO() {
		return agendaMedicoDAO;
	}

	public void setAgendaMedicoDAO(AgendaMedicoDAO agendaMedicoDAO) {
		this.agendaMedicoDAO = agendaMedicoDAO;
	}

	public AgendaMedico getAgendaMedico() {
		return agendaMedico;
	}

	public void setAgendaMedico(AgendaMedico agendaMedico) {
		this.agendaMedico = agendaMedico;
	}

	public AgendaMedico getAgendaMedicoExclusao() {
		return agendaMedicoExclusao;
	}

	public void setAgendaMedicoExclusao(AgendaMedico agendaMedicoExclusao) {
		this.agendaMedicoExclusao = agendaMedicoExclusao;
	}

	public List<AgendaMedico> getAgendaMedicoList() {
		return agendaMedicoList;
	}

	public void setAgendaMedicoList(List<AgendaMedico> agendaMedicoList) {
		this.agendaMedicoList = agendaMedicoList;
	}

	public DataAtendimento getDataAtendimento() {
		return dataAtendimento;
	}

	public void setDataAtendimento(DataAtendimento dataAtendimento) {
		this.dataAtendimento = dataAtendimento;
	}

	public Date getDataMinima() {
		return dataMinima;
	}

	public void setDataMinima(Date dataMinima) {
		this.dataMinima = dataMinima;
	}

}