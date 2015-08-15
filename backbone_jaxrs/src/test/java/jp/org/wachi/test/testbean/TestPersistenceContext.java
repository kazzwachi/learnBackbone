package jp.org.wachi.test.testbean;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;

import jp.org.wachi.testbean.TestBean;
import jp.org.wachi.testbean.TestBeanDao;

@RunWith(Arquillian.class)
public class TestPersistenceContext {
	
	@Inject
	TestBeanDao dao;
	
	@Inject
	Logger logger;
	
	@Deployment
	public static WebArchive createDeployment() {
		return TestHelper.createDeployment();
	}
	
	@Test
	public void should_exists_dao() {
		assertNotNull(dao);
	}
	@Test
	public void should_exists_logger(){
		assertNotNull(logger);
	}
	@Test
	public void should_dao_has_entityClass_correctry(){
		assertNotNull(dao.getEntityClass());
		assertEquals(dao.getEntityClass(), TestBean.class);
		logger.info(dao.getEntityClass().toString());
	}
	@Test
	public void should_dao_has_entityManager_correctry(){
		assertNotNull(dao.getEntityManager());
		logger.info(dao.getEntityManager().getEntityManagerFactory().toString());
	}
}
