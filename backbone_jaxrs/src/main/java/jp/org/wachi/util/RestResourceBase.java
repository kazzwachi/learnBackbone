package jp.org.wachi.util;

import java.io.Serializable;
import java.net.URI;

import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.UriBuilder;

import org.slf4j.Logger;

public abstract class RestResourceBase<E, K extends Serializable> {

	@Inject
	Logger logger;

	protected Class<?> restResourceClass;
	
	protected Repository<E, K> repository;

	protected Class<K> idClass;

	protected abstract void setRepository(Repository<E, K> repository);

	public Class<?> getRestResourceClass() {
		return restResourceClass;
	}

	protected void setRestResourceClass(Class<?> restResourceClass){
		this.restResourceClass = restResourceClass;
	}

	protected abstract void prepareRestResourceClass();

	public Repository<E, K> getRepository() {
		return repository;
	}

	protected void setIdClass(Class<K> idClass) {
		this.idClass = idClass;
	}

	public Class<K> getIdClass() {
		return this.idClass;
	}

	public Response postEntity(E entity) {
		repository.create(entity);

		if(null == restResourceClass){
			prepareRestResourceClass();
		}
		
		UriBuilder uriBuilder = UriBuilder.fromResource(restResourceClass);
		try {
			uriBuilder.path(restResourceClass,"getEntity");
		} catch (IllegalArgumentException | SecurityException e) {
			throw new WebApplicationException(e);
		}
		URI uri = uriBuilder.build(extractId(entity));
		ResponseBuilder responseBuilder = Response.created(uri);
		return responseBuilder.build();
	}

	public E getEntity(K id) {
		E entity = repository.read(id);
		if (null == entity) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		return entity;
	}

	public Response putEntity(E entity) {
		repository.update(entity);
		ResponseBuilder responseBuilder;
		responseBuilder = Response.ok(entity);
		return responseBuilder.build();
	}

	public Response deleteEntity(K id) {
		repository.delete(id);
		ResponseBuilder responseBuilder = Response.noContent();
		return responseBuilder.build();
	}

	protected abstract K extractId(E entity);

}
