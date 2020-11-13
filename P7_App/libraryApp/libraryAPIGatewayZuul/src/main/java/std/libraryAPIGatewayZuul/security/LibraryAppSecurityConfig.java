package std.libraryAPIGatewayZuul.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import std.libraryAPIGatewayZuul.security.encoder.LibraryEncoder;
import std.libraryAPIGatewayZuul.security.service.LibraryUserDetailsService;


@Configuration
@EnableWebSecurity
public class LibraryAppSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private LibraryUserDetailsService user;

	@Autowired
	private LibraryEncoder encoder;




	@Bean
	public LogSuccessCustomHandler authSuccessHandler() {
		return new LogSuccessCustomHandler();
	}









	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors();
		 http.csrf().disable();
		 http.authorizeRequests().antMatchers("/", "/login", "/logout","/letsGo").permitAll();
		 http.sessionManagement()
	        .sessionCreationPolicy(SessionCreationPolicy.ALWAYS);



		http
		.authorizeRequests()
			.antMatchers("/booksList").hasAnyAuthority("TYPE_admin","TYPE_user")
			.and()
			.headers().frameOptions().sameOrigin()
			.and()
			.formLogin().loginPage("/login")
			.and()
			.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/");
	}


	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(user).passwordEncoder(encoder.bCryptEncoder());
    }

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowCredentials(true);
		configuration.addAllowedOrigin("http://localhost:9005");
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}




}
