package br.com.sisClinicaPUC.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import br.com.sisClinicaPUC.persistencia.ExameSolicitadoDAO;
import br.com.sisClinicaPUC.persistencia.TipoExameDAO;

@ManagedBean(name="tipoExameService", eager = true)
@ApplicationScoped
public class TipoExameService {
     
	private TipoExameDAO tipoExameDAO = new TipoExameDAO();
    private List<ExameSolicitado> exameSolicitadoList;
     
    @PostConstruct
    public void init() {
    	carregarExameSolicitadoList();
    }
     
    public void carregarExameSolicitadoList() {
		this.setExameSolicitadoList(new ArrayList<ExameSolicitado>());
		this.getExameSolicitadoList().addAll(this.getExameSolicitadoDAO().getExameSolicitadoDisponivelList(tipoExameDAO));
	}

	public ExameSolicitado getExameSolicitadoPorId(long id) {
		for (ExameSolicitado exameSol : this.getExameSolicitadoList()) {
			if(exameSol.getId().equals(id))
				return exameSol;
		}
		return null;
	}

	public ExameSolicitadoDAO getExameSolicitadoDAO() {
		return exameSolicitadoDAO;
	}

	public void setExameSolicitadoDAO(ExameSolicitadoDAO exameSolicitadoDAO) {
		this.exameSolicitadoDAO = exameSolicitadoDAO;
	}

	public TipoExameDAO getTipoExameDAO() {
		return tipoExameDAO;
	}

	public void setTipoExameDAO(TipoExameDAO tipoExameDAO) {
		this.tipoExameDAO = tipoExameDAO;
	}

	public List<ExameSolicitado> getExameSolicitadoList() {
		return exameSolicitadoList;
	}

	public void setExameSolicitadoList(List<ExameSolicitado> exameSolicitadoList) {
		this.exameSolicitadoList = exameSolicitadoList;
	}
	
}