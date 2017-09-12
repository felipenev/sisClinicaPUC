package br.com.sisClinicaPUC.controller;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import br.com.sisClinicaPUC.entidade.Medicamento;
import br.com.sisClinicaPUC.persistencia.MedicamentoDAO;

@ManagedBean(name="manterMedicamentoService", eager = true)
@ApplicationScoped
public class ManterMedicamentoService {
     
	private MedicamentoDAO medicamentoDAO = new MedicamentoDAO();

	public Medicamento getMedicamentosDisponivelPorId(long id) {
		Medicamento medicamento = this.getMedicamentoDAO().encontrar(id);
		return medicamento;
	}
	
	public MedicamentoDAO getMedicamentoDAO() {
		return medicamentoDAO;
	}

	public void setMedicamentoDAO(MedicamentoDAO medicamentoDAO) {
		this.medicamentoDAO = medicamentoDAO;
	}

}