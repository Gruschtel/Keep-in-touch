package de.touch.mobile_api.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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

import de.touch.mobile_api.config.Constants;
import de.touch.mobile_api.config.Constants.AccountStatus;
import de.touch.mobile_api.model.Message;
import de.touch.mobile_api.model.Request.RequestAccount;
import de.touch.mobile_api.model.Request.RequestMessage;
import de.touch.mobile_api.model.account.Account;
import de.touch.mobile_api.model.account.Friends;
import de.touch.mobile_api.model.jwt.JwtRequest;
import de.touch.mobile_api.model.jwt.JwtResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@EnableAutoConfiguration
@RequestMapping("/v1/message")
@CrossOrigin
public class MessageController extends MainController {

    //
    private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationController.class);
    //

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public ResponseEntity<?> allMessages() {

        List<Message> messageList = new ArrayList<>();
        messageList = messageService.findAll();
        List<Message> backList = new ArrayList<>();
        for (int i = messageList.size() - 1; i >= 0; i--) {
            backList.add(messageList.get(i));
        }

        return ResponseEntity.ok().headers(new HttpHeaders()).body(backList);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<?> addMessage(@RequestBody String textMessage, final HttpServletRequest request) {

        Account user = null;

        final String requestTokenHeader = request.getHeader(Constants.JWT_TOKEN_AUTHORIZATION_KEY);
        if (requestTokenHeader != null && requestTokenHeader.startsWith(Constants.JWT_TOKEN_AUTHORIZATION_VALUE)) {
            String jwtToken = requestTokenHeader.substring(Constants.JWT_TOKEN_AUTHORIZATION_VALUE.length());
            String username = jwtTokenUtil.getUsernameFromToken(jwtToken);
            user = loadAccountData(username);

            Message tempMessage = new Message(user.getPid(), textMessage, new Date());

            LOGGER.error("Ausgabe: " + tempMessage.getTextMessage());
            LOGGER.error("Hätte sein sollen: " + textMessage);

            messageService.save(tempMessage);

            return ResponseEntity.ok().headers(new HttpHeaders()).body("ok");
        }
        // 0 -> Irgendwas hat nicht funktioniert
        return ResponseEntity.badRequest().headers(new HttpHeaders()).body("0");
    }

    @RequestMapping(value = "/loadMessages", method = RequestMethod.POST)
    public ResponseEntity<?> loadMessages(final HttpServletRequest request) {

        final String requestTokenHeader = request.getHeader(Constants.JWT_TOKEN_AUTHORIZATION_KEY);
        if (requestTokenHeader != null && requestTokenHeader.startsWith(Constants.JWT_TOKEN_AUTHORIZATION_VALUE)) {

            String jwtToken = requestTokenHeader.substring(Constants.JWT_TOKEN_AUTHORIZATION_VALUE.length());
            String username = jwtTokenUtil.getUsernameFromToken(jwtToken);
            Account user = loadAccountData(username);

            List<Friends> friends = friendService.findAllFromID(user.getPid());
            List<RequestAccount> myFollowerData = new ArrayList<RequestAccount>();

            myFollowerData.add(new RequestAccount(accountService.loadUserData(user.getUsername())));

            for (Friends tempFollower : friends) {
                String follower_name = accountService.loadUsernameById(tempFollower.getIdfriend());
                Account tempAccount = loadAccountData(follower_name);
                myFollowerData.add(new RequestAccount(tempAccount));
            }

            List<RequestMessage> messageList = new ArrayList<>();
            for (RequestAccount tempAccount : myFollowerData) {
                List<Message> tempAllMessages = messageService.loadMessageFrom(tempAccount.getId());

                for (Message tempMessage : tempAllMessages) {
                    messageList.add(new RequestMessage(tempMessage, tempAccount.getUsername()));
                }
            }
            return ResponseEntity.ok().headers(new HttpHeaders()).body(getSortetByPID(messageList));
        }
        // 0 -> Irgendwas hat nicht funktioniert
        return ResponseEntity.badRequest().headers(new HttpHeaders()).body("0");
    }


    public List<RequestMessage> getSortetByPID(List<RequestMessage> liste) {
        List<RequestMessage> temp = new ArrayList<>();

        for (int i = 0; i < liste.size(); i++) {
            int index = liste.get(i).getPid();
            if (i == 0) {
                temp.add(liste.get(i));
            } else {
                boolean added = false;
                for (int j = 0; j < temp.size(); j++) {
                    if (index < temp.get(j).getPid()) {
                        temp.add(j, liste.get(i));
                        added = true;
                        break;
                    }
                }

                if (!added) {
                    temp.add(liste.get(i));
                }
            }

        }
        List<RequestMessage> backList = new ArrayList<>();
        for (int i = temp.size() - 1; i >= 0; i--) {
            backList.add(temp.get(i));
        }
        return backList;
    }
}