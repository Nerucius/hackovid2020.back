package hackovid2020.back.config;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicate;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	 @Bean
	    public Docket api() { 
	       return new Docket(DocumentationType.SWAGGER_2)
	    	  .select()
	          .paths(paths()) 
	          .apis(RequestHandlerSelectors.any())              
	          .paths(PathSelectors.any())
	          .build()
	          .apiInfo(apiInfo());
	    }
	 
	@SuppressWarnings("unchecked")
	private Predicate<String> paths() {
	      return or(
	          regex("/api.*")
	        );
	    }
	
	 private ApiInfo apiInfo() {
	    	return new ApiInfoBuilder()
	    			.title("Hackovid 2020")
	    			.description("API documentation for the Hackovid 2020 application backend")
	    			.termsOfServiceUrl("**TBD**")
	    			.version("1.0.0")
	    			.license("MIT")
	    			.licenseUrl("https://opensource.org/licenses/MIT")
	    			.build();
	    }
}
