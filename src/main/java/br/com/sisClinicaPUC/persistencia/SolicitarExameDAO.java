package br.com.sisClinicaPUC.persistencia;

import br.com.sisClinicaPUC.entidade.SolicitarExame;
   
   
	public class SolicitarExameDAO extends GenericDAO<SolicitarExame, Long>{
		
		private static final long serialVersionUID = 1L;

		public SolicitarExameDAO() {
			super(SolicitarExame.class);
		}
	  
	    public boolean inserir(SolicitarExame solicitarExame) {
	    	try {
	    		this.salvar(solicitarExame);
	            return true;
	    	} catch (Exception e) {
	    		this.tratarMensagemErro("formPrincipal:growlMsgm", e.getMessage(), "");
	            return false;
	    	}
		}
	    
	    public boolean alterar(SolicitarExame solicitarExame) {
	    	try {
	    		this.atualizar(solicitarExame);
	    		return true;
	    	} catch (Exception e) {
	    		this.tratarMensagemErro("formPrincipal:growlMsgm", e.getMessage(), "");
	    		return false;
	    	}
	    }
	    
	    public boolean excluir(SolicitarExame solicitarExame) {
	    	try {
	    		this.remover(solicitarExame.getId());
	    		return true;
	    	} catch (Exception e) {
	    		this.tratarMensagemErro("formPrincipal:growlMsgm", e.getMessage(), "");
	    		return false;
	    	}
	    }
	    
	}