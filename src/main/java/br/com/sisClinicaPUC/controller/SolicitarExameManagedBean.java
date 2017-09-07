package br.com.sisClinicaPUC.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;

import br.com.sisClinicaPUC.entidade.Exame;
import br.com.sisClinicaPUC.entidade.Paciente;
import br.com.sisClinicaPUC.entidade.TipoExame;
import br.com.sisClinicaPUC.persistencia.ConsultaDAO;
import br.com.sisClinicaPUC.persistencia.ExameDAO;
import br.com.sisClinicaPUC.persistencia.PacienteDAO;
import br.com.sisClinicaPUC.persistencia.TipoExameDAO;
import br.com.sisClinicaPUC.util.Util;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
   
@Named
@ViewScoped
public class SolicitarExameManagedBean extends AbstractMangedBean<Exame> implements Serializable{
   
private static final long serialVersionUID = 1L;
	
	private ExameDAO exameDAO = new ExameDAO();
	private PacienteDAO pacienteDAO = new PacienteDAO();
	private TipoExameDAO tipoExameDAO = new TipoExameDAO();
	private ConsultaDAO consultaDAO = new ConsultaDAO();
    private Exame exame = new Exame();
    private List<Exame> exameList = new ArrayList<Exame>();
    private List<Paciente> pacienteList = new ArrayList<Paciente>();
    private List<TipoExame> tipoExameList = new ArrayList<TipoExame>();
    
    private Date dataAtual = new Date();

    public SolicitarExameManagedBean() {
	}

    @PostConstruct
	public void init() {
    	System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!! inicializei o ManagedBean de Solicitacao de Exame !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
    	this.setExame(new Exame(this.getMedicoSessao()));
		carregarSolicitacoesExamePorMedicoList();
		carregarPacienteMedicoList();
		carregarTipoExameList();
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
//    		boolean inclusao = this.getExameDAO().inserir(this.getExame());
    		boolean inclusao = this.getExameDAO().alterar(this.getExame());
    		if (inclusao) {
    			init();
    			this.tratarMensagemSucesso(null);
    		}
    	}
	}
	
	@Override
	public void alterar(Exame exame) {
		if(validarCampos()) {
			boolean alteracao = this.getExameDAO().alterar(exame);
			if (alteracao) {
				init();
				this.tratarMensagemSucesso(null);
			}
		}
		
	}

	@Override
	public void excluir() {}
	
	@Override
	public void excluir(Exame exame) {}

	public void carregarAlteracao(Exame exameAlterar) {
		this.setExame(new Exame(this.getMedicoSessao()));
		carregarSolicitacoesExamePorMedicoList();
		Exame exaAlterar = Util.cloneSerializable(exameAlterar);
		this.setExame(exaAlterar);
//		this.getExameList().remove(exaAlterar);
	}
	
	/**
	 * Valida os campos obrigatorios
	 */
	public boolean validarCampos() {
		boolean valid = true;

		if(!Util.isObjectNotNull(this.getExame().getMedico())) {
			this.tratarMensagemErro(null, "MSG999");
			valid = false;
		}
		if(!Util.isObjectNotNull(this.getExame().getPaciente())) {
			this.tratarMensagemErro(null);
			valid = false;
		}
		if(!Util.isColecaoNaoVazia(this.getExame().getTipoExameList())){
			this.tratarMensagemErro(null);
			valid = false;
		}
		
		return valid;
	}

	
	/**
	 * Carrega as solicitacoes de exames que foram feitas pelo medico
	 */
	private void carregarSolicitacoesExamePorMedicoList() {
		this.setExameList(new ArrayList<Exame>());
		this.getExameList().addAll(this.getExameDAO().getExamesSolicitadosPorMedico(this.getMedicoSessao()));
	}
	
	//TODO:Carregar pacientes da consulta do medico
	/**
	 * Carrega os pacientes que possuem consulta marcada com o medico na data do atendimento
	 */
	private void carregarPacienteMedicoList() {
		this.setPacienteList(new ArrayList<Paciente>());
		this.getPacienteList().addAll(this.getConsultaDAO().getPacientePorMedicoDataConsultaList(this.getMedicoSessao()));
	}

	/**
	 * Carrega os tipos de exames disponiveis para solicitacao.
	 */
	private void carregarTipoExameList() {
		this.setTipoExameList(new ArrayList<TipoExame>());
		this.getTipoExameList().addAll(this.getTipoExameDAO().getList());
	}
	
	public void gerarSolicitacaoPDFExame(Exame exameRelatorio) {
		
		try {
            System.out.println("entrou no solicitar exame PDF");
            if(!Util.isObjectNotNull(exameRelatorio)) {
            	return;
            }
            //---------- gera o relatorio ----------
            HashMap<String,Object> parametros = new HashMap<String,Object>();
            parametros.put("medico", exameRelatorio.getMedico());
            parametros.put("paciente", exameRelatorio.getPaciente());
            JasperReport jasperReport = JasperCompileManager.compileReport(getClass().getResourceAsStream("/relatorio/SolicitacaoExame.jrxml"));
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, new JRBeanCollectionDataSource(exameRelatorio.getTipoExameList()));
            byte[] b = JasperExportManager.exportReportToPdf(jasperPrint); 
            HttpServletResponse res = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            res.setContentType("application/pdf");
            //Código abaixo gerar o relatório e disponibiliza diretamente na página 
            res.setHeader("Content-disposition", "inline;filename=arquivo.pdf");
            //Código abaixo gerar o relatório e disponibiliza para o cliente baixar ou salvar 
            //res.setHeader("Content-disposition", "attachment;filename=arquivo.pdf");
            res.getOutputStream().write(b);
            res.getCharacterEncoding();
            FacesContext.getCurrentInstance().responseComplete();
            System.out.println("saiu do visualizar relatorio");
        } catch (Exception ex) {
            ex.printStackTrace();
            this.tratarMensagemErro(null, ex.getMessage());
        }
		
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

	public List<Paciente> getPacienteList() {
		return pacienteList;
	}

	public void setPacienteList(List<Paciente> pacienteList) {
		this.pacienteList = pacienteList;
	}

	public Date getDataAtual() {
		return dataAtual;
	}

	public void setDataAtual(Date dataAtual) {
		this.dataAtual = dataAtual;
	}

	public TipoExameDAO getTipoExameDAO() {
		return tipoExameDAO;
	}

	public void setTipoExameDAO(TipoExameDAO tipoExameDAO) {
		this.tipoExameDAO = tipoExameDAO;
	}

	public List<TipoExame> getTipoExameList() {
		return tipoExameList;
	}

	public void setTipoExameList(List<TipoExame> tipoExameList) {
		this.tipoExameList = tipoExameList;
	}

	public ConsultaDAO getConsultaDAO() {
		return consultaDAO;
	}

	public void setConsultaDAO(ConsultaDAO consultaDAO) {
		this.consultaDAO = consultaDAO;
	}

}