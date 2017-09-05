package br.com.sisClinicaPUC.controller;

  import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.com.sisClinicaPUC.entidade.Medicamento;
import br.com.sisClinicaPUC.persistencia.MedicamentoDAO;
import br.com.sisClinicaPUC.util.Util;
import br.com.sisClinicaPUC.vo.SituacaoEnum;
   
@Named
@ViewScoped
public class MedicamentoManagedBean extends AbstractMangedBean<Medicamento> implements Serializable{
   
	private static final long serialVersionUID = 1L;
	
//	@ManagedProperty(name="em", value="#{requestScope.em}")
//	private EntityManager em;
	
	private MedicamentoDAO medicamentoDAO = new MedicamentoDAO();
    private Medicamento medicamento = new Medicamento();
    private Medicamento medicamentoExclusao = new Medicamento();
    private List<Medicamento> medicamentoList = new ArrayList<Medicamento>();
    
    public MedicamentoManagedBean() {}

    @PostConstruct
	public void init() {
    	this.setMedicamento(new Medicamento());
		this.setMedicamentoExclusao(new Medicamento());
		carregarMedicamentoAtivoList();
	}
    
    public void inserirAlterar() {
    	if(Util.isValueNotBlankOrNotEmpty(this.getMedicamento().getId())) {
    		alterar(this.getMedicamento());
    	}else {
    		inserir();
    	}
    }
    
    @Override
    public void inserir() {
    	if(validarCampos()) {
    		boolean inclusao = this.getMedicamentoDAO().inserir(this.getMedicamento());
    		if (inclusao) {
    			medicamento = new Medicamento();
    			carregarMedicamentoAtivoList();
    			this.tratarMensagemSucesso("formPrincipal:growlMsgm");
    		}
    	}
	}
	
	@Override
	public void alterar(Medicamento objeto) {
		if(validarCampos()) {
			boolean alteracao = this.getMedicamentoDAO().alterar(objeto);
			if (alteracao) {
				this.setMedicamento(new Medicamento());
				carregarMedicamentoAtivoList();
				this.tratarMensagemSucesso("formPrincipal:growlMsgm");
			}
		}
		
	}

	@Override
	public void excluir() {
		//Exclusao logica
		this.getMedicamentoExclusao().setAtivoInaivo(SituacaoEnum.INATIVO);
		boolean exclusao = this.getMedicamentoDAO().alterar(this.getMedicamentoExclusao());
		if(exclusao) {
			this.setMedicamento(new Medicamento());
			this.setMedicamentoExclusao(new Medicamento());
			carregarMedicamentoAtivoList();
			this.tratarMensagemSucesso("formPrincipal:growlMsgm");
		}
		
	}
	
	@Override
	public void excluir(Medicamento med) {}

	public void carregarAlteracao(Medicamento medicamentoAlterar) {
		this.setMedicamento(medicamentoAlterar);
		this.getMedicamentoList().remove(medicamentoAlterar);
	}
	
	/**
	 * Valida os campos obrigatorios
	 */
	public boolean validarCampos() {
		boolean valid = true;
		
		if(!Util.isStringNotBlankOrNotNull(this.getMedicamento().getNomeFabricante())) {
			this.tratarMensagemErro("growlMsgm");
			valid = false;
		}
		if(!Util.isStringNotBlankOrNotNull(this.getMedicamento().getNomeGenerico())) {
			this.tratarMensagemErro("formPrincipal:nomeGenerico");
			valid = false;
		}
		if(!Util.isStringNotBlankOrNotNull(this.getMedicamento().getNomeDeFabrica())) {
			this.tratarMensagemErro("formPrincipal:nomeDeFrabrica");
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
	private void carregarMedicamentoAtivoList() {
		this.setMedicamentoList(new ArrayList<Medicamento>());
		this.getMedicamentoList().addAll(this.getMedicamentoDAO().getMedicamentoAtivoList());
	}
	
	public Medicamento getMedicamento() {
		return medicamento;
	}

	public void setMedicamento(Medicamento medicamento) {
		this.medicamento = medicamento;
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

	public Medicamento getMedicamentoExclusao() {
		return medicamentoExclusao;
	}

	public void setMedicamentoExclusao(Medicamento medicamentoExclusao) {
		this.medicamentoExclusao = medicamentoExclusao;
	}

}