package com.CaridadMichael.WallPaperWorld.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import com.CaridadMichael.WallPaperWorld.utils.JwtUtil;

import io.jsonwebtoken.JwtException;

@Component
public class JwtFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private CustomUserDetailsService customUserDetailService;

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		return new AntPathMatcher().match("api/user/register", request.getServletPath());
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		final String requestTokenHeader = request.getHeader("Authorization");

		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ") && !requestTokenHeader.isBlank()) {
			String jwt = requestTokenHeader.substring(7);

			if (jwt == null || jwt.isBlank()) {
				response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid JWT Token in Bearer Header");
			} else
				try {
					String username = jwtUtil.getUsernameFromToken(jwt);
					UserDetails userDetails = customUserDetailService.loadUserByUsername(username);
					UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
							userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
					if (SecurityContextHolder.getContext().getAuthentication() == null) {
						SecurityContextHolder.getContext().setAuthentication(authToken);
					}
				} catch (JwtException exc) {
					response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid JWT Token");
				}
		}
		filterChain.doFilter(request, response);
	}

}
