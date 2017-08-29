package br.com.sisClinicaPUC.persistencia;

import java.util.ArrayList;
import java.util.List;

import br.com.sisClinicaPUC.entidade.Medico;
import br.com.sisClinicaPUC.entidade.ReceitaMedica;
   
   
	public class ReceitaMedicaDAO extends GenericDAO<ReceitaMedica, Long>{
		
		private static final long serialVersionUID = 1L;

		public ReceitaMedicaDAO() {
			super(ReceitaMedica.class);
		}
	  
	    public boolean inserir(ReceitaMedica ReceitaMedica) {
	    	try {
	    		this.salvar(ReceitaMedica);
	            return true;
	    	} catch (Exception e) {
	    		this.tratarMensagemErro(null, e.getMessage(), "");
	            return false;
	    	}
		}
	    
	    public boolean alterar(ReceitaMedica ReceitaMedica) {
	    	try {
	    		this.atualizar(ReceitaMedica);
	    		return true;
	    	} catch (Exception e) {
	    		this.tratarMensagemErro(null, e.getMessage(), "");
	    		return false;
	    	}
	    }
	    
	    public boolean excluir(ReceitaMedica ReceitaMedica) {
	    	try {
	    		this.remover(ReceitaMedica.getId());
	    		return true;
	    	} catch (Exception e) {
	    		this.tratarMensagemErro(null, e.getMessage(), "");
	    		return false;
	    	}
	    }
	    
	    /**
	     * Recupera as receitas por medico
	     * 
	     * @return
	     */
	    public List<ReceitaMedica> getReceitasPorMedicoList(Medico medico) {
	    	try {
	    		List<ReceitaMedica> receitaMedicaList = getEntityManager().createNamedQuery(ReceitaMedica.RECEITA_POR_MEDICO, ReceitaMedica.class).setParameter("idMedico", medico.getId()).getResultList();
	    		return receitaMedicaList;
	    	} catch (Exception e) {
	    		this.tratarMensagemErro(null, e.getMessage(), "");
	    		return new ArrayList<ReceitaMedica>();
	    	}
	    }

	}