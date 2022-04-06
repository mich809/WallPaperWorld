package com.CaridadMichael.WallPaperWorld.config;




import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;




@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	   @Autowired
		private CustomUserDetailsService userDetailsService;
	   
	   @Autowired
	   private JwtFilter jwtFilter;



	   @Autowired
	    public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
	        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	        
	    }
	   
	   

	    @Bean
	    @Override
	    public AuthenticationManager authenticationManagerBean() throws Exception {
	        return super.authenticationManagerBean();
	    }

	@Override
	protected void configure(HttpSecurity http) throws Exception {			 
				     http.cors();
			         http.csrf().disable()
	                .authorizeRequests().antMatchers( HttpMethod.POST,"api/**").permitAll()
	                .antMatchers(HttpHeaders.ALLOW).permitAll()
	             
	                .and()
	                .exceptionHandling().authenticationEntryPoint((request, response, authException) ->
	  	             response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized"))
	                .and()
	                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

			         http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

	}
	
	
	

	
	
	 @Bean
	    public static PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
	
	  @Bean
	    public CorsFilter corsFilter() {
	        UrlBasedCorsConfigurationSource source =
	            new UrlBasedCorsConfigurationSource();
	        CorsConfiguration config = new CorsConfiguration();
	        config.setAllowCredentials(true);
	        config.addAllowedOrigin("*");
	        config.addAllowedHeader("*");
	        config.addAllowedMethod("*");
	        source.registerCorsConfiguration("/**", config);
	        return new CorsFilter(source);
	    }
	

}
