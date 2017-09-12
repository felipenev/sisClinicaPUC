package br.com.sisClinicaPUC.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfWriter;

import br.com.sisClinicaPUC.entidade.Exame;
import br.com.sisClinicaPUC.entidade.Historico;
import br.com.sisClinicaPUC.entidade.Paciente;
import br.com.sisClinicaPUC.entidade.TipoExame;
import br.com.sisClinicaPUC.persistencia.ConsultaDAO;
import br.com.sisClinicaPUC.persistencia.ExameDAO;
import br.com.sisClinicaPUC.persistencia.PacienteDAO;
import br.com.sisClinicaPUC.persistencia.TipoExameDAO;
import br.com.sisClinicaPUC.util.Util;
import br.com.sisClinicaPUC.vo.OperacaoEnum;
import br.com.sisClinicaPUC.vo.TipoPesquisaHistoricoEnum;
   
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
    			
    			montarHistorico(OperacaoEnum.INCLUIR, this.getExame());
    			
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
     * @param exame
     */
	private void montarHistorico(OperacaoEnum operacao, Exame exame) {
		Historico historico = new Historico();
		historico.setOperacao(operacao);
		historico.setExame(exame);
		historico.setTipoPesquisaHistoricoEnum(TipoPesquisaHistoricoEnum.EXAMES_SOLICITADOS);
		
		//Gravando historico
		this.gravarHistorico(historico);
	}

//	public void carregarAlteracao(Exame exameAlterar) {
//		this.setExame(new Exame(this.getMedicoSessao()));
//		carregarSolicitacoesExamePorMedicoList();
//		Exame exaAlterar = Util.cloneSerializable(exameAlterar);
//		this.setExame(exaAlterar);
//	}
	
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
		
		Document document = new Document(PageSize.A4, 0f, 0f, 0f, 0f);
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		response.setContentType("application/pdf");
		response.setHeader("Content-disposition", "inline;filename=solicitacaoExame.pdf");
		try {
			float fntSizeTitulo, lineSpacingTitulo;
		    fntSizeTitulo = 18f;
		    lineSpacingTitulo = 20f;
		    
		    float fntSizeAssinatura, lineSpacingAssinatura;
		    fntSizeAssinatura = 15f;
		    lineSpacingAssinatura = 15f;
		    
			PdfWriter.getInstance(document, response.getOutputStream());
		    document.open();
		    
		    Paragraph tituloDocumento = new Paragraph(new Phrase(lineSpacingTitulo,"Solicitação de Exames", FontFactory.getFont(FontFactory.HELVETICA_BOLD, fntSizeTitulo)));
		    tituloDocumento.setAlignment(Element.ALIGN_CENTER);
		    
		    Paragraph pacienteDocumento = new Paragraph("Paciente: " + exameRelatorio.getPaciente().getNome());
		    pacienteDocumento.setIndentationLeft(20);
		    pacienteDocumento.setIndentationRight(20);
		    
		    Paragraph examesSolicitadosDocumento = new Paragraph("Exames solicitados: " + exameRelatorio.getExamesSolicitados());
		    examesSolicitadosDocumento.setIndentationLeft(20);
		    examesSolicitadosDocumento.setIndentationRight(20);
		    
		    Paragraph linhaAssinaturaMedico = new Paragraph(new Phrase("___________________________________________________________"));
		    linhaAssinaturaMedico.setAlignment(Element.ALIGN_CENTER);
		    Paragraph assinaturaMedico = new Paragraph(new Phrase(lineSpacingAssinatura,exameRelatorio.getMedico().getNome(), FontFactory.getFont(FontFactory.HELVETICA_BOLD, fntSizeAssinatura)));
		    assinaturaMedico.setAlignment(Element.ALIGN_CENTER);
		    
		    //Documento
		    document.add(tituloDocumento);
		    document.add(Chunk.NEWLINE);
		    document.add(Chunk.NEWLINE);
		    document.add(Chunk.NEWLINE);
		    document.add(Chunk.NEWLINE);
		    document.add(Chunk.NEWLINE);
			document.add(pacienteDocumento);
			document.add(Chunk.NEWLINE);
			document.add(Chunk.NEWLINE);
			document.add(Chunk.NEWLINE);
			document.add(examesSolicitadosDocumento);
			document.add(Chunk.NEWLINE);
			document.add(Chunk.NEWLINE);
			document.add(Chunk.NEWLINE);
			document.add(Chunk.NEWLINE);
			document.add(Chunk.NEWLINE);
			document.add(Chunk.NEWLINE);
			document.add(Chunk.NEWLINE);
			document.add(Chunk.NEWLINE);
			document.add(Chunk.NEWLINE);
			document.add(Chunk.NEWLINE);
			document.add(Chunk.NEWLINE);
			document.add(Chunk.NEWLINE);
			document.add(Chunk.NEWLINE);
			document.add(Chunk.NEWLINE);
			document.add(linhaAssinaturaMedico);
			document.add(assinaturaMedico);
			
			
		    FacesContext.getCurrentInstance().responseComplete();
		    
		} catch (Exception e) {
			// TODO: handle exception
			this.tratarMensagemErro(null, e.getMessage());
		}finally {
			document.close();
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