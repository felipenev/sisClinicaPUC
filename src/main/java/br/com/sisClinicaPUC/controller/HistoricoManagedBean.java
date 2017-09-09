package br.com.sisClinicaPUC.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.com.sisClinicaPUC.entidade.Historico;
import br.com.sisClinicaPUC.persistencia.HistoricoDAO;
import br.com.sisClinicaPUC.vo.TipoPesquisaHistoricoEnum;
   
@Named
@ViewScoped
public class HistoricoManagedBean extends AbstractMangedBean<Historico> implements Serializable{
   
	private static final long serialVersionUID = 1L;

	private HistoricoDAO historicoDAO = new HistoricoDAO();
    private Historico historico = new Historico();
    
    private List<Historico> historicoList = new ArrayList<Historico>();
    
	private TipoPesquisaHistoricoEnum tipoPesquisaHistoricoSelecionado;
	
    public HistoricoManagedBean() {}

    @PostConstruct
	public void init() {
    	System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!! inicializei o ManagedBean de Historico !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
	}
    
    @Override
    public void inserir() {}
	
	@Override
	public void alterar(Historico historico) {}

	@Override
	public void excluir() {}
	
	@Override
	public void excluir(Historico historico) {}

	public void inicializarHistorico() {
		System.out.println("inicializa historico");
		this.setHistoricoList(new ArrayList<Historico>());
		pesquisarHistorico();
	}

	//TODO: MARCAR USUARIO QUE REALIZOU AS ALTERACOES. INCLUIR NOS METODOS GRAVARHISTORICO
	public List<Historico> pesquisarHistorico() {
		this.getHistoricoList().addAll(this.getHistoricoDAO().getHistoricoPorTipo(tipoPesquisaHistoricoSelecionado));
		return this.getHistoricoList();
	}
		
	public boolean isMedicamentoPrescrito() {
		return TipoPesquisaHistoricoEnum.MEDICAMENTOS_PRESCRITOS.equals(tipoPesquisaHistoricoSelecionado);
	}
	
	public boolean isExameSolicitado() {
		return TipoPesquisaHistoricoEnum.EXAMES_SOLICITADOS.equals(tipoPesquisaHistoricoSelecionado);
	}
	
	public boolean isResultadoObtido() {
		return TipoPesquisaHistoricoEnum.RESULTADOS_OBTIDOS.equals(tipoPesquisaHistoricoSelecionado);
	}
	
	public boolean isConsultar() {
		return TipoPesquisaHistoricoEnum.CONSULTAS.equals(tipoPesquisaHistoricoSelecionado);
	}
	
	public TipoPesquisaHistoricoEnum[] getTipoPesquisaHistoricoValues() {
		return TipoPesquisaHistoricoEnum.values();
	}

	public HistoricoDAO getHistoricoDAO() {
		return historicoDAO;
	}

	public void setHistoricoDAO(HistoricoDAO historicoDAO) {
		this.historicoDAO = historicoDAO;
	}

	public TipoPesquisaHistoricoEnum getTipoPesquisaHistoricoSelecionado() {
		return tipoPesquisaHistoricoSelecionado;
	}

	public void setTipoPesquisaHistoricoSelecionado(TipoPesquisaHistoricoEnum tipoPesquisaHistoricoSelecionado) {
		this.tipoPesquisaHistoricoSelecionado = tipoPesquisaHistoricoSelecionado;
	}

	public Historico getHistorico() {
		return historico;
	}

	public void setHistorico(Historico historico) {
		this.historico = historico;
	}

	public List<Historico> getHistoricoList() {
		return historicoList;
	}

	public void setHistoricoList(List<Historico> historicoList) {
		this.historicoList = historicoList;
	}

}