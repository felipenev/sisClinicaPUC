package br.com.sisClinicaPUC.controller;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import br.com.sisClinicaPUC.entidade.Paciente;
import br.com.sisClinicaPUC.persistencia.PacienteDAO;

@ManagedBean(name="manterPacienteService", eager = true)
@ApplicationScoped
public class ManterPacienteService {
     
	private PacienteDAO pacienteDAO = new PacienteDAO();
     
	public Paciente getMedicamentosDisponivelPorId(long id) {
		Paciente paciente = this.getPacienteDAO().encontrar(id);
		return paciente;
	}

	public PacienteDAO getPacienteDAO() {
		return pacienteDAO;
	}

	public void setPacienteDAO(PacienteDAO pacienteDAO) {
		this.pacienteDAO = pacienteDAO;
	}
    
}