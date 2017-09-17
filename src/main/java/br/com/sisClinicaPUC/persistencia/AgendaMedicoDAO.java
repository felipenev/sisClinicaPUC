package br.com.sisClinicaPUC.persistencia;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.TemporalType;

import br.com.sisClinicaPUC.entidade.AgendaMedico;
import br.com.sisClinicaPUC.entidade.Medico;
import br.com.sisClinicaPUC.util.Util;
import br.com.sisClinicaPUC.vo.SituacaoEnum;
   
   
	public class AgendaMedicoDAO extends GenericDAO<AgendaMedico, Long>{
		
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
	     * Recupera a agenda do medico.
	     * @param medico 
	     * 
	     * @return
	     */
	    public List<AgendaMedico> getAgendaMedicoList(Medico medico) {
	    	try {
	    		
	    		this.createEntityManager();
	    		
	    		List<AgendaMedico> agendaMedicoList = getEntityManager().createNamedQuery(AgendaMedico.AGENDA_MEDICO, AgendaMedico.class)
	    												.setParameter("situacao", SituacaoEnum.ATIVO.getCodigo())
	    												.setParameter("idMedico", medico.getId())
	    												.setParameter("dataAtual", new Date(), TemporalType.DATE)
	    												.getResultList();
	    		Set<AgendaMedico> agendaSet = new HashSet<AgendaMedico>(agendaMedicoList);
	    		List<AgendaMedico> retorno = new ArrayList<AgendaMedico>(agendaSet);
	    		
	    		return retorno;
	    		
	    	} catch (Exception e) {
	    		this.tratarMensagemErro(null, e.getMessage());
	    		return new ArrayList<AgendaMedico>();
	    	}finally {
	    		this.closeEntityManager();
	    	}
	    }
	    
	    /**
	     * Verifica se o horario ja foi registrado para o dia.
	     * @param medico 
	     * 
	     * @return
	     */
	    public boolean verificaAgendamentoHorario(Medico medico, AgendaMedico agendaMedico) {
	    	try {
	    		
	    		this.createEntityManager();
	    		
	    		List<AgendaMedico> agendaMedicoList = getEntityManager().createNamedQuery(AgendaMedico.VERIFICAR_DATA, AgendaMedico.class)
	    				.setParameter("situacao", SituacaoEnum.ATIVO.getCodigo())
	    				.setParameter("idAgenda", Util.isValueNotBlankOrNotEmpty(agendaMedico.getId()) ? agendaMedico.getId() : -1)
	    				.setParameter("idMedico", medico.getId())
	    				.setParameter("data", agendaMedico.getData(), TemporalType.DATE)
	    				.setParameter("horaInicio", agendaMedico.getHorarioInicioAtendimento(), TemporalType.TIMESTAMP)
	    				.setParameter("horaFim", agendaMedico.getHorarioFimAtendimento(), TemporalType.TIMESTAMP)
	    				.getResultList();
	    		Set<AgendaMedico> agendaSet = new HashSet<AgendaMedico>(agendaMedicoList);

	    		return agendaSet.size() > 0;
	    		
	    	} catch (Exception e) {
	    		this.tratarMensagemErro(null, e.getMessage());
	    		return true;
	    	}finally {
	    		this.closeEntityManager();
	    	}
	    }
	    
	}