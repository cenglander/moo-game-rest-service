package moo.restservice;
import org.slf4j.*;
import org.springframework.beans.factory.InjectionPoint;
import org.springframework.context.annotation.*;
import org.springframework.core.MethodParameter;

import static java.util.Optional.*;

import java.lang.reflect.Field;

@Configuration
public class LoggingConfiguration {
	
 @Bean
 @Scope("prototype")
 public Logger logger(final InjectionPoint ip) {
	
	if (ip.getField() != null ) 
		return LoggerFactory.getLogger(ip.getField().getDeclaringClass());
	if (ip.getMethodParameter() != null ) 
		return LoggerFactory.getLogger(ip.getMethodParameter().getDeclaringClass());
	throw new IllegalArgumentException("Loggingconfig: could not create logger");
 }
}