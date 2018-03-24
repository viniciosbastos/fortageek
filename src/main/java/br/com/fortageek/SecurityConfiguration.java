package br.com.fortageek;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.stereotype.Component;

@Configuration
@Component
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	@Autowired
	@Qualifier("datasource")
	private DataSource dataSource;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
		auth.jdbcAuthentication().passwordEncoder(NoOpPasswordEncoder.getInstance())
			.dataSource(dataSource)
			.authoritiesByUsernameQuery("select username, role from usuario where username = ?")
			.usersByUsernameQuery("select username, password, enabled from usuario where username = ?");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.headers().frameOptions().disable();
		http
			.httpBasic()
			.and()
			.authorizeRequests()
				.antMatchers("/h2/**").permitAll()
				.antMatchers("/api/login").permitAll()
				.antMatchers("/api/cidade/**").permitAll()
			.anyRequest().authenticated();
		http.csrf().disable();
	}

}

