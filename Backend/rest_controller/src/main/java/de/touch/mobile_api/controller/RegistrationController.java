package de.touch.mobile_api.controller;

import java.util.Date;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.touch.mobile_api.config.Constants.AccountStatus;
import de.touch.mobile_api.config.Constants.Gender;
import de.touch.mobile_api.config.Constants.Role;
import de.touch.mobile_api.model.account.Account;
import de.touch.mobile_api.model.jwt.JwtResponse;

import org.springframework.web.bind.annotation.RequestMethod;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * RegistrationController
 */

@RestController
@EnableAutoConfiguration
@RequestMapping("/v1")
@CrossOrigin
public class RegistrationController extends MainController {

    @Autowired
    private PasswordEncoder bcryptEncoder;

    
    @GetMapping(path = "/isUsernameAvailable")
    public boolean isUsernameAvailable(@RequestParam String username) {
        return accountService.checkUsername(username);
    }

    @GetMapping(path = "/isEmailAvailable")
    public boolean isEmailAvailable(@RequestParam String email) {
        return accountService.checkEmail(email);
    }

    //
    private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationController.class);
    //


    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ResponseEntity<?> addNewUserTest(@RequestBody Account newUser) {

        LOGGER.error(newUser.toString());
        HttpHeaders responseHeaders = new HttpHeaders();

        Account account = new Account();
        account.setUsername(newUser.getUsername());
        account.setPassword(bcryptEncoder.encode(newUser.getPassword()));
        account.setEmail(newUser.getEmail());
        account.setdob(newUser.getDob());
        account.setGender(newUser.getGender());
        account.setAccountstatus(AccountStatus.Activated);
        account.setRole(Role.User);
        // save data in db
        accountService.save(account);
        // login
        final UserDetails userDetails = accountService.loadUserByUsername(account.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails, account.getRole(), account.getAccountstatus());
        LOGGER.error(token);
        LOGGER.error(new JwtResponse(token) + "");
        return ResponseEntity.ok().headers(responseHeaders).body(new JwtResponse(token));
    }

}