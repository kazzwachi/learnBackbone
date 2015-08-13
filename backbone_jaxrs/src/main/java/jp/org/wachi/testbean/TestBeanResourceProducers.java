package jp.org.wachi.testbean;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
@ApplicationScoped
public class TestBeanResourceProducers {

	@Produces
	public Class<TestBean> getTestBeanClass(){
		return TestBean.class;
	}
}
