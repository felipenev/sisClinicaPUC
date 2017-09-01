package br.com.sisClinicaPUC.persistencia;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import br.com.sisClinicaPUC.entidade.ExameSolicitado;
import br.com.sisClinicaPUC.entidade.TipoExame;
   
   
	public class ExameSolicitadoDAO extends GenericDAO<ExameSolicitado, Long>{
		
		private static final long serialVersionUID = 1L;
		
		public ExameSolicitadoDAO() {
			super(ExameSolicitado.class);
		}
		
	    public boolean inserir(ExameSolicitado exameSolicitado) {
	    	try {
	    		this.salvar(exameSolicitado);
	            return true;
	    	} catch (Exception e) {
	    		this.tratarMensagemErro("formPrincipal:growlMsgm", e.getMessage(), "");
	            return false;
	    	}
		}
	    
	    public boolean alterar(ExameSolicitado exameSolicitado) {
	    	try {
	    		this.atualizar(exameSolicitado);
	    		return true;
	    	} catch (Exception e) {
	    		this.tratarMensagemErro("formPrincipal:growlMsgm", e.getMessage(), "");
	    		return false;
	    	}
	    }
	    
	    public boolean excluir(ExameSolicitado exameSolicitado) {
	    	try {
	    		this.remover(exameSolicitado.getId());
	    		return true;
	    	} catch (Exception e) {
	    		this.tratarMensagemErro("formPrincipal:growlMsgm", e.getMessage(), "");
	    		return false;
	    	}
	    }

	    
	    /**
	     * Recupera a lista de objetos dos exames solicitados disponiveis
	     * @return
	     */
		public List<ExameSolicitado> getExameSolicitadoDisponivelList(TipoExameDAO tipoExameDAO) {
			try {
				List<ExameSolicitado> exameSolicitadoList = new ArrayList<ExameSolicitado>();
	    		List<TipoExame> tipoExameList = tipoExameDAO.getList();
	    		for (TipoExame tipoExame : tipoExameList) {
					exameSolicitadoList.add(new ExameSolicitado(tipoExame));
				}
	    		
	    		return exameSolicitadoList;
	    	} catch (Exception e) {
	    		this.tratarMensagemErro("formPrincipal:growlMsgm", e.getMessage(), "");
	    		return new ArrayList<ExameSolicitado>();
	    	}
		}

	}