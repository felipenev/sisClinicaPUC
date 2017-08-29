package br.com.sisClinicaPUC.persistencia;

import java.util.ArrayList;
import java.util.List;

import br.com.sisClinicaPUC.entidade.Recepcionista;
import br.com.sisClinicaPUC.vo.SituacaoEnum;
   
   
	public class RecepcionistaDAO extends GenericDAO<Recepcionista, Long>{
		
		private static final long serialVersionUID = 1L;

		public RecepcionistaDAO() {
			super(Recepcionista.class);
		}
	  
	    public boolean inserir(Recepcionista recepcionista) {
	    	try {
	    		this.salvar(recepcionista);
	            return true;
	    	} catch (Exception e) {
	    		this.tratarMensagemErro(null, e.getMessage());
	            return false;
	    	}
		}
	    
	    public boolean alterar(Recepcionista recepcionista) {
	    	try {
	    		this.atualizar(recepcionista);
	    		return true;
	    	} catch (Exception e) {
	    		this.tratarMensagemErro(null, e.getMessage());
	    		return false;
	    	}
	    }
	    
	    public boolean excluir(Recepcionista recepcionista) {
	    	try {
	    		this.remover(recepcionista.getId());
	    		return true;
	    	} catch (Exception e) {
	    		this.tratarMensagemErro(null, e.getMessage());
	    		return false;
	    	}
	    }
	    
	    /**
	     * Recupera os recepcionistas na situacao ativo
	     * 
	     * @return
	     */
	    public List<Recepcionista> getRecepcionistaAtivoList() {
	    	try {
	    		List<Recepcionista> recepcionistaList = getEntityManager().createNamedQuery(Recepcionista.RECEPCIONISTA_POR_SITUACAO, Recepcionista.class).setParameter("situacao", SituacaoEnum.ATIVO.getCodigo()).getResultList();
	    		return recepcionistaList;
	    	} catch (Exception e) {
	    		this.tratarMensagemErro(null, e.getMessage());
	    		return new ArrayList<Recepcionista>();
	    	}
	    }

	}