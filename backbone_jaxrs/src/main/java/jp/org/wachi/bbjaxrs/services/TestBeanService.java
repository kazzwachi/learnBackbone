package jp.org.wachi.bbjaxrs.services;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import jp.org.wachi.bbjaxrs.models.TestBean;

@Path("/testbean")
public class TestBeanService {
	
	
	@GET
	@Produces("application/json")
	@Path("{id}")
	public TestBean read(@PathParam("id") Long id){
		Map<Long,TestBean> beans = new HashMap<>();
		TestBean bean1 = new TestBean();
		bean1.setId(1L);
		bean1.setKey("00000001");
		bean1.setValue("VALUE0001");

		TestBean bean2 = new TestBean();
		bean2.setId(2L);
		bean2.setKey("00000002");
		bean2.setValue("VALUE0002");
		
		beans.put(bean1.getId(), bean1);
		beans.put(bean2.getId(), bean2);

		return beans.get(id);
	}
	
	@GET
	@Produces("application/json")
	public Collection<TestBean> readBeans(){
		Map<Long,TestBean> beans = new HashMap<>();
		TestBean bean1 = new TestBean();
		bean1.setId(1L);
		bean1.setKey("00000001");
		bean1.setValue("VALUE0001");

		TestBean bean2 = new TestBean();
		bean2.setId(2L);
		bean2.setKey("00000002");
		bean2.setValue("VALUE0002");
		
		beans.put(bean1.getId(), bean1);
		beans.put(bean2.getId(), bean2);

		return beans.values();
	}
	
	@POST
	@Consumes("application/json")
	public TestBean create(TestBean testBean){
		System.out.println(testBean.getKey());
		System.out.println(testBean.getValue());
		testBean.setId(3L);
		return testBean;
	}
	
}
