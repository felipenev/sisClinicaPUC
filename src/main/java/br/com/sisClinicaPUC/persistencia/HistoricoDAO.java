package br.com.sisClinicaPUC.persistencia;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.com.sisClinicaPUC.entidade.Historico;
import br.com.sisClinicaPUC.vo.TipoPesquisaHistoricoEnum;
   
   
	public class HistoricoDAO extends GenericDAO<Historico, Long>{
		
		private static final long serialVersionUID = 1L;

		public HistoricoDAO() {
			super(Historico.class);
		}
	  
	    public boolean inserir(Historico historico) {
	    	try {
	    		this.salvar(historico);
	            return true;
	    	} catch (Exception e) {
	    		this.tratarMensagemErro(null, e.getMessage());
	            return false;
	    	}
		}
	    
	    public List<Historico> getHistoricoPorTipo(TipoPesquisaHistoricoEnum tipoPesquisaHistoricoEnum){
	    	try {
				this.createEntityManager();
				List<Historico> historicoList = this.getEntityManager().createNamedQuery(Historico.HISTORICO_POR_TIPO, Historico.class).setParameter("tipoPesquisaHistoricoEnum", tipoPesquisaHistoricoEnum.getCodigo()).getResultList();
				Set<Historico> historicoSet = new HashSet<Historico>(historicoList);
				List<Historico> retorno = new ArrayList<Historico>(historicoSet);
				
				return retorno;
				
			} catch (Exception e) {
				this.tratarMensagemErro(null, e.getMessage());
				return new ArrayList<Historico>();
			}finally {
				this.closeEntityManager();
			}
	    }
	    
	}