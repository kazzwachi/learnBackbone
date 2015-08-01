package jp.org.wachi.services.resources;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import jp.org.wachi.services.models.TestBean;

@Path("/testbean")
public class TestBeanService {
	
	private static Map<Long,TestBean> map;
	private static long lastId = 0L;
		
	static{
		map = new HashMap<>();
	}
	
	@GET
	@Produces("application/json")
	@Path("{id}")
	public TestBean read(@PathParam("id") Long id){
		return TestBeanService.map.get(id);
	}
	
	@GET
	@Produces("application/json")
	public Collection<TestBean> readBeans(){
		return TestBeanService.map.values();
	}
	
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public TestBean create(TestBean testBean){
		System.out.println("Post");
		TestBeanService.lastId++;
		testBean.setId(TestBeanService.lastId);
		TestBeanService.map.put(TestBeanService.lastId, testBean);
		System.out.println(testBean.getId());
		return testBean;
	}
	
	@PUT
	@Consumes("application/json")
	@Produces("application/json")
	public void update(TestBean testBean){
		System.out.println("Put");
		TestBeanService.map.put(testBean.getId(), testBean);
	}
	
	@DELETE
	@Consumes("appliaction/json")
	public void delete(TestBean testBean){
		System.out.println("Delete");
		TestBeanService.map.remove(testBean.getId());
	}
}
