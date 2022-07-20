package kr.pe.july.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import kr.pe.july.auth.AuthenticationProviderImp;
import kr.pe.july.auth.UserDetailsServiceImp;
import kr.pe.july.model.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	
	private final String URL = "/api/auth";
	
	private final UserRepository userRepository;
	
	@Bean
	public BCryptPasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public UserDetailsServiceImp getService() {
		return new UserDetailsServiceImp(userRepository);
	}
	
	@Bean
	public AuthenticationProviderImp getProvider() {
		return new AuthenticationProviderImp(getService(), getPasswordEncoder());
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf().disable();
		
		http.authorizeRequests()
			.antMatchers("/api/external").hasRole("ADMIN")
			.antMatchers(URL + "/member").hasRole("MEMBER")
			.antMatchers("/api/likes").hasRole("MEMBER")
			.anyRequest().permitAll();
		
		http.formLogin()
			.loginProcessingUrl(URL + "/login")
			.failureForwardUrl(URL + "/fail");
		
		http.logout()
			.logoutUrl(URL + "/logout")
			.logoutSuccessUrl(URL + "/home");
		
		http.sessionManagement()
			.maximumSessions(1)
			.maxSessionsPreventsLogin(true);
		
		http.authenticationProvider(getProvider());
		
		return http.build();
	}
}
