package br.com.sisClinicaPUC.persistencia;

import br.com.sisClinicaPUC.entidade.TipoExame;
   
   
	public class TipoExameDAO extends GenericDAO<TipoExame, Long>{
		
		private static final long serialVersionUID = 1L;

		public TipoExameDAO() {
			super(TipoExame.class);
		}
	  
	    public boolean inserir(TipoExame tipoExame) {
	    	try {
	    		this.salvar(tipoExame);
	            return true;
	    	} catch (Exception e) {
	    		this.tratarMensagemErro(null, e.getMessage(), "");
	            return false;
	    	}
		}
	    
	    public boolean alterar(TipoExame tipoExame) {
	    	try {
	    		this.atualizar(tipoExame);
	    		return true;
	    	} catch (Exception e) {
	    		this.tratarMensagemErro(null, e.getMessage(), "");
	    		return false;
	    	}
	    }
	    
	    public boolean excluir(TipoExame tipoExame) {
	    	try {
	    		this.remover(tipoExame.getId());
	    		return true;
	    	} catch (Exception e) {
	    		this.tratarMensagemErro(null, e.getMessage(), "");
	    		return false;
	    	}
	    }
	    
	}