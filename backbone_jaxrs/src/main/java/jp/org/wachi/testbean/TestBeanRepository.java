package jp.org.wachi.testbean;

import javax.ejb.Stateless;

import jp.org.wachi.util.RepositoryBase;

@Stateless
public class TestBeanRepository extends RepositoryBase<TestBean, Long> {

	@Override
	public TestBean newEntity() {
		return new TestBean();
	}

}
