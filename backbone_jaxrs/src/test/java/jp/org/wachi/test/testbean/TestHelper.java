package jp.org.wachi.test.testbean;

import java.io.File;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;

public class TestHelper {

	public static WebArchive createDeployment() {
		return ShrinkWrap.create(WebArchive.class,"test.war")
				.addPackage("jp.org.wachi.testbean")
				.addPackage("jp.org.wachi.util")
				.addAsResource(new File("src/main/resources/META-INF/persistence.xml"), "/META-INF/persistence.xml")
				.addAsWebInfResource(new File("src/main/webapp/WEB-INF/beans.xml"),"beans.xml");
	}
	
	public static WebArchive createTestDeployment(){
		return ShrinkWrap.create(WebArchive.class,"test.war")
				.addPackage("jp.org.wachi.testbean")
				.addPackage("jp.org.wachi.util")
				.addAsResource(new File("src/test/resources/META-INF/test-persistence.xml"), "/META-INF/persistence.xml")
				.addAsWebInfResource(new File("src/main/webapp/WEB-INF/beans.xml"),"beans.xml")
                .addAsWebInfResource("test-ds.xml");

	}
	public static void main(String[] args){
		WebArchive archive = createDeployment();
		System.out.println(archive.toString(true));
		WebArchive testArchive = createDeployment();
		System.out.println(testArchive.toString(true));
		
	}
}
