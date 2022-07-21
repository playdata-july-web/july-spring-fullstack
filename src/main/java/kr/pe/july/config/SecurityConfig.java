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
			.antMatchers("/api/likes").hasRole("MEMBER")
			.anyRequest().permitAll();
		
		http.formLogin()
			.loginPage("/api/auth/form")
			.loginProcessingUrl("/login")
			.defaultSuccessUrl("/api/auth/login")
			.failureUrl("/api/auth/form");
		
		http.logout()
			.logoutSuccessUrl("/api/auth/logout")
			.invalidateHttpSession(true)
			.deleteCookies("JSESSIONID");
		
		http.sessionManagement()
			.maximumSessions(1)
			.maxSessionsPreventsLogin(true);
		
		http.authenticationProvider(getProvider());
		
		return http.build();
	}
}
