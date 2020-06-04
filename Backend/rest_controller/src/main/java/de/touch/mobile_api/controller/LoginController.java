package de.touch.mobile_api.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.touch.mobile_api.config.Constants.AccountStatus;
import de.touch.mobile_api.model.account.Account;
import de.touch.mobile_api.model.jwt.JwtRequest;
import de.touch.mobile_api.model.jwt.JwtResponse;

/**
 * RegistrationController
 */
@RestController
@EnableAutoConfiguration
@RequestMapping("/v1")
@CrossOrigin
public class LoginController extends MainController {

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) {

        HttpHeaders responseHeaders = new HttpHeaders();

        try {
            authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

            final Account user = accountService.loadUserData(authenticationRequest.getUsername());

            // checkStatus
            if (user.getAccountstatus().equals(AccountStatus.Blocked))
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You have been blocked");

            final UserDetails userDetails = accountService.loadUserByUsername(user.getUsername());
            final String token = jwtTokenUtil.generateToken(userDetails, user.getRole(), user.getAccountstatus());

            return ResponseEntity.ok().headers(responseHeaders).body(new JwtResponse(token));

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // irgendwas hat nicht geklappt
        return ResponseEntity.badRequest().headers(responseHeaders).body("7");
    }

}