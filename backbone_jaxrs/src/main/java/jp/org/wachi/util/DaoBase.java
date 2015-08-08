package jp.org.wachi.util;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public abstract class DaoBase<E,K extends Serializable> {

	@Inject
	@UseKazzDB
	private EntityManager entityManager;
	
	private Class<E> entityClass;

	
	public Class<E> getEntityClass() {
		return entityClass;
	}

	@Inject
	public void setEntityClass(Class<E> entityClass) {
		this.entityClass = entityClass;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager){
		this.entityManager = entityManager;
	}
	
	public void create(E entity) {
		entityManager.persist(entity);
	}

	public E read(K id) {
		return entityManager.find(entityClass, id);
	}

	public void update(E entity) {
		entityManager.merge(entity);
	}

	public void delete(E entity) {
		entityManager.remove(entity);
	}
	public List<E> findBy(String name,Object key){
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<E> criteriaQuery = criteriaBuilder.createQuery(entityClass);
		Root<E> root = criteriaQuery.from(entityClass);
		criteriaQuery.select(root);
		criteriaQuery.where(criteriaBuilder.equal(root.get(name), key));
		TypedQuery<E> tq =  entityManager.createQuery(criteriaQuery);
		return tq.getResultList();
	}
	
	public List<E> list(String sortBy,int offset,int count){
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<E> criteriaQuery = criteriaBuilder.createQuery(entityClass);
		Root<E> root = criteriaQuery.from(entityClass);
		criteriaQuery.select(root);
		criteriaQuery.orderBy(criteriaBuilder.asc(root.get("id")));
		TypedQuery<E> tq =  entityManager.createQuery(criteriaQuery);
		return tq.setFirstResult(offset).setMaxResults(count).getResultList();
	}
}