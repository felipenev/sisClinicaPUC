package br.com.sisClinicaPUC.persistencia;

import javax.persistence.EntityManager;
  import javax.persistence.EntityManagerFactory;
  import javax.persistence.NoResultException;
  import javax.persistence.Persistence;

import br.com.sisClinicaPUC.entidade.Usuario;
   
   
  public class UsuarioDAO extends GenericDao<Usuario, Long>{
   
	private static final long serialVersionUID = 1L;

	public UsuarioDAO() {
		super(Usuario.class);
	}
	  
	
    private EntityManagerFactory factory = Persistence.createEntityManagerFactory("sisClinica");
    private EntityManager em = factory.createEntityManager();
   
    public Usuario getUsuario(String nomeUsuario, String senha) {
   
    	try {
            	  
    		Usuario usuario = (Usuario) em.createQuery("SELECT u from Usuario u where u.nomeUsuario = :name and u.senha = :senha")
						                   .setParameter("name", nomeUsuario)
						                   .setParameter("senha", senha).getSingleResult();
   
    		return usuario;
    	} catch (NoResultException e) {
    		return null;
    	}
	}
   
	public boolean inserirUsuario(Usuario usuario) {
    	try {
        	em.persist(usuario);
            return true;
    	} catch (Exception e) {
        	e.printStackTrace();
            return false;
    	}
    }
    
    public boolean deletarUsuario(Usuario usuario) {
    	try {
        	em.remove(usuario);
            return true;
    	} catch (Exception e) {
        	e.printStackTrace();
            return false;
    	}
	}
    
}