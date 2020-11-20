package br.com.Kdmeubichinho.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.Kdmeubichinho.services.JwtService;
import br.com.Kdmeubichinho.services.PessoaServiceImpl;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private PessoaServiceImpl pessoaService;
	@Autowired
	private JwtService jwtService;
	
	private static final String[] AUTH_WHITELIST = {
            // -- swagger ui
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            "/boasvindas",
            "/anuncio/busca",
            "/**"
            // other public endpoints of your API may be appended to this array
    };
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();	
	}
	
	@Bean
	public OncePerRequestFilter jwtFilter(){
		return new JwtAuthFilter(jwtService, pessoaService);
	}
	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.userDetailsService(pessoaService)
			.passwordEncoder(passwordEncoder());
	}
	
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and()
			.csrf().disable()
			.authorizeRequests()
			.antMatchers(HttpMethod.POST, "/pessoa/**")
			.permitAll()
			.antMatchers(AUTH_WHITELIST)
			.permitAll()
			.anyRequest().authenticated()
			.and()
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);
	}
	
}