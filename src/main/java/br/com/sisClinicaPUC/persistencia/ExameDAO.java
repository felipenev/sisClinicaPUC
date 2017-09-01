package br.com.sisClinicaPUC.persistencia;

import br.com.sisClinicaPUC.entidade.Exame;
   
   
	public class ExameDAO extends GenericDAO<Exame, Long>{
		
		private static final long serialVersionUID = 1L;

		public ExameDAO() {
			super(Exame.class);
		}
	  
	    public boolean inserir(Exame exame) {
	    	try {
	    		this.salvar(exame);
	            return true;
	    	} catch (Exception e) {
	    		this.tratarMensagemErro("formPrincipal:growlMsgm", e.getMessage(), "");
	            return false;
	    	}
		}
	    
	    public boolean alterar(Exame exame) {
	    	try {
	    		this.atualizar(exame);
	    		return true;
	    	} catch (Exception e) {
	    		this.tratarMensagemErro("formPrincipal:growlMsgm", e.getMessage(), "");
	    		return false;
	    	}
	    }
	    
	    public boolean excluir(Exame exame) {
	    	try {
	    		this.remover(exame.getId());
	    		return true;
	    	} catch (Exception e) {
	    		this.tratarMensagemErro("formPrincipal:growlMsgm", e.getMessage(), "");
	    		return false;
	    	}
	    }
	    
	}