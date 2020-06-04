package de.touch.mobile_api.config.jwt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import de.touch.mobile_api.config.Constants;
import de.touch.mobile_api.config.Constants.AccountStatus;
import de.touch.mobile_api.model.account.Account;
import de.touch.mobile_api.service.AccountService;
import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	@Autowired
	private AccountService accountService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {

		final String requestTokenHeader = request.getHeader(Constants.JWT_TOKEN_AUTHORIZATION_KEY);

		logger.error("");
		logger.error("");
		/*
		 * Enumeration<String> headerNames = request.getHeaderNames(); if (headerNames
		 * != null) { while (headerNames.hasMoreElements()) { logger.error("Header: " +
		 * request.getHeader(headerNames.nextElement())); } }
		 * logger.error("---------------------------------------");
		 * logger.error(request.getHeader("content-type"));
		 * logger.error(request.getHeader("Authorization"));
		 * logger.error("---------------------------------------");
		 * logger.error(request.getReader().lines().collect(Collectors.joining(System.
		 * lineSeparator())));
		 * 
		 * logger.error("---------------------------------------");
		 */
		String username = null;
		String jwtToken = null;

		// JWT Token is in the form "Bearer token". Remove Bearer word and get
		// only the Token
		if (requestTokenHeader != null && requestTokenHeader.startsWith(Constants.JWT_TOKEN_AUTHORIZATION_VALUE)) {
			jwtToken = requestTokenHeader.substring(Constants.JWT_TOKEN_AUTHORIZATION_VALUE.length());
			try {
				username = jwtTokenUtil.getUsernameFromToken(jwtToken);

			} catch (IllegalArgumentException e) {
				logger.error("E1 Unable to get JWT Token");
			} catch (ExpiredJwtException e) {
				logger.error("E2 JWT Token has expired");
			}
		} else {
			logger.warn("E3 JWT Token does not begin with Bearer String: " + jwtToken + " request: " + request);
		}

		// Once we get the token validate it.
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

			final Account user = accountService.loadUserData(username);

			// checkStatus
			if (user.getAccountstatus().equals(AccountStatus.Blocked))
				response.sendError(HttpServletResponse.SC_FORBIDDEN, "E6 You have been blocked");

			final UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getUsername(),
					user.getPassword(), new ArrayList<>());

			// if token is valid configure Spring Security to manually set
			// authentication
			if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {

				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());

				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				// After setting the Authentication in the context, we specify
				// that the current user is authenticated. So it passes the
				// Spring Security Configurations successfully.
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			} else {
				response.sendError(HttpServletResponse.SC_EXPECTATION_FAILED,
						"E4 Something is wrong with Token " + jwtToken + " Header: " + requestTokenHeader);
			}
		} else {
			logger.error("E5 Something is wrong with Token " + jwtToken + " request: " + request);
		}
		chain.doFilter(request, response);
	}

}