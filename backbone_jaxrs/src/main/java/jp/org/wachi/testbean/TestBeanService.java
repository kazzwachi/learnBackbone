package jp.org.wachi.testbean;

import java.util.Collection;

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

@Path("/testbean")
public class TestBeanService {

	@Inject
	Logger logger;
	
	@Inject
	TestBeanRepository repository;
	
	@GET
	@Produces("application/json")
	@Path("{id}")
	public TestBean read(@PathParam("id") Long id){
		return repository.read(id);
	}
	
	@GET
	@Produces("application/json")
	public Collection<TestBean> readBeans(){
		return repository.list("id", 0, 999);
	}
	
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public TestBean create(TestBean testBean){
		repository.create(testBean);
		return testBean;
	}
	
	@PUT
	@Consumes("application/json")
	@Produces("application/json")
	@Path("{id}")
	public void update(TestBean testBean){
		repository.update(testBean);
	}
	
	@DELETE
	@Path("{id}")
	public void delete(@PathParam("id") Long id){
		repository.delete(id);
	}
}
