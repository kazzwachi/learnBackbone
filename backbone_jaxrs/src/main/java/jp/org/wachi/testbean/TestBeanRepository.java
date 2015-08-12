package jp.org.wachi.testbean;

import jp.org.wachi.util.RepositoryBase;

public class TestBeanRepository extends RepositoryBase<TestBean, Long> {

	@Override
	public TestBean newEntity() {
		return new TestBean();
	}

}
