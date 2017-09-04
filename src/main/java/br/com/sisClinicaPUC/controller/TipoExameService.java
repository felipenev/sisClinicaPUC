package br.com.sisClinicaPUC.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import br.com.sisClinicaPUC.entidade.TipoExame;
import br.com.sisClinicaPUC.persistencia.TipoExameDAO;

@ManagedBean(name="tipoExameService", eager = true)
@ApplicationScoped
public class TipoExameService {
     
	private TipoExameDAO tipoExameDAO = new TipoExameDAO();
    private List<TipoExame> tipoExameList;
     
    @PostConstruct
    public void init() {
    	carregarTipoExameList();
    }
     
    public void carregarTipoExameList() {
		this.setTipoExameList(new ArrayList<TipoExame>());
		this.getTipoExameList().addAll(this.getTipoExameDAO().getList());
	}

	public TipoExame getTipoExamePorId(long id) {
		for (TipoExame tipoExa : this.getTipoExameList()) {
			if(tipoExa.getId().equals(id))
				return tipoExa;
		}
		return null;
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

}