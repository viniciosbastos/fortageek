package br.com.fortageek;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class FortaGeekApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(FortaGeekApiApplication.class, args);
	}

	@Bean(value = "datasource")
	public DataSource dataSource() {
		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
		driverManagerDataSource.setDriverClassName("org.h2.Driver");
	    driverManagerDataSource.setUrl("jdbc:h2:file:./database/fortageek");
	    driverManagerDataSource.setUsername("sa");
	    driverManagerDataSource.setPassword("");
		return driverManagerDataSource;
	}
	
//	@Bean
//	public WebMvcConfigurer corsConfigurer() {
//		
//		return new WebMvcConfigurerAdapter() {
//	
//			@Override
//			public void addCorsMappings(CorsRegistry registry) {
//				registry.addMapping("/**");
//				
//			}
//		};
//	}
}
