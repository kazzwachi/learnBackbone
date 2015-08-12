package jp.org.wachi.testbean;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.slf4j.Logger;

@Path("/testbeano")
public class TestBeanServiceOld {

	@Inject
	Logger logger;
	
	private static Map<Long,TestBean> map;
	private static long lastId = 0L;
		
	static{
		map = new HashMap<>();
//		for(int i = 0; i < 3; i++){
//			TestBean testBean = new TestBean();
//			testBean.setId(++lastId);
//			testBean.setKey("0000000" + String.valueOf(lastId));
//			testBean.setValue("VALUE000" + String.valueOf(lastId));
//			map.put(lastId, testBean);
//		}
	}
	
	@GET
	@Produces("application/json")
	@Path("{id}")
	public TestBean read(@PathParam("id") Long id){
		TestBean testBean = TestBeanServiceOld.map.get(id);
		logger.info("read method start.");
		logger.info("returns bean value:"+testBean.getValue());
		return testBean;
	}
	
	@GET
	@Produces("application/json")
	public Collection<TestBean> readBeans(){
		return TestBeanServiceOld.map.values();
	}
	
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public TestBean create(TestBean testBean){
		logger.info("create method start.");
		TestBeanServiceOld.lastId++;
		testBean.setId(TestBeanServiceOld.lastId);
		TestBeanServiceOld.map.put(TestBeanServiceOld.lastId, testBean);
		logger.info("new bean added value:" + testBean.getValue());
		return testBean;
	}
	
	@PUT
	@Consumes("application/json")
	@Produces("application/json")
	@Path("{id}")
	public void update(TestBean testBean){
		logger.info("update method start.");
		TestBeanServiceOld.map.put(testBean.getId(), testBean);
		logger.info("test bean updated value:" + testBean.getValue());
	}
	
	@DELETE
	@Path("{id}")
	public void delete(@PathParam("id") Long id){
		logger.info("delete method start.");
		TestBeanServiceOld.map.remove(id);
		logger.info("test bean deleted id:" + id);
	}
}
