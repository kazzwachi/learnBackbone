package jp.org.wachi.testbean;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
	TestBeanRepository repository;
	
	@Inject
	Logger logger;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		if(null == repository){
			logger.info("repository is null");
			out.println("repository is nulll");
		}else{
			logger.info("repository is not null");
			out.println("repository is not nulll");
		}
		if(null == repository.getDao()){
			logger.info("dao is null");
			out.println("dao is null");
		}else{
			logger.info("dao is not null");
			out.println("dao is not null");
		}
		if(null == repository.getDao().getEntityClass()){
			logger.info("entityClass is null");
			out.println("entityClass is null");
		}else{
			logger.info("entityClass is not null");
			out.println("entityClass is not null");
		}
		TestBean testBean = new TestBean();
		testBean.setKey("99999999");
		testBean.setValue("Value9999");
		repository.create(testBean);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
