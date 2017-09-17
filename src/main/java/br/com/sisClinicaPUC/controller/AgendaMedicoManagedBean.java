package br.com.sisClinicaPUC.controller;

  import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.com.sisClinicaPUC.entidade.AgendaMedico;
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
    private AgendaMedico agendaMedicoExclusao = new AgendaMedico();
    private List<AgendaMedico> agendaMedicoList = new ArrayList<AgendaMedico>();
    
    private Date dataMinima = new Date();
    
    public AgendaMedicoManagedBean() {}

    @PostConstruct
	public void init() {
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!Inicializei o AgendaMedicamentoManagedBean!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		this.setAgendaMedico(new AgendaMedico(this.getMedicoSessao()));
		this.setAgendaMedicoExclusao(new AgendaMedico());
		carregarAgendaMedicoList();
	}
    
    public void inserirAlterar() {
    	
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
    			init();
    			this.tratarMensagemSucesso(null);
    		}
    	}
	}
	
	@Override
	public void alterar(AgendaMedico objeto) {
		if(validarCampos() && validarData()) {
			boolean alteracao = this.getAgendaMedicoDAO().alterar(objeto);
			if (alteracao) {
				init();
				this.tratarMensagemSucesso(null);
			}
		}
		
	}

	@Override
	public void excluir() {
		//Exclusao logica
		this.getAgendaMedicoExclusao().setAtivoInaivo(SituacaoEnum.INATIVO);
		boolean exclusao = this.getAgendaMedicoDAO().alterar(this.getAgendaMedicoExclusao());
		if(exclusao) {
			init();
			this.tratarMensagemSucesso(null);
		}
		
	}
	
	@Override
	public void excluir(AgendaMedico med) {}

	public void carregarAlteracao(AgendaMedico agendaMedicoAlterar) {
		this.setAgendaMedico(new AgendaMedico(this.getMedicoSessao()));
		carregarAgendaMedicoList();
		AgendaMedico agAlterar = Util.cloneSerializable(agendaMedicoAlterar);
		this.setAgendaMedico(agAlterar);
//		this.getAgendaMedicoList().remove(medicoAlterar);
		
	}
	
	/**
	 * Valida os campos obrigatorios
	 */
	public boolean validarCampos() {
		boolean valid = true;
		
		if(!Util.isDateNotNull(this.getAgendaMedico().getData())) {
			this.tratarMensagemErro(null);
			valid = false;
		}
		if(!Util.isDateNotNull(this.getAgendaMedico().getHorarioInicioAtendimento())) {
			this.tratarMensagemErro(null);
			valid = false;
		}
		if(!Util.isDateNotNull(this.getAgendaMedico().getHorarioFimAtendimento())) {
			this.tratarMensagemErro(null);
			valid = false;
		}
		
		if(valid) {
			if(this.getAgendaMedicoDAO().verificaAgendamentoHorario(this.getMedicoSessao(), this.getAgendaMedico())) {
				this.tratarMensagemErro(null, "MSG014");
				valid = false;
			}
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
		
		if(this.getAgendaMedico().getHorarioFimAtendimento().before(this.getAgendaMedico().getHorarioInicioAtendimento())) {
			this.tratarMensagemErro(null, "MSG005");
			valid = false;
		}
		
		if(this.getAgendaMedico().getHorarioFimAtendimento().compareTo(this.getAgendaMedico().getHorarioInicioAtendimento()) == 0) {
			this.tratarMensagemErro(null, "MSG005");
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
	private void carregarAgendaMedicoList() {
		this.setAgendaMedicoList(new ArrayList<AgendaMedico>());
		this.getAgendaMedicoList().addAll(this.getAgendaMedicoDAO().getAgendaMedicoList(this.getMedicoSessao()));
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

	public Date getDataMinima() {
		return dataMinima;
	}

	public void setDataMinima(Date dataMinima) {
		this.dataMinima = dataMinima;
	}

}