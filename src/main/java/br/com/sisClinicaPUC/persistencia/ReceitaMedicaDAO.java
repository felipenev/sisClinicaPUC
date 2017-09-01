package br.com.sisClinicaPUC.persistencia;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.com.sisClinicaPUC.entidade.Medico;
import br.com.sisClinicaPUC.entidade.ReceitaMedica;
   
   
	public class ReceitaMedicaDAO extends GenericDAO<ReceitaMedica, Long>{
		
		private static final long serialVersionUID = 1L;

		public ReceitaMedicaDAO() {
			super(ReceitaMedica.class);
		}
	  
	    public boolean inserir(ReceitaMedica receitaMedica) {
	    	try {
	    		this.salvar(receitaMedica);
	            return true;
	    	} catch (Exception e) {
	    		this.tratarMensagemErro("formPrincipal:growlMsgm", e.getMessage(), "");
	            return false;
	    	}
		}
	    
	    public boolean alterar(ReceitaMedica receitaMedica) {
	    	try {
	    		this.atualizar(receitaMedica);
	    		return true;
	    	} catch (Exception e) {
	    		this.tratarMensagemErro("formPrincipal:growlMsgm", e.getMessage(), "");
	    		return false;
	    	}
	    }
	    
	    public boolean excluir(ReceitaMedica receitaMedica) {
	    	try {
	    		this.remover(receitaMedica.getId());
	    		return true;
	    	} catch (Exception e) {
	    		this.tratarMensagemErro("formPrincipal:growlMsgm", e.getMessage(), "");
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
	    		Set<ReceitaMedica> receitaSet = new HashSet<ReceitaMedica>(receitaMedicaList);
	    		List<ReceitaMedica> retorno = new ArrayList<ReceitaMedica>(receitaSet);
	    		
	    		return retorno;
	    	} catch (Exception e) {
	    		this.tratarMensagemErro("formPrincipal:growlMsgm", e.getMessage(), "");
	    		return new ArrayList<ReceitaMedica>();
	    	}
	    }

	}