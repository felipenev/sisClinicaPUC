package br.com.sisClinicaPUC.persistencia;

import java.util.ArrayList;
import java.util.List;

import br.com.sisClinicaPUC.entidade.AgendaMedico;
import br.com.sisClinicaPUC.vo.SituacaoEnum;
   
   
	public class AgendaMedicoDAO extends GenericDao<AgendaMedico, Long>{
		
		private static final long serialVersionUID = 1L;

		public AgendaMedicoDAO() {
			super(AgendaMedico.class);
		}
	  
	    public boolean inserir(AgendaMedico agendaMedico) {
	    	try {
	    		this.salvar(agendaMedico);
	            return true;
	    	} catch (Exception e) {
	    		this.tratarMensagemErro(null, e.getMessage());
	            return false;
	    	}
		}
	    
	    public boolean alterar(AgendaMedico agendaMedico) {
	    	try {
	    		this.atualizar(agendaMedico);
	    		return true;
	    	} catch (Exception e) {
	    		this.tratarMensagemErro(null, e.getMessage());
	    		return false;
	    	}
	    }
	    
	    public boolean excluir(AgendaMedico agendaMedico) {
	    	try {
	    		this.remover(agendaMedico.getId());
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
	    public List<AgendaMedico> getAgendaMedicamentoAtivoList() {
	    	try {
	    		List<AgendaMedico> agendaMedicamentoList = getEntityManager().createNamedQuery(AgendaMedico.AGENDA_MEDICO_POR_SITUACAO, AgendaMedico.class).setParameter("situacao", SituacaoEnum.ATIVO.getCodigo()).getResultList();
	    		return agendaMedicamentoList;
	    	} catch (Exception e) {
	    		this.tratarMensagemErro(null, e.getMessage());
	    		return new ArrayList<AgendaMedico>();
	    	}
	    }

	}