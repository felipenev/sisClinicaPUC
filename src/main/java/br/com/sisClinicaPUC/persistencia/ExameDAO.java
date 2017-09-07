package br.com.sisClinicaPUC.persistencia;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.com.sisClinicaPUC.entidade.Exame;
import br.com.sisClinicaPUC.entidade.Medico;
import br.com.sisClinicaPUC.vo.SituacaoExameEnum;
   
   
	public class ExameDAO extends GenericDAO<Exame, Long>{
		
		private static final long serialVersionUID = 1L;

		public ExameDAO() {
			super(Exame.class);
		}
	  
	    public boolean inserir(Exame exame) {
	    	try {
	    		this.salvar(exame);
	            return true;
	    	} catch (Exception e) {
	    		this.tratarMensagemErro("formPrincipal:growlMsgm", e.getMessage(), "");
	            return false;
	    	}
		}
	    
	    public boolean alterar(Exame exame) {
	    	try {
	    		this.atualizar(exame);
	    		return true;
	    	} catch (Exception e) {
	    		this.tratarMensagemErro("formPrincipal:growlMsgm", e.getMessage(), "");
	    		return false;
	    	}
	    }
	    
	    public boolean excluir(Exame exame) {
	    	try {
	    		this.remover(exame.getId());
	    		return true;
	    	} catch (Exception e) {
	    		this.tratarMensagemErro("formPrincipal:growlMsgm", e.getMessage(), "");
	    		return false;
	    	}
	    }

		public List<Exame> getExamesSolicitadosPorMedico(Medico medico) {
			try {
				this.createEntityManager();
				
	    		List<Exame> exameList = getEntityManager().createNamedQuery(Exame.EXAME_SOLICITADO_POR_MEDICO, Exame.class).setParameter("idMedico", medico.getId()).getResultList();
	    		Set<Exame> exameSet = new HashSet<Exame>(exameList);
	    		List<Exame> retorno = new ArrayList<Exame>(exameSet);

	    		return retorno;
	    		
	    	} catch (Exception e) {
	    		this.tratarMensagemErro(null, e.getMessage());
	    		return new ArrayList<Exame>();
	    	}finally {
	    		this.closeEntityManager();
			}
		}

		public List<Exame> getExamesPendentesResultado() {
			try {
				this.createEntityManager();
				List<Exame> exameList = this.getEntityManager().createNamedQuery(Exame.EXAME_PENDENTE_RESULTADO_LABORATORIO, Exame.class).setParameter("situacaoExame", SituacaoExameEnum.AGUARDANDO_RESULTADO.getCodigo()).getResultList();
				Set<Exame> exameSet = new HashSet<Exame>(exameList);
				List<Exame> retorno = new ArrayList<Exame>(exameSet);
				
				return retorno;
				
			} catch (Exception e) {
				this.tratarMensagemErro(null, e.getMessage());
				return new ArrayList<Exame>();
			}finally {
				this.closeEntityManager();
			}
		}
	    
	}