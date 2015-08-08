package jp.org.wachi.util;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Singleton;
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
public class LoggerProducer {

	@Produces
	public Logger produceLogger(final InjectionPoint injectionPoint){
		return LoggerFactory.getLogger(injectionPoint.getMember().getDeclaringClass());
	}
	
}
