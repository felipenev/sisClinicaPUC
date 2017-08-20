package br.com.sisClinicaPUC.view;

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
public class MedicamentoManagedBean extends AbstractMangedBean implements Serializable{
   
	private static final long serialVersionUID = 1L;
	
//	@ManagedProperty(name="em", value="#{requestScope.em}")
//	private EntityManager em;
	
	private MedicamentoDAO medicamentoDAO = new MedicamentoDAO();
    public Medicamento medicamento = new Medicamento();
    private List<Medicamento> medicamentoList = new ArrayList<Medicamento>();
    
    public MedicamentoManagedBean() {
    	
	}

    @PostConstruct
	public void init() {
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!Inicializei o MedicamentoManagedBean!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		medicamento = new Medicamento();
		carregarMedicamentoList();
	}
    
	public void inserir() {

    	if(validarCampos()) {
    		boolean inclusao = this.getMedicamentoDAO().inserir(medicamento);
    		if (inclusao) {
    			medicamento = new Medicamento();
    			
    			carregarMedicamentoList();
    		}
    	}
	}

	/**
	 * Valida os campos obrigatorios
	 */
	private boolean validarCampos() {
		boolean valid = true;
		
		if(!Util.isStringBlankOrNull(this.getMedicamento().getNomeFabricante())) {
			this.tratarMensagemErro("formPrincipal:nomeFabricante");
			valid = false;
		}
		if(!Util.isStringBlankOrNull(this.getMedicamento().getNomeGenerico())) {
			this.tratarMensagemErro("formPrincipal:nomeGenerico");
			valid = false;
		}
		if(!Util.isStringBlankOrNull(this.getMedicamento().getNomeDeFabrica())) {
			this.tratarMensagemErro("formPrincipal:nomeDeFrabrica");
			valid = false;
		}
		
		return valid;
	}

	private void carregarMedicamentoList() {
		this.setMedicamentoList(new ArrayList<Medicamento>());
    	this.getMedicamentoList().addAll(this.getMedicamentoDAO().getList());
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
        
}