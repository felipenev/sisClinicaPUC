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
import br.com.sisClinicaPUC.entidade.Medicamento;
import br.com.sisClinicaPUC.entidade.Paciente;
import br.com.sisClinicaPUC.entidade.ReceitaMedica;
import br.com.sisClinicaPUC.persistencia.ConsultaDAO;
import br.com.sisClinicaPUC.persistencia.MedicamentoDAO;
import br.com.sisClinicaPUC.persistencia.PacienteDAO;
import br.com.sisClinicaPUC.persistencia.ReceitaMedicaDAO;
import br.com.sisClinicaPUC.util.Util;
import br.com.sisClinicaPUC.vo.OperacaoEnum;
import br.com.sisClinicaPUC.vo.TipoPesquisaHistoricoEnum;
   
@Named
@ViewScoped
public class ManterReceitaMedicaManagedBean extends AbstractMangedBean<ReceitaMedica> implements Serializable{
   
	private static final long serialVersionUID = 1L;
	
	private ConsultaDAO consultaDAO = new ConsultaDAO();
	private ReceitaMedicaDAO receitaMedicaDAO = new ReceitaMedicaDAO();
	private PacienteDAO pacienteDAO = new PacienteDAO();
	private MedicamentoDAO medicamentoDAO = new MedicamentoDAO();
    private ReceitaMedica receitaMedica = new ReceitaMedica();
    private List<ReceitaMedica> receitaMedicaList = new ArrayList<ReceitaMedica>();
    private List<Paciente> pacienteList = new ArrayList<Paciente>();
    private List<Medicamento> medicamentoList = new ArrayList<Medicamento>();
    
    private Date dataAtual = new Date();
    
    public ManterReceitaMedicaManagedBean() {}

    //TODO: Colocar botao de exportacao de documento.
    
