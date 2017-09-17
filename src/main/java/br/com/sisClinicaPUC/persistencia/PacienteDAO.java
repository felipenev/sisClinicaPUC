package br.com.sisClinicaPUC.persistencia;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import br.com.sisClinicaPUC.entidade.Paciente;
import br.com.sisClinicaPUC.util.Util;
import br.com.sisClinicaPUC.vo.SituacaoEnum;
   
   
	public class PacienteDAO extends GenericDAO<Paciente, Long>{
		
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
	    		
	    		this.createEntityManager();
	    		
	    		List<Paciente> pacienteList = getEntityManager().createNamedQuery(Paciente.PACIENTE_POR_SITUACAO, Paciente.class).setParameter("situacao", SituacaoEnum.ATIVO.getCodigo()).getResultList();
	    		
	    		return pacienteList;
	    	} catch (Exception e) {
	    		this.tratarMensagemErro(null, e.getMessage());
	    		return new ArrayList<Paciente>();
	    	}finally {
	    		this.closeEntityManager();
	    	}
	    }

		public boolean findByCPFAtivo(Paciente pac) {
			
			try {
				this.createEntityManager();
				
				Paciente paciente = (Paciente) this.getEntityManager().createQuery("select p from Paciente p where p.CPF = :cpf and (p.id != :idPac and p.id is not null)")
						.setParameter("cpf", pac.getCPF())
						.setParameter("idPac", Util.isValueNotBlankOrNotEmpty(pac.getId()) ? pac.getId() : -1)
						.getSingleResult();

				return paciente != null;
				
			} catch (NoResultException e) {
				return false;
			} catch (Exception e) {
				this.tratarMensagemErro(null, e.getMessage());
				return false;
			}finally {
				this.closeEntityManager();
			}
		}
	    
	}