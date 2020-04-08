package hackovid2020.back.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import hackovid2020.back.service.support.AuthenticationFilter;
import hackovid2020.back.service.support.AuthenticationProvider;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	private static final RequestMatcher PROTECTED_URLS = new OrRequestMatcher(
			new AntPathRequestMatcher("/api/**"));
	
	AuthenticationProvider provider;

	public SecurityConfiguration(final AuthenticationProvider authenticationProvider) {
		super();
		this.provider = authenticationProvider;
	}
	
	@Override
	protected void configure(final AuthenticationManagerBuilder auth) {
		auth.authenticationProvider(provider);
	}
	
	@Override
	public void configure(final WebSecurity webSecurity) {
		webSecurity.ignoring().antMatchers(
					"/v2/api-docs",
					"/configuration/ui,",
					"/swagger-resources/**",
					"/configuration/security",
					"/documentation/swagger-ui.html",
					"/webjars/**");
		webSecurity.ignoring().antMatchers(HttpMethod.POST, "/api/user");
	}
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and().exceptionHandling()
		.and().authenticationProvider(provider)
		.addFilterBefore(authenticationFilter(), AnonymousAuthenticationFilter.class)
		.authorizeRequests()
			.requestMatchers(PROTECTED_URLS)
			.authenticated()
		.and().csrf().disable()
		.formLogin().disable()
		.httpBasic().disable()
		.logout().disable();
	}
	
	
	AuthenticationFilter authenticationFilter() throws Exception {
		final AuthenticationFilter filter = new AuthenticationFilter(PROTECTED_URLS);
		filter.setAuthenticationManager(authenticationManager());
		return filter;
	}
	
	@Bean
	AuthenticationEntryPoint forbiddenEntryPoint() {
		return new HttpStatusEntryPoint(HttpStatus.FORBIDDEN);
	}
	
}
