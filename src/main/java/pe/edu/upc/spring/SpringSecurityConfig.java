package pe.edu.upc.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import pe.edu.upc.spring.auth.handler.LoginSucessHandler;
import pe.edu.upc.spring.serviceimpl.JpaUserDetailsService;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private JpaUserDetailsService userDetailsService;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@Autowired
	private LoginSucessHandler sucessHandler;
	
	protected void configure(HttpSecurity http) throws Exception {
		try {
			http.authorizeRequests()
			.antMatchers("/race/**").access("hasRole('ROLE_ADMIN')")
			.antMatchers("/pet/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
			.and()
			.formLogin()
			.successHandler(sucessHandler).loginPage("/login")
			.loginProcessingUrl("/login")
			.defaultSuccessUrl("/race/bienvenido")
			.permitAll()
			.and()
			.logout()
			.logoutSuccessUrl("/login")
			.permitAll()
			.and()
			.exceptionHandling().accessDeniedPage("/error_403");
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void configureGlobal(AuthenticationManagerBuilder build) throws Exception {
		build.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	}
}
