package jp.org.wachi.testbean;

import javax.enterprise.context.Dependent;

import jp.org.wachi.util.RepositoryBase;

@Dependent
public class TestBeanRepository extends RepositoryBase<TestBean, Long> {

	@Override
	public TestBean newEntity() {
		return new TestBean();
	}

}
