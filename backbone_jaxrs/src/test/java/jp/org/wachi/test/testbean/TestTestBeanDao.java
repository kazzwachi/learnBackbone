package jp.org.wachi.test.testbean;

import static org.junit.Assert.*;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class TestTestBeanDao {
	
	@Deployment
	public static WebArchive createDeployment() {
		return TestHelper.createDeployment();
	}

	@Test(timeout = 20000)
	public void should_exists_eao() {
		fail("Not implemented");
	}
}