    @PostConstruct
	public void init() {
    	System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!! inicializei o ManagedBean de ReceitaMedica !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
    	this.setReceitaMedica(new ReceitaMedica(this.getMedicoSessao()));
		carregarReceitaMedicaPorMedicoList();
		carregarPacientePorMedicoList();
		carregarMedicamentoAtivoList();
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
//    		boolean inclusao = this.getReceitaMedicaDAO().inserir(this.getReceitaMedica());
    		boolean inclusao = this.getReceitaMedicaDAO().alterar(this.getReceitaMedica());
    		if (inclusao) {
    			
    			montarHistorico(OperacaoEnum.INCLUIR, this.getReceitaMedica());
    			
    			init();
    			this.tratarMensagemSucesso(null);
    		}
    	}
	}
	
	@Override
	public void alterar(ReceitaMedica receitaMedica) {
		if(validarCampos()) {
			boolean alteracao = this.getReceitaMedicaDAO().alterar(receitaMedica);
			if (alteracao) {
				
				montarHistorico(OperacaoEnum.ALTERAR, receitaMedica);
				
				init();
				this.tratarMensagemSucesso(null);
			}
		}
		
	}

	@Override
	public void excluir() {}
	
	@Override
	public void excluir(ReceitaMedica receitaMedica) {}

	public void carregarAlteracao(ReceitaMedica receitaMedicaAlterar) {
		
		this.setReceitaMedica(new ReceitaMedica(this.getMedicoSessao()));
		carregarReceitaMedicaPorMedicoList();
		ReceitaMedica recAlterar = Util.cloneSerializable(receitaMedicaAlterar);
		this.setReceitaMedica(recAlterar);
//		this.getReceitaMedicaList().remove(receitaMedicaAlterar);
	}
	
	 /**
     * Monta o historico para inclusao
     * 
     * @param operacao
     * @param consulta
     */
	private void montarHistorico(OperacaoEnum operacao, ReceitaMedica receitaMedica) {
		Historico historico = new Historico();
		historico.setOperacao(operacao);
		historico.setReceitaMedica(receitaMedica);
		historico.setTipoPesquisaHistoricoEnum(TipoPesquisaHistoricoEnum.MEDICAMENTOS_PRESCRITOS);
		
		//Gravando historico
		this.gravarHistorico(historico);
	}
	
	/**
	 * Valida os campos obrigatorios
	 */
	public boolean validarCampos() {
		boolean valid = true;

		if(!Util.isObjectNotNull(this.getReceitaMedica().getMedico())) {
			this.tratarMensagemErro(null, "MSG999");
			valid = false;
		}
		if(!Util.isObjectNotNull(this.getReceitaMedica().getPaciente())) {
			this.tratarMensagemErro(null);
			valid = false;
		}
		if(!Util.isStringNotBlankOrNotNull(this.getReceitaMedica().getDescricaoReceita())) {
			this.tratarMensagemErro(null);
			valid = false;
		}
		if(!Util.isColecaoNaoVazia(this.getReceitaMedica().getMedicamentoList())) {
			this.tratarMensagemErro(null);
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
	
	private void carregarPacientePorMedicoList() {
		this.setPacienteList(new ArrayList<>());
		this.getPacienteList().addAll(this.getConsultaDAO().getPacientePorMedicoDataConsultaList(this.getMedicoSessao()));
	}
	
	public void carregarMedicamentoAtivoList() {
		this.setMedicamentoList(new ArrayList<Medicamento>());
		this.getMedicamentoList().addAll(this.getMedicamentoDAO().getMedicamentoAtivoList());
	}

	

	public void gerarSolicitacaoPDFExame(ReceitaMedica receitaMedica) {
		
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
		    
		    Paragraph tituloDocumento = new Paragraph(new Phrase(lineSpacingTitulo,"Receita Médica", FontFactory.getFont(FontFactory.HELVETICA_BOLD, fntSizeTitulo)));
		    tituloDocumento.setAlignment(Element.ALIGN_CENTER);
		    
		    Paragraph dataEmissaoReceitaDocumento = new Paragraph("Data Emissão: " + Util.formatoData.format(receitaMedica.getDataEmissao()));
		    dataEmissaoReceitaDocumento.setIndentationLeft(20);
		    dataEmissaoReceitaDocumento.setIndentationRight(20);
		    
		    Paragraph pacienteDocumento = new Paragraph("Paciente: " + receitaMedica.getPaciente().getNome());
		    pacienteDocumento.setIndentationLeft(20);
		    pacienteDocumento.setIndentationRight(20);
		    
		    Paragraph examesSolicitadosDocumento = new Paragraph("Medicamento: " + receitaMedica.getMedicamentosPrescritos());
		    examesSolicitadosDocumento.setIndentationLeft(20);
		    examesSolicitadosDocumento.setIndentationRight(20);
		    
		    Paragraph prescricaoDocumento = new Paragraph("Prescrição: " + receitaMedica.getDescricaoReceita());
		    prescricaoDocumento.setIndentationLeft(20);
		    prescricaoDocumento.setIndentationRight(20);
		    
		    Paragraph linhaAssinaturaMedico = new Paragraph(new Phrase("___________________________________________________________"));
		    linhaAssinaturaMedico.setAlignment(Element.ALIGN_CENTER);
		    Paragraph assinaturaMedico = new Paragraph(new Phrase(lineSpacingAssinatura,receitaMedica.getMedico().getNome(), FontFactory.getFont(FontFactory.HELVETICA_BOLD, fntSizeAssinatura)));
		    assinaturaMedico.setAlignment(Element.ALIGN_CENTER);
		    
		    //Documento
		    document.add(tituloDocumento);
		    document.add(Chunk.NEWLINE);
		    document.add(Chunk.NEWLINE);
		    document.add(Chunk.NEWLINE);
		    document.add(dataEmissaoReceitaDocumento);
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
			document.add(prescricaoDocumento);
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
			this.tratarMensagemErro(null, e.getMessage());
		}finally {
			document.close();
		}
		
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

	public List<Medicamento> getMedicamentoList() {
		return medicamentoList;
	}

	public void setMedicamentoList(List<Medicamento> medicamentoList) {
		this.medicamentoList = medicamentoList;
	}

	public MedicamentoDAO getMedicamentoDAO() {
		return medicamentoDAO;
	}

	public void setMedicamentoDAO(MedicamentoDAO medicamentoDAO) {
		this.medicamentoDAO = medicamentoDAO;
	}

	public ConsultaDAO getConsultaDAO() {
		return consultaDAO;
	}

	public void setConsultaDAO(ConsultaDAO consultaDAO) {
		this.consultaDAO = consultaDAO;
	}

}