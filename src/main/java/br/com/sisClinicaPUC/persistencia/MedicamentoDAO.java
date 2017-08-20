package br.com.sisClinicaPUC.persistencia;

import br.com.sisClinicaPUC.entidade.Medicamento;
   
   
	public class MedicamentoDAO extends GenericDao<Medicamento, Long>{
		
		public MedicamentoDAO() {
			super(Medicamento.class);
		}
	  
	    public boolean inserir(Medicamento medicamento) {
	    	try {
	    		this.salvar(medicamento);
	            return true;
	    	} catch (Exception e) {
	    		//TODO: Tratar excecao
	    		e.printStackTrace();
	            return false;
	    	}
		}
        
	}