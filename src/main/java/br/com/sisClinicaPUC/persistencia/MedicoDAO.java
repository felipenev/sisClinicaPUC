package br.com.sisClinicaPUC.persistencia;

import java.util.ArrayList;
import java.util.List;

import br.com.sisClinicaPUC.entidade.Medico;
import br.com.sisClinicaPUC.vo.SituacaoEnum;
   
   
	public class MedicoDAO extends GenericDAO<Medico, Long>{
		
		private static final long serialVersionUID = 1L;

		public MedicoDAO() {
			super(Medico.class);
		}
	  
	    public boolean inserir(Medico medico) {
	    	try {
	    		this.salvar(medico);
	            return true;
	    	} catch (Exception e) {
	    		this.tratarMensagemErro(null, e.getMessage());
	            return false;
	    	}
		}
	    
	    public boolean alterar(Medico medico) {
	    	try {
	    		this.atualizar(medico);
	    		return true;
	    	} catch (Exception e) {
	    		this.tratarMensagemErro(null, e.getMessage());
	    		return false;
	    	}
	    }
	    
	    public boolean excluir(Medico medico) {
	    	try {
	    		this.remover(medico.getId());
	    		return true;
	    	} catch (Exception e) {
	    		this.tratarMensagemErro(null, e.getMessage());
	    		return false;
	    	}
	    }
	    
	    /**
	     * Recupera os medicamentos na situacao ativo
	     * 
	     * @return
	     */
	    public List<Medico> getMedicoAtivoList() {
	    	try {
	    		List<Medico> medicamentoList = getEntityManager().createNamedQuery(Medico.MEDICO_POR_SITUACAO, Medico.class).setParameter("situacao", SituacaoEnum.ATIVO.getCodigo()).getResultList();
	    		return medicamentoList;
	    	} catch (Exception e) {
	    		this.tratarMensagemErro(null, e.getMessage());
	    		return new ArrayList<Medico>();
	    	}
	    }

	}