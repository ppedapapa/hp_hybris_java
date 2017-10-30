package hello;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

/**
 * @SpringBootApplication is a convenience annotation that adds all of the
 *                        following:
 * 
 * @Configuration tags the class as a source of bean definitions for the
 *                application context.
 * @EnableAutoConfiguration tells Spring Boot to start adding beans based on
 *                          classpath settings, other beans, and various
 *                          property settings. Normally you would
 *                          add @EnableWebMvc for a Spring MVC app, but Spring
 *                          Boot adds it automatically when it sees
 *                          spring-webmvc on the classpath. This flags the
 *                          application as a web application and activates key
 *                          behaviors such as setting up a DispatcherServlet.
 * @ComponentScan tells Spring to look for other components, configurations, and
 *                services in the hello package, allowing it to find the
 *                controllers.
 * 
 * 
 * 
 * @author Elli Albek
 */

@SpringBootApplication

@ComponentScan(lazyInit = true, basePackages = "hello")
public class Application extends SpringBootServletInitializer {
	// @Override
	// protected SpringApplicationBuilder configure(SpringApplicationBuilder
	// application) {
	// //return application.sources(Application.class);
	// }

	// @Component
	// @Primary
	// public class CustomObjectMapper extends ObjectMapper {
	// public CustomObjectMapper() {
	// setSerializationInclusion(JsonInclude.Include.NON_NULL);
	// configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
	// configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
	// configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	// enable(SerializationFeature.INDENT_OUTPUT);
	// }
	// }
}