package br.com.sisClinicaPUC.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import br.com.sisClinicaPUC.entidade.Medicamento;
import br.com.sisClinicaPUC.persistencia.MedicamentoDAO;

@ManagedBean(name="manterMedicamentoService", eager = true)
@ApplicationScoped
public class ManterMedicamentoService {
     
	private MedicamentoDAO medicamentoDAO = new MedicamentoDAO();
    private List<Medicamento> medicamentoDisponivelList;
     
    @PostConstruct
    public void init() {
    	carregarMedicamentoAtivoList();
    }
     
    public void carregarMedicamentoAtivoList() {
		this.setMedicamentoDisponivelList(new ArrayList<Medicamento>());
		this.getMedicamentoDisponivelList().addAll(this.getMedicamentoDAO().getMedicamentoAtivoList());
	}

	public List<Medicamento> getMedicamentoDisponivelList() {
		return medicamentoDisponivelList;
	}

	public void setMedicamentoDisponivelList(List<Medicamento> medicamentoDisponivelList) {
		this.medicamentoDisponivelList = medicamentoDisponivelList;
	}

	public MedicamentoDAO getMedicamentoDAO() {
		return medicamentoDAO;
	}

	public void setMedicamentoDAO(MedicamentoDAO medicamentoDAO) {
		this.medicamentoDAO = medicamentoDAO;
	}

	public Medicamento getMedicamentosDisponivelPorId(long id) {
		for (Medicamento medicamento : this.getMedicamentoDisponivelList()) {
			if(medicamento.getId().equals(id))
				return medicamento;
		}
		return null;
	}
    
}