package br.com.sisClinicaPUC.persistencia;

import java.util.ArrayList;
import java.util.List;

import br.com.sisClinicaPUC.entidade.Paciente;
import br.com.sisClinicaPUC.vo.SituacaoEnum;
   
   
	public class PacienteDAO extends GenericDao<Paciente, Long>{
		
		private static final long serialVersionUID = 1L;

		public PacienteDAO() {
			super(Paciente.class);
		}
	  
	    public boolean inserir(Paciente paciente) {
	    	try {
	    		this.salvar(paciente);
	            return true;
	    	} catch (Exception e) {
	    		this.tratarMensagemErro(null, e.getMessage());
	            return false;
	    	}
		}
	    
	    public boolean alterar(Paciente paciente) {
	    	try {
	    		this.atualizar(paciente);
	    		return true;
	    	} catch (Exception e) {
	    		this.tratarMensagemErro(null, e.getMessage());
	    		return false;
	    	}
	    }
	    
	    public boolean excluir(Paciente paciente) {
	    	try {
	    		this.remover(paciente.getId());
	    		return true;
	    	} catch (Exception e) {
	    		this.tratarMensagemErro(null, e.getMessage());
	    		return false;
	    	}
	    }
	    
	    /**
	     * Recupera os pacientes na situacao ativo
	     * 
	     * @return
	     */
	    public List<Paciente> getPacienteAtivoList() {
	    	try {
	    		List<Paciente> pacienteList = getEntityManager().createNamedQuery(Paciente.PACIENTE_POR_SITUACAO, Paciente.class).setParameter("situacao", SituacaoEnum.ATIVO.getCodigo()).getResultList();
	    		return pacienteList;
	    	} catch (Exception e) {
	    		this.tratarMensagemErro(null, e.getMessage());
	    		return new ArrayList<Paciente>();
	    	}
	    }

	}