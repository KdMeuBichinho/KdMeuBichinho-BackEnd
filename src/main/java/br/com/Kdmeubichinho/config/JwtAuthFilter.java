package br.com.Kdmeubichinho.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.Kdmeubichinho.services.JwtService;
import br.com.Kdmeubichinho.services.PessoaService;

public class JwtAuthFilter extends OncePerRequestFilter{

	private JwtService jwtService;
	private PessoaService pessoaService;
	
	public JwtAuthFilter(JwtService jwtService, PessoaService pessoaService) {
		this.jwtService = jwtService;
		this.pessoaService = pessoaService;
	}

	@Override
	protected void doFilterInternal(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse,
			FilterChain filterChain) throws ServletException, IOException {
		
		String authorization = httpServletRequest.getHeader("Authorization");
		
		if(authorization != null && authorization.startsWith("Bearer")){
			String token = authorization.split(" ")[1];
			boolean isValid = jwtService.tokenValido(token);
			
			if(isValid) {
				String emailUsuario = jwtService.obterEmailUsuario(token);
				UserDetails usuario = pessoaService.loadUserByUsername(emailUsuario);
				UsernamePasswordAuthenticationToken user = new
						UsernamePasswordAuthenticationToken(usuario, null,
								usuario.getAuthorities());
				user.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
				SecurityContextHolder.getContext().setAuthentication(user);
			}
		}
		
		filterChain.doFilter(httpServletRequest, httpServletResponse);
	}
	
}
