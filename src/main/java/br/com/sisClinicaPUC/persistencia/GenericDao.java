package br.com.sisClinicaPUC.persistencia;

import java.io.Serializable;
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

public abstract class GenericDao<T, I extends Serializable> extends ValidacaoException {

   private Class<T> persistedClass;
   @Inject
   private EntityManager entityManager;

   protected GenericDao() {
   }

   protected GenericDao(Class<T> persistedClass) {
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
       EntityTransaction t = getEntityManager().getTransaction();
       t.begin();
       getEntityManager().persist(entity);
       getEntityManager().flush();
       t.commit();
       return entity;
   }

   public T atualizar(@Valid T entity) {
       EntityTransaction t = getEntityManager().getTransaction();
       t.begin();
       getEntityManager().merge(entity);
       getEntityManager().flush();
       t.commit();
       return entity;
   }

   public void remover(I id) {
       T entity = encontrar(id);
       EntityTransaction tx = getEntityManager().getTransaction();
       tx.begin();
       T mergedEntity = getEntityManager().merge(entity);
       getEntityManager().remove(mergedEntity);
       getEntityManager().flush();
       tx.commit();
   }

   public List<T> getList() {
       CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
       CriteriaQuery<T> query = builder.createQuery(persistedClass);
       query.from(persistedClass);
       return getEntityManager().createQuery(query).getResultList();
   }

   public T encontrar(I id) {
       return getEntityManager().find(persistedClass, id);
   }

	private EntityManager getEntityManager() {
		return entityManager;
	}
	
	private void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
}