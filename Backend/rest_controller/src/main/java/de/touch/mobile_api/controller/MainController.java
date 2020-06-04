package de.touch.mobile_api.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import de.touch.mobile_api.config.Constants;
import de.touch.mobile_api.config.Constants.Role;
import de.touch.mobile_api.config.jwt.JwtTokenUtil;
import de.touch.mobile_api.model.account.Account;
import de.touch.mobile_api.model.jwt.JwtRequest;
import de.touch.mobile_api.service.AccountService;
import de.touch.mobile_api.service.FriendService;
import de.touch.mobile_api.service.MessageService;
import io.jsonwebtoken.ExpiredJwtException;

/**
 * MainController
 */
public class MainController {

    @Autowired
    protected JwtTokenUtil jwtTokenUtil;

    @Autowired
    protected AuthenticationManager authenticationManager;

    @Autowired
    protected AccountService accountService;

    @Autowired
    protected FriendService friendService;

    @Autowired
    protected MessageService messageService;

    //
    //
    //

    // List of roles for allowed access
    protected final List<Role> adminList = new ArrayList<>(Arrays.asList(Role.Admin));
    // protected final List<Role> moderatorList = new
    // ArrayList<>(Arrays.asList(Role.Admin, Role.Moderator));
    protected final List<Role> userList = new ArrayList<>(Arrays.asList(Role.Admin, Role.User));

    //
    //
    //

    protected boolean hasPermission(HttpServletRequest request, List<Role> roleList) throws BadCredentialsException {
        final String requestTokenHeader = request.getHeader(Constants.JWT_TOKEN_AUTHORIZATION_KEY);

        Role role = null;
        String jwtToken = null;
        if (requestTokenHeader != null && requestTokenHeader.startsWith(Constants.JWT_TOKEN_AUTHORIZATION_VALUE)) {
            jwtToken = requestTokenHeader.substring(Constants.JWT_TOKEN_AUTHORIZATION_VALUE.length());
            try {
                role = jwtTokenUtil.getRoleFromToken(jwtToken);
                for (Role checkRole : roleList) {
                    if (checkRole.equals(role))
                        return true;
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Unable to get JWT Token");
            }
        }
        throw new BadCredentialsException("No permission");
    }

    //
    protected Account loadAccountData(String username) throws UsernameNotFoundException {

        // final String requestTokenHeader =
        // request.getHeader(Constants.JWT_TOKEN_AUTHORIZATION_KEY);

        // JWT Token is in the form "Bearer token". Remove Bearer word and get
        // only the Token
        try {
            return accountService.loadUserData(username);

        } catch (IllegalArgumentException e) {
            System.out.println("Unable to get JWT Token");
        } catch (ExpiredJwtException e) {
            System.out.println("JWT Token has expired");
        }
        throw new UsernameNotFoundException("User not found: " + username);
    }

    protected void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}