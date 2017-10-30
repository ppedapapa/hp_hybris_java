package hello;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.google.common.base.Predicate;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Configuration of Swagger and SwingFox rest api docs.
 * 
 * @author Elli Albek
 */
@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class ApiDocConfiguration {

	@Bean
	public Docket customDocket() {
		return new Docket(DocumentationType.SWAGGER_2)
				// PUBLIC API
				.groupName("public-api").select().paths(publicPaths()).build()
		// Internal API

		;
	}

	private Predicate<String> publicPaths() {
		return Paths.paths("/greeting");
	}

	@Bean
	public UiConfiguration uiConfig() {
		return new UiConfiguration(null,null, null, null, null, true, true, null); 
	}
}