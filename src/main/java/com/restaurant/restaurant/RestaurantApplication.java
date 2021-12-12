package com.restaurant.restaurant;

import com.restaurant.restaurant.security.JWTAuthorizationFilter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class RestaurantApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestaurantApplication.class, args);
	}

	@EnableWebSecurity
	@Configuration
	class WebSecurityConfig extends WebSecurityConfigurerAdapter {

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.csrf().disable()
					.addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
					.authorizeRequests()
					.antMatchers(HttpMethod.POST, "/login").permitAll()
					.antMatchers(HttpMethod.POST, "/user").permitAll()
					.antMatchers(HttpMethod.GET, "/food/day").permitAll()
					.antMatchers(HttpMethod.GET, "/food/week").permitAll()
					.antMatchers(HttpMethod.GET, "/imageFood/id/*").permitAll()
					.antMatchers(
							HttpMethod.GET,
							"/",
							"/v2/api-docs", // swagger
							"/webjars/**", // swagger-ui webjars
							"/swagger-resources/**", // swagger-ui resources
							"/configuration/**", // swagger configuration
							"/*.html",
							"/favicon.ico",
							"/**/*.html",
							"/**/*.css",
							"/**/*.js")
					.permitAll()
					.antMatchers(HttpMethod.GET, "/swagger-ui.html").permitAll()
					.anyRequest().authenticated();
		}
	}
}
