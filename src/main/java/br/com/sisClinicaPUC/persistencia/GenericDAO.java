package br.com.sisClinicaPUC.persistencia;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.validation.Valid;

import br.com.sisClinicaPUC.util.ValidacaoException;

public abstract class GenericDAO<T, I extends Serializable>  extends ValidacaoException implements Serializable{

	private static final long serialVersionUID = 1L;
	
private Class<T> persistedClass;
   @Inject
   private EntityManager entityManager;

   protected GenericDAO() {
   }

   protected GenericDAO(Class<T> persistedClass) {
       this();
//       this.createEntityManager();
       this.persistedClass = persistedClass;
   }

   protected void createEntityManager() {
	   if(this.entityManager == null) {
		   EntityManagerFactory factory = Persistence.createEntityManagerFactory("sisClinica");
		   this.setEntityManager(factory.createEntityManager());
	   }
   }
   
   protected void closeEntityManager() {
	   if(this.entityManager != null) {
		   this.getEntityManager().close();
		   this.setEntityManager(null);
	   }
   }

   public T salvar(@Valid T entity) {
	   	try {
	   		this.createEntityManager();
	   		
	   		EntityTransaction t = getEntityManager().getTransaction();
	   		t.begin();
	   		getEntityManager().persist(entity);
	   		getEntityManager().flush();
	   		t.commit();
	   		
	   		
	   		return entity;
	   	} catch (Exception e) {
	   		this.tratarMensagemErro("formPrincipal:growlMsgm", e.getMessage(), "");
   		}finally {
   			this.closeEntityManager();
		}
	   	
	   	return entity;
   }

   public T atualizar(@Valid T entity) {
	   try {
		   
		   this.createEntityManager();
		   
		   EntityTransaction t = getEntityManager().getTransaction();
	       t.begin();
	       getEntityManager().merge(entity);
	       getEntityManager().flush();
	       
	       t.commit();
	       return entity;
   		} catch (Exception e) {
   			this.tratarMensagemErro("formPrincipal:growlMsgm", e.getMessage(), "");
		}finally {
			this.closeEntityManager();
		}
	   
	   return entity;
   }

   public void remover(I id) {
	   try {
		   
		   this.createEntityManager();
		   
		   T entity = encontrar(id);
	       EntityTransaction tx = getEntityManager().getTransaction();
	       tx.begin();
	       T mergedEntity = getEntityManager().merge(entity);
	       getEntityManager().remove(mergedEntity);
	       getEntityManager().flush();
	       tx.commit();
	       
	   } catch (Exception e) {
		   this.tratarMensagemErro("formPrincipal:growlMsgm", e.getMessage(), "");
	   }finally {
		   this.closeEntityManager();
	   }
   }

   public List<T> getList() {
	   try {
		   
		   this.createEntityManager();
		   
		   CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		   CriteriaQuery<T> query = builder.createQuery(persistedClass);
		   query.from(persistedClass);
		   List<T> retorno = getEntityManager().createQuery(query).getResultList();
		   
		   return retorno;
	   } catch (Exception e) {
		   this.tratarMensagemErro("formPrincipal:growlMsgm", e.getMessage(), "");
	   }finally {
		   this.closeEntityManager();
	   }
	   
	   return new ArrayList<T>();
   }

   public T encontrar(I id) {
	   try {
		   
		   this.createEntityManager();
		   
		   T retorno = getEntityManager().find(persistedClass, id);
		   
		   return retorno;
		   
	   } catch (Exception e) {
		   this.tratarMensagemErro("formPrincipal:growlMsgm", e.getMessage(), "");
	   }finally {
		   this.closeEntityManager();
	   }
	   
	   return null;
   }

	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	private void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
}