package jp.org.wachi.util;

import java.io.IOException;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
@Provider
public class CORSFilter implements ContainerResponseFilter {

	@Inject
	Logger logger;
	
	@Override
	public void filter(ContainerRequestContext requestContext,
			ContainerResponseContext responseContext) throws IOException {
		responseContext.getHeaders().add("Access-Control-Allow-Origin", "*" );
		responseContext.getHeaders().add("Access-Control-Allow-Credentials", "true" );
		responseContext.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT" );
		responseContext.getHeaders().add("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With" );
	}

}
