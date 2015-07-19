package jp.org.wachi.bbjaxrs.services;

import java.util.List;
import java.util.Vector;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;

import jp.org.wachi.bbjaxrs.models.TestBean;

@Path("/testbean")
public class TestBeanService {
	
	@GET
	@Produces("application/json")
	public List<TestBean> readBeans(){
		List<TestBean> beans = new Vector<>();
		
		TestBean bean1 = new TestBean();
		bean1.setId(1L);
		bean1.setKey("00000001");
		bean1.setValue("VALUE0001");

		TestBean bean2 = new TestBean();
		bean2.setId(1L);
		bean2.setKey("00000001");
		bean2.setValue("VALUE0001");
		
		beans.add(bean1);
		beans.add(bean2);
		
		return beans;
	}
}
