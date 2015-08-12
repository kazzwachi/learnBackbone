package jp.org.wachi.testbean;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;

@ApplicationScoped
public class TestBeanResourceProducers {

	@Produces
	@Dependent
	public TestBeanDao getTestBeanDao(){
		return new TestBeanDao();
	}
	
	@Produces
	@RequestScoped
	public TestBeanRepository getTestBeanRepository(){
		return new TestBeanRepository();
	}
	
	@Produces
	@Dependent
	public Class<TestBean> getTestBeanClass(){
		return TestBean.class;
	}
}
