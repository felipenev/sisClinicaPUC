package br.com.sisClinicaPUC.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.com.sisClinicaPUC.entidade.Exame;
import br.com.sisClinicaPUC.entidade.Historico;
import br.com.sisClinicaPUC.persistencia.ExameDAO;
import br.com.sisClinicaPUC.persistencia.PacienteDAO;
import br.com.sisClinicaPUC.util.Util;
import br.com.sisClinicaPUC.vo.OperacaoEnum;
import br.com.sisClinicaPUC.vo.SituacaoExameEnum;
import br.com.sisClinicaPUC.vo.TipoPesquisaHistoricoEnum;
   
@Named
@ViewScoped
public class AvaliarExameManagedBean extends AbstractMangedBean<Exame> implements Serializable{
   
	private static final long serialVersionUID = 1L;
	
	private ExameDAO exameDAO = new ExameDAO();
	private PacienteDAO pacienteDAO = new PacienteDAO();
    private Exame exame = new Exame();
    private Exame exameAvaliadoPorMedico = new Exame();
    private List<Exame> exameList = new ArrayList<Exame>();
    
    private Date dataAtual = new Date();

    public AvaliarExameManagedBean() {
	}

    //TODO: AJUSTAR O BOTAO DE EDICAO DO MEDICO.. COLOCAR VALIDACAO DE PERFIL PARA APRESENTAR O BOTAO
    
    @PostConstruct
	public void init() {
    	System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!! inicializei o ManagedBean de Avaliacao de Exame !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
    	this.setExame(new Exame());
    	this.setExameAvaliadoPorMedico(new Exame());
		carregarExameAvaliacaoList();
	}
    
	public void inserirAlterar() {
		this.getExame().setSituacaoExame(SituacaoExameEnum.AGUARDANDO_ANALISE_MEDICO);
   		alterar(this.getExame());
    }
    
    @Override
    public void inserir() {}
	
	@Override
	public void alterar(Exame exame) {
		if(validarCampos(exame)) {
			boolean alteracao = this.getExameDAO().alterar(exame);
			if (alteracao) {
				
				montarHistorico(OperacaoEnum.ALTERAR, exame);
				
				init();
				this.tratarMensagemSucesso(null);
			}
		}
	}

	@Override
	public void excluir() {}
	
	@Override
	public void excluir(Exame exame) {}

	 /**
     * Monta o historico para inclusao
     * 
     * @param operacao
     * @param consulta
     */
	private void montarHistorico(OperacaoEnum operacao, Exame exame) {
		Historico historico = new Historico();
		historico.setOperacao(operacao);
		historico.setExame(exame);
		historico.setTipoPesquisaHistoricoEnum(TipoPesquisaHistoricoEnum.RESULTADOS_OBTIDOS);
		
		//Gravando historico
		this.gravarHistorico(historico);
	}
	
	/**
	 * Valida os campos obrigatorios
	 * @param exameValidacao 
	 */
	public boolean validarCampos(Exame exameValidacao) {
		boolean valid = true;

		if(!Util.isValueNotBlankOrNotEmpty(exameValidacao.getId())) {
			this.tratarMensagemErro(null, "MSG011");
			valid = false;
			return valid;
		}
		if(!Util.isObjectNotNull(exameValidacao.getResultado())) {
			this.tratarMensagemErro(null);
			valid = false;
		}
		
		return valid;
	}

	public void editarAvaliacaoExame(Exame exameAlterar) {
		this.setExame(new Exame());
		carregarExameAvaliacaoList();
		Exame exaAlterar = Util.cloneSerializable(exameAlterar);
		this.setExame(exaAlterar);
//		this.getExameList().remove(exaAlterar);
	}
	
	/**
	 * Altera a situacao do exame para AGUARDANDO_RETORNO_PACIENTE
	 */
	public void aguardarRetornoPaciente() {
		this.getExameAvaliadoPorMedico().setSituacaoExame(SituacaoExameEnum.AGUARDANDO_RETORNO_PACIENTE);
   		alterar(this.getExameAvaliadoPorMedico());
	}
	
	/**
	 * Altera a situacao do exame para ENTREGAR_RESULTADO_PACIENTE
	 */
	public void entregarResultadoPaciente() {
		this.getExameAvaliadoPorMedico().setSituacaoExame(SituacaoExameEnum.ENTREGAR_RESULTADO_PACIENTE);
		alterar(this.getExameAvaliadoPorMedico());
	}

	/**
	 * Carrega os exames que para inclusao dos resultados clinicos.
	 */
	private void carregarExameAvaliacaoList() {
		this.setExameList(new ArrayList<Exame>());
		this.getExameList().addAll(this.getExameDAO().getList());
//		this.getExameList().addAll(this.getExameDAO().getExamesPendentesResultado());
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

	public Date getDataAtual() {
		return dataAtual;
	}

	public void setDataAtual(Date dataAtual) {
		this.dataAtual = dataAtual;
	}

	public Exame getExameAvaliadoPorMedico() {
		return exameAvaliadoPorMedico;
	}

	public void setExameAvaliadoPorMedico(Exame exameAvaliadoPorMedico) {
		this.exameAvaliadoPorMedico = exameAvaliadoPorMedico;
	}

}