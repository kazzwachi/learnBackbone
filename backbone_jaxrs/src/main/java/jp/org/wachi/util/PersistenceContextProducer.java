package jp.org.wachi.util;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
@ApplicationScoped
public class PersistenceContextProducer {

	@Produces
	@PersistenceUnit(unitName="primary")
	EntityManagerFactory emf; 
	
	@Produces
	@PersistenceContext(unitName="primary")
	EntityManager em;
		
}
