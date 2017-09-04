package br.com.sisClinicaPUC.persistencia;

import br.com.sisClinicaPUC.entidade.Consulta;
   
   
	public class ConsultaDAO extends GenericDAO<Consulta, Long>{
		
		private static final long serialVersionUID = 1L;

		public ConsultaDAO() {
			super(Consulta.class);
		}
	  
	    public boolean inserir(Consulta consulta) {
	    	try {
	    		this.salvar(consulta);
	            return true;
	    	} catch (Exception e) {
	    		this.tratarMensagemErro(null, e.getMessage());
	            return false;
	    	}
		}
	    
	    public boolean alterar(Consulta consulta) {
	    	try {
	    		this.atualizar(consulta);
	    		return true;
	    	} catch (Exception e) {
	    		this.tratarMensagemErro(null, e.getMessage());
	    		return false;
	    	}
	    }
	    
	    public boolean excluir(Consulta consulta) {
	    	try {
	    		this.remover(consulta.getId());
	    		return true;
	    	} catch (Exception e) {
	    		this.tratarMensagemErro(null, e.getMessage());
	    		return false;
	    	}
	    }
	    
	}