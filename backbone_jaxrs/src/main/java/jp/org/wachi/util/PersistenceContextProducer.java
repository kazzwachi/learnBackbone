package jp.org.wachi.util;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
public class PersistenceContextProducer {

	@Produces
	@Dependent
	@PersistenceContext(unitName="primary")
	@UseKazzDB
	private EntityManager entityManager;
		
}
