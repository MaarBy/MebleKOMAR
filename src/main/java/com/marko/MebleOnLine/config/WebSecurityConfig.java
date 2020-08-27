package com.marko.MebleOnLine.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public UserDetailsService userDetailsService() {
		return new UserDetailsServiceImpl();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) {
		auth.authenticationProvider(authenticationProvider());
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http
		.authorizeRequests()
//		.antMatchers("/admin").hasRole("ADMIN")
//		.antMatchers("/produkty").access("hasRole('USER')")
		.antMatchers("/produkty").hasRole("ADMIN")
		.antMatchers("/uzytkownicy").hasRole("ADMIN")
		.antMatchers("/dodajProdukt").hasRole("ADMIN")
		.antMatchers("/dodajUzytkownik").hasRole("ADMIN")
		.antMatchers("/usunProdukt").hasRole("ADMIN")
		.antMatchers("/usunUzytkownik").hasRole("ADMIN")
		.antMatchers("/edytujProdukt/**").hasRole("ADMIN")
		.antMatchers("/edytujUzytkownik/**").hasRole("ADMIN")
		.antMatchers("/dodajDoKoszyka").hasRole("USER")
		.antMatchers("/koszyk").hasRole("USER")
		.antMatchers("/usunZKoszyka").hasRole("USER")
		.anyRequest().permitAll()
//		.anyRequest().authenticated()
		.and().formLogin().loginPage("/logowanie").usernameParameter("email").passwordParameter("haslo").permitAll()
		.and().logout().logoutUrl("/wyloguj").permitAll()
		.and().exceptionHandling().accessDeniedPage("/403");
		
	http
		.formLogin()
		   .loginPage("/logowanie")
		   .defaultSuccessUrl("/default", true)
		   .permitAll();
	
	}
}