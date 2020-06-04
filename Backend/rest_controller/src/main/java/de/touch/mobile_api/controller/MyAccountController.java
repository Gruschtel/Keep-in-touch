package de.touch.mobile_api.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.touch.mobile_api.config.Constants;
import de.touch.mobile_api.config.jwt.JwtTokenUtil;
import de.touch.mobile_api.model.Request.RequestAccount;
import de.touch.mobile_api.model.account.Account;
import de.touch.mobile_api.model.account.Friends;
import de.touch.mobile_api.model.jwt.JwtRequest;
import de.touch.mobile_api.model.jwt.JwtResponse;
import de.touch.mobile_api.service.AccountService;
import io.jsonwebtoken.ExpiredJwtException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@EnableAutoConfiguration
@RequestMapping("/v1/user")
@CrossOrigin
public class MyAccountController extends MainController {

    //
    private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationController.class);
    //
    
    @Autowired
    private PasswordEncoder bcryptEncoder;

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public ResponseEntity<?> allUsers(final HttpServletRequest request) {
        // if (!hasPermission(request, userList))
        // return ResponseEntity.badRequest().body("No Permission");

        return ResponseEntity.ok().headers(new HttpHeaders()).body(accountService.findAll());
    }

    @CrossOrigin
    @RequestMapping(value = "/getUserData", method = RequestMethod.POST)
    public ResponseEntity<?> loadUserData(final HttpServletRequest request) {

        HttpHeaders responseHeaders = new HttpHeaders();

        final String requestTokenHeader = request.getHeader(Constants.JWT_TOKEN_AUTHORIZATION_KEY);
        String username = "";

        if (requestTokenHeader != null && requestTokenHeader.startsWith(Constants.JWT_TOKEN_AUTHORIZATION_VALUE)) {
            String jwtToken = requestTokenHeader.substring(Constants.JWT_TOKEN_AUTHORIZATION_VALUE.length());
            username = jwtTokenUtil.getUsernameFromToken(jwtToken);
        }

        final Account user = loadAccountData(username);
        // if(user == null)
        // TODO: Error Message

        return ResponseEntity.ok().headers(responseHeaders).body(new RequestAccount(user));
    }

    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public ResponseEntity<?> updateUser(final HttpServletRequest request, @RequestBody Account new_account) {

        HttpHeaders responseHeaders = new HttpHeaders();

        final String requestTokenHeader = request.getHeader(Constants.JWT_TOKEN_AUTHORIZATION_KEY);
        String username = "";

        if (requestTokenHeader != null && requestTokenHeader.startsWith(Constants.JWT_TOKEN_AUTHORIZATION_VALUE)) {
            String jwtToken = requestTokenHeader.substring(Constants.JWT_TOKEN_AUTHORIZATION_VALUE.length());
            username = jwtTokenUtil.getUsernameFromToken(jwtToken);

            Account tempAccount = accountService.loadUserData(username);
            new_account.setPid(tempAccount.getPid());
            LOGGER.error(new_account.getPid() + "");

            tempAccount.update(new_account);
            tempAccount.setPassword(bcryptEncoder.encode(tempAccount.getPassword()));

            accountService.updateUser(tempAccount);



            final UserDetails userDetails = accountService.loadUserByUsername(tempAccount.getUsername());
            final String token = jwtTokenUtil.generateToken(userDetails, tempAccount.getRole(), tempAccount.getAccountstatus());
            
            return ResponseEntity.ok().headers(responseHeaders).body(new JwtResponse(token));
        }

        // irgendwas hat nicht geklappt
        return ResponseEntity.badRequest().headers(responseHeaders).body("7");

    }

}