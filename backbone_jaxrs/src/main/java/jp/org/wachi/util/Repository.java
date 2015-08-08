package jp.org.wachi.util;

import java.io.Serializable;
import java.util.List;

public interface Repository<E,K extends Serializable> {
	public E newEntity();
	public void create(E entity);
	public E read(K id);
	public void update(E entity);
	public void delete(E entity);
	public void delete(K id);
	public List<E> list(String sortBy,int offset,int count);
}
