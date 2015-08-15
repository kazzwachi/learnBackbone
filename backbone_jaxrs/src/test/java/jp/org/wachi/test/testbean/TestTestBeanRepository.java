package jp.org.wachi.test.testbean;

import static org.junit.Assert.*;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;

import jp.org.wachi.testbean.TestBean;
import jp.org.wachi.testbean.TestBeanRepository;

@RunWith(Arquillian.class)
public class TestTestBeanRepository {
	
	@PersistenceUnit(unitName="primary")
	EntityManagerFactory emf;
	
	@PersistenceContext
	EntityManager em;
	
	@Inject
	TestBeanRepository repository;
	
	@Inject
	Logger logger;
	
	@Deployment
	public static WebArchive createDeployment() {
		return TestHelper.createTestDeployment();
	}
	
	@Test
	public void should_exist_entityManagerFactory(){
		assertNotNull(emf);
		assertNotNull(em);
	}
	
	@Test
	public void should_exist_repository(){
		assertNotNull(repository);
	}
	
	@Test
	public void testCreate(){
		TestBean testBean = new TestBean();
		testBean.setKey("00000001");
		testBean.setValue("Value0001");
		repository.create(testBean);
		assertNotNull(testBean.getId());
	}
	
	@Test
	public void testRead(){
		TestBean testBean = new TestBean();
		testBean.setKey("00000002");
		testBean.setValue("Value0002");
		repository.create(testBean);
		long id = testBean.getId();

		assertNotNull(repository.read(id));
	}

	@Test
	public void testUpdate(){
		TestBean testBean = new TestBean();
		testBean.setKey("00000003");
		testBean.setValue("Value0003");
		repository.create(testBean);
		
		testBean.setValue("NewValue");
		repository.update(testBean);
		
		testBean = null;
		List<TestBean> testBeans = repository.findBy("key", "00000003");
		assertEquals("NewValue",testBeans.get(0).getValue());		
	}
	
	@Test
	public void testDelete(){
		TestBean testBean = new TestBean();
		testBean.setKey("00000004");
		testBean.setValue("Value0004");
		repository.create(testBean);
		long id = testBean.getId();
		
		repository.delete(id);

		assertNull(repository.read(id));
		
	}

	
	@Test
	public void testFindBy(){
		List<TestBean> testBeans = repository.findBy("key", "00000001");
		assertEquals("Value0001",testBeans.get(0).getValue());
	}
	
	@Test
	public void testList(){
		TestBean testBean = new TestBean();
		testBean.setKey("00000005");
		testBean.setValue("Value0005");
		repository.create(testBean);
		
		List<TestBean> testBeans = repository.list("KEY", 0, 10);
		assertNotSame(0,testBeans.size());
		
	}
	
}
