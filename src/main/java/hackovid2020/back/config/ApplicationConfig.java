package hackovid2020.back.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ComponentScan(basePackages = "hackovid2020.back")
@EnableWebMvc
@EnableCaching
@EnableScheduling
public class ApplicationConfig implements WebMvcConfigurer {
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addRedirectViewController("/documentation/v2/api-docs", "/v2/api-docs");
		registry.addRedirectViewController("/documentation/swagger-resources/configuration/ui",
				"/swagger-resources/configuration/ui");
		registry.addRedirectViewController("/documentation/swagger-resources/configuration/security",
				"/swagger-resources/configuration/security");
		registry.addRedirectViewController("/documentation/swagger-resources", "/swagger-resources");
		registry.addRedirectViewController("/documentation/", "/");
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// swagger UI
		registry.addResourceHandler("/documentation/**")
				.addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/documentation/swagger-ui.html**")
				.addResourceLocations("classpath:/META-INF/resources/swagger-ui.html");
		registry.addResourceHandler("/documentation/webjars/**")
				.addResourceLocations("classpath:/META-INF/resources/webjars/");
	}

}
