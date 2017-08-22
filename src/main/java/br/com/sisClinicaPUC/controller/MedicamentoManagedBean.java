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
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!Inicializei o MedicamentoManagedBean!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		medicamento = new Medicamento();
		medicamentoExclusao = new Medicamento();
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
    		boolean inclusao = this.getMedicamentoDAO().inserir(medicamento);
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
			boolean alteracao = this.getMedicamentoDAO().alterar(medicamento);
			if (alteracao) {
				medicamento = new Medicamento();
				carregarMedicamentoAtivoList();
				this.tratarMensagemSucesso("formPrincipal:growlMsgm");
			}
		}
		
	}

	@Override
	public void excluir() {
		//Exclusao logica
		boolean exclusao = this.getMedicamentoDAO().excluir(this.getMedicamentoExclusao());
		if(exclusao) {
			medicamento = new Medicamento();
			medicamentoExclusao = new Medicamento();
			carregarMedicamentoAtivoList();
			this.tratarMensagemSucesso("formPrincipal:growlMsgm");
		}
		
	}
	
	@Override
	public void excluir(Medicamento med) {
		//Exclusao logica
		boolean exclusao = this.getMedicamentoDAO().excluir(med);
		if(exclusao) {
			medicamento = new Medicamento();
			medicamentoExclusao = new Medicamento();
			carregarMedicamentoAtivoList();
			this.tratarMensagemSucesso("formPrincipal:growlMsgm");
		}
		
	}

	public void carregarAlteracao(Medicamento medicamentoAlterar) {
		this.setMedicamento(medicamentoAlterar);
	}
	
	/**
	 * Valida os campos obrigatorios
	 */
	public boolean validarCampos() {
		boolean valid = true;
		
		if(!Util.isStringNotBlankOrNotNull(this.getMedicamento().getNomeFabricante())) {
			this.tratarMensagemErro("formPrincipal:nomeFabricante");
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