package musicfy.config;


import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import musicfy.security.JwtAuthenticationFilter;
import musicfy.service.AppUserDetailService;

@Configuration
public class SecurityConfig {
	
	 private final AppUserDetailService appUserDetailService;
	 private final JwtAuthenticationFilter jwtAuthenticationFilter;
	 
	 public SecurityConfig(AppUserDetailService appUserDetailService, JwtAuthenticationFilter jwtAuthenticationFilter) {
		super();
		this.appUserDetailService = appUserDetailService;
		this.jwtAuthenticationFilter = jwtAuthenticationFilter;
	}

		@Bean
	    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	        http.cors(Customizer.withDefaults())
	                .csrf(AbstractHttpConfigurer::disable)
	                .authorizeHttpRequests(auth -> auth.requestMatchers("/api/health", "/api/auth/login", "/api/auth/register").permitAll()
	                        .requestMatchers(HttpMethod.GET, "/api/albums","api/songs").hasAnyRole("USER", "ADMIN")
	                        .anyRequest().hasRole("ADMIN"))
	                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	        .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
	        return http.build();
	    }

	    @Bean
	    public CorsFilter corsFilter() {
	        return new CorsFilter(corsConfigurationSource());
	    }

	    private UrlBasedCorsConfigurationSource corsConfigurationSource() {

	        CorsConfiguration config = new CorsConfiguration();

	        config.addAllowedOriginPattern("*");
	        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
	        config.setAllowedHeaders(List.of("Authorization", "Content-Type", "Accept"));
	        config.setAllowCredentials(true);

	        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	        source.registerCorsConfiguration("/**", config);
	        return source;
	    }

	    @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }

//	    @Bean
//	    public AuthenticationManager authenticationManager() throws Exception {
//	        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//	        authProvider.setUserDetailsService(appUserDetailService);
//	        authProvider.setPasswordEncoder(passwordEncoder());
//	        return new ProviderManager(authProvider);
//	    }
	    
	    @Bean
	    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
	        return configuration.getAuthenticationManager();
	    }


}
