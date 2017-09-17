package br.com.sisClinicaPUC.persistencia;

import javax.persistence.NoResultException;

import br.com.sisClinicaPUC.entidade.Usuario;
import br.com.sisClinicaPUC.util.Util;
import br.com.sisClinicaPUC.vo.SituacaoEnum;
   
   
  public class UsuarioDAO extends GenericDAO<Usuario, Long>{
   
	private static final long serialVersionUID = 1L;

	public UsuarioDAO() {
		super(Usuario.class);
	}
	  
    public Usuario getUsuario(Usuario usuario) {
   
    	try {
    		this.createEntityManager();
    		Usuario usuarioRetorno = (Usuario) this.getEntityManager().createQuery("SELECT u from Usuario u where u.login = :login and u.senha = :senha and u.ativoInativo = :ativoInativo")
						                   .setParameter("login", usuario.getLogin())
						                   .setParameter("senha", usuario.getSenha())
    										.setParameter("ativoInativo", SituacaoEnum.ATIVO.getCodigo()).getSingleResult();
   
    		return usuarioRetorno;
    	} catch (NoResultException e) {
    		return null;
    	}finally {
			this.closeEntityManager();
		}
	}
   
	public Usuario inserirUsuario(Usuario usuario) {
    	try {
        	Usuario usr = null;
        	usr = super.salvar(usuario);
        			
            return usr;
            
    	} catch (Exception e) {
    		this.tratarMensagemErro(null, e.getMessage());
    		return null;
    	}
    }
	
	public boolean findByLoginAtivo(Usuario usuario) {
		try {
			
			this.createEntityManager();
			
			Usuario usuarioRetorno = (Usuario) this.getEntityManager().createQuery("SELECT u from Usuario u where u.login = :login and u.ativoInativo = :ativoInativo and (u.id != :idUsr and u.id is not null)")
	                   .setParameter("login", usuario.getLogin().trim())
	                   .setParameter("ativoInativo", SituacaoEnum.ATIVO.getCodigo())
	                   .setParameter("idUsr", Util.isValueNotBlankOrNotEmpty(usuario.getId()) ? usuario.getId() : -1 ).getSingleResult();

			return usuarioRetorno != null;
			
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