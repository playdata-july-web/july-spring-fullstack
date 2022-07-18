package kr.pe.july.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import kr.pe.july.auth.AuthenticationProviderImp;
import kr.pe.july.auth.UserDetailsServiceImp;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	private final String API = "api";
	
	@Bean
	public AuthenticationProviderImp getProvider() {
		return new AuthenticationProviderImp();
	}
	
	@Bean
	public UserDetailsServiceImp getService() {
		return new UserDetailsServiceImp();
	}
	
	@Bean
	public BCryptPasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf().disable();
		
		http.authorizeRequests()
			.antMatchers(API + "/admin").hasRole("ADMIN")
			.antMatchers(API+ "/member").hasRole("MEMBER")
			.anyRequest().permitAll();
		
		http.formLogin()
			.loginProcessingUrl(API + "/login");
		
		http.logout()
			.logoutUrl(API + "logout");
		
		http.sessionManagement()
			.maximumSessions(1)
			.maxSessionsPreventsLogin(true);
		
		http.authenticationProvider(getProvider());
		
		return http.build();
	}
}
