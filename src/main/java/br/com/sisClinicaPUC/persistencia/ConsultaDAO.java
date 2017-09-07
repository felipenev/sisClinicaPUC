package br.com.sisClinicaPUC.persistencia;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.TemporalType;

import br.com.sisClinicaPUC.entidade.Consulta;
import br.com.sisClinicaPUC.entidade.Medico;
import br.com.sisClinicaPUC.entidade.Paciente;
import br.com.sisClinicaPUC.vo.SituacaoConsultaEnum;
   
   
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
	    
	    /**
	     * Carrega os pacientes que possuem consulta marcada com o medico na data do atendimento
	     * 
	     * @return
	     */
	    public List<Paciente> getPacientePorMedicoDataConsultaList(Medico medico) {
	    	try {
	    		
	    		this.createEntityManager();
	    		
	    		List<Consulta> consultaList = getEntityManager()
	    				.createNamedQuery(Consulta.PACIENTE_POR_MEDICO_DATA_CONSULTA, Consulta.class)
	    				.setParameter("situacao", SituacaoConsultaEnum.MARCADA.getCodigo())
	    				.setParameter("idMedico", medico.getId())
	    				.setParameter("dataConsulta", new Date(), TemporalType.DATE)
	    				.getResultList();
	    		
	    		List<Paciente> retorno = new ArrayList<Paciente>();
	    		for (Consulta consulta : consultaList) {
					retorno.add(consulta.getPaciente());
				}
	    		return retorno;
	    	} catch (Exception e) {
	    		this.tratarMensagemErro(null, e.getMessage());
	    		return new ArrayList<Paciente>();
	    	}finally {
	    		this.closeEntityManager();
	    	}
	    }
	    
	}