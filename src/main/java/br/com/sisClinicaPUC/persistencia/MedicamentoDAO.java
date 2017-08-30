package br.com.sisClinicaPUC.persistencia;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import br.com.sisClinicaPUC.entidade.Medicamento;
import br.com.sisClinicaPUC.vo.SituacaoEnum;
   
   
	public class MedicamentoDAO extends GenericDAO<Medicamento, Long>{
		
		private static final long serialVersionUID = 1L;

		public MedicamentoDAO() {
			super(Medicamento.class);
		}
	  
	    public boolean inserir(Medicamento medicamento) {
	    	try {
	    		this.salvar(medicamento);
	            return true;
	    	} catch (Exception e) {
	    		this.tratarMensagemErro(null, e.getMessage(), "");
	            return false;
	    	}
		}
	    
	    public boolean alterar(Medicamento medicamento) {
	    	try {
	    		this.atualizar(medicamento);
	    		return true;
	    	} catch (Exception e) {
	    		this.tratarMensagemErro(null, e.getMessage(), "");
	    		return false;
	    	}
	    }
	    
	    public boolean excluir(Medicamento medicamento) {
	    	try {
	    		this.remover(medicamento.getId());
	    		return true;
	    	} catch (Exception e) {
	    		this.tratarMensagemErro(null, e.getMessage(), "");
	    		return false;
	    	}
	    }
	    
	    /**
	     * Recupera os medicamentos na situacao ativo
	     * 
	     * @return
	     */
	    public List<Medicamento> getMedicamentoAtivoList() {
	    	try {
	    		List<Medicamento> medicamentoList = getEntityManager().createNamedQuery(Medicamento.MEDICAMENTO_POR_SITUACAO, Medicamento.class).setParameter("situacao", SituacaoEnum.ATIVO.getCodigo()).getResultList();
	    		return medicamentoList;
	    	} catch (Exception e) {
	    		this.tratarMensagemErro(null, e.getMessage(), "");
	    		return new ArrayList<Medicamento>();
	    	}
	    }

		public List<Medicamento> getMedicamentoPorIdList(Set<Medicamento> medicamentoList) {
			try {
				List<Long> idList = new ArrayList<Long>();
				idList = recuperaIds(medicamentoList);
	    		List<Medicamento> medicamentoPorIdList = getEntityManager().createNamedQuery(Medicamento.MEDICAMENTO_POR_ID, Medicamento.class).setParameter("id", idList).getResultList();
	    		return medicamentoPorIdList;
	    	} catch (Exception e) {
	    		this.tratarMensagemErro(null, e.getMessage(), "");
	    		return new ArrayList<Medicamento>();
	    	}
		}

		private List<Long> recuperaIds(Set<Medicamento> medicamentoList) {
			List<Long> list = new ArrayList<Long>();
			for (Medicamento m : medicamentoList) {
				list.add(m.getId());
			}
			return list;
		}

	}