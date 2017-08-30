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
       this.createEntityManager();
       this.persistedClass = persistedClass;
   }

   private void createEntityManager() {
	   if(this.entityManager == null) {
		   EntityManagerFactory factory = Persistence.createEntityManagerFactory("sisClinica");
		   this.setEntityManager(factory.createEntityManager());
	   }
   }

   public T salvar(@Valid T entity) {
	   	try {
	   		EntityTransaction t = getEntityManager().getTransaction();
	   		t.begin();
	   		getEntityManager().persist(entity);
	   		getEntityManager().flush();
	   		t.commit();
	   		return entity;
	   	} catch (Exception e) {
	   		this.tratarMensagemErro("formPrincipal:growlMsgm", e.getMessage(), "");
   		}
	return entity;
   }

   public T atualizar(@Valid T entity) {
	   try {
		   EntityTransaction t = getEntityManager().getTransaction();
	       t.begin();
	       getEntityManager().merge(entity);
	       getEntityManager().flush();
	       t.commit();
	       return entity;
   		} catch (Exception e) {
   			this.tratarMensagemErro("formPrincipal:growlMsgm", e.getMessage(), "");
		}
	   return entity;
   }

   public void remover(I id) {
	   try {
		   T entity = encontrar(id);
	       EntityTransaction tx = getEntityManager().getTransaction();
	       tx.begin();
	       T mergedEntity = getEntityManager().merge(entity);
	       getEntityManager().remove(mergedEntity);
	       getEntityManager().flush();
	       tx.commit();
	   } catch (Exception e) {
		   this.tratarMensagemErro("formPrincipal:growlMsgm", e.getMessage(), "");
	   }
   }

   public List<T> getList() {
	   try {
		   CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		   CriteriaQuery<T> query = builder.createQuery(persistedClass);
		   query.from(persistedClass);
		   return getEntityManager().createQuery(query).getResultList();
	   } catch (Exception e) {
		   this.tratarMensagemErro("formPrincipal:growlMsgm", e.getMessage(), "");
	   }
	   return new ArrayList<T>();
   }

   public T encontrar(I id) {
	   try {
		   return getEntityManager().find(persistedClass, id);
	   } catch (Exception e) {
		   this.tratarMensagemErro("formPrincipal:growlMsgm", e.getMessage(), "");
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