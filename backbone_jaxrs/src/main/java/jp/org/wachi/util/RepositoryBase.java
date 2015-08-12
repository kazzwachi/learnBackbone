package jp.org.wachi.util;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

@RequestScoped
@Transactional
public abstract class RepositoryBase<E, K extends Serializable> {

	private DaoBase<E, K> dao;

	public DaoBase<E, K> getDao() {
		return dao;
	}
	
	@Inject
	protected void setDao(DaoBase<E, K> dao) {
		this.dao = dao;
	}

	public abstract E newEntity();
	
	public void create(E entity) {
		if (null == entity) {
			return;
		}
		dao.create(entity);
	}

	public E read(K id) {
		if (id == null) {
			return null;
		}
		return dao.read(id);
	}

	public void update(E entity) {
		if (null == entity) {
			return;
		}
		dao.update(entity);
	}

	public void delete(E entity) {
		if (null == entity) {
			return;
		}
		dao.delete(entity);
	}

	public void delete(K id) {
		if (id == null) {
			return;
		}
		E entity = dao.read(id);
		if (null == entity) {
			return;
		}
		dao.delete(entity);
	}
	
	public List<E> list(String sortBy,int offset,int count){
		return dao.list(sortBy,offset, count);
	}
}