package de.touch.mobile_api.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.touch.mobile_api.config.Constants;
import de.touch.mobile_api.model.Request.RequestAccount;
import de.touch.mobile_api.model.account.Account;
import de.touch.mobile_api.model.account.Friends;

@RestController
@EnableAutoConfiguration
@RequestMapping("/v1/follower")
@CrossOrigin
public class FriendController extends MainController {

    //
    private static final Logger LOGGER = LoggerFactory.getLogger(FriendController.class);
    //

    //
    //
    //
    @RequestMapping(value = "/getFollowerData", method = RequestMethod.POST)
    public ResponseEntity<?> searchUser(@RequestBody String friend_name, final HttpServletRequest request) {

        final String requestTokenHeader = request.getHeader(Constants.JWT_TOKEN_AUTHORIZATION_KEY);

        if (requestTokenHeader != null && requestTokenHeader.startsWith(Constants.JWT_TOKEN_AUTHORIZATION_VALUE)) {
            String jwtToken = requestTokenHeader.substring(Constants.JWT_TOKEN_AUTHORIZATION_VALUE.length());
            String username = jwtTokenUtil.getUsernameFromToken(jwtToken);

            if (username.equals(friend_name.substring(1, friend_name.length() - 1))) {
                // 1 -> Username darf nicht gleich freundesname sein
                return ResponseEntity.badRequest().headers(new HttpHeaders()).body("3");
            } else {

                if (!accountService.checkUsername(friend_name.substring(1, friend_name.length() - 1))) {
                    final Account user = loadAccountData(friend_name.substring(1, friend_name.length() - 1));
                    return ResponseEntity.ok().headers(new HttpHeaders()).body(new RequestAccount(user));
                } else {
                    // 2▼ -> Freund konnte nicht gefunden werden
                    return ResponseEntity.badRequest().headers(new HttpHeaders()).body("2");
                }
            }
        } else {
            // 0 -> Nutzer konnte nicht gefunden werden
            return ResponseEntity.badRequest().headers(new HttpHeaders()).body("1");
        }
    }

    //
    //
    //
    @RequestMapping(value = "/addFollower", method = RequestMethod.POST)
    public ResponseEntity<String> addNewFollower(final HttpServletRequest request, @RequestBody Account follower) {

        LOGGER.error(follower.toString());

        Account user = null;

        final String requestTokenHeader = request.getHeader(Constants.JWT_TOKEN_AUTHORIZATION_KEY);
        if (requestTokenHeader != null && requestTokenHeader.startsWith(Constants.JWT_TOKEN_AUTHORIZATION_VALUE)) {
            String jwtToken = requestTokenHeader.substring(Constants.JWT_TOKEN_AUTHORIZATION_VALUE.length());
            String username = jwtTokenUtil.getUsernameFromToken(jwtToken);
            user = loadAccountData(username);

            follower = loadAccountData(follower.getUsername());

            List<Friends> followerList = friendService.findAllFromID(user.getPid());
            for (Friends tempFollower : followerList) {
                if (tempFollower.getIdfriend() == follower.getPid()) {
                    // 4 -> Nutzer schon befreundet
                    return ResponseEntity.badRequest().headers(new HttpHeaders()).body("4");
                }
            }

            Friends tempFriends = new Friends(user.getPid(), follower.getPid());

            friendService.save(tempFriends);
            return ResponseEntity.ok().headers(new HttpHeaders()).body("ok");

        } else {
            // 0 -> Nutzer konnte nicht gefunden werden
            return ResponseEntity.badRequest().headers(new HttpHeaders()).body("1");
        }

    }

    //
    //
    //
    @RequestMapping(value = "/findAllFollower", method = RequestMethod.POST)
    public ResponseEntity<?> findAllFollower() {
        return ResponseEntity.ok().headers(new HttpHeaders()).body(friendService.findAll());
    }

    //
    //
    //
    @RequestMapping(value = "/findAllFollowerDataByID", method = RequestMethod.POST)
    public ResponseEntity<?> findAllFollowerDataByID(@RequestBody int param) {
        
        List<Friends> follower = friendService.findAllFromID(param);

        List<RequestAccount> accounts = new ArrayList<RequestAccount>();

        for (Friends tempFollower : follower) {

            String follower_name = accountService.loadUsernameById(tempFollower.getIdfriend());
            final Account user = loadAccountData(follower_name);
            accounts.add(new RequestAccount(user));

        }

        // return ResponseEntity.ok().headers(new HttpHeaders()).body("OK");
        return ResponseEntity.ok().headers(new HttpHeaders()).body(accounts);
    }

    //
    //
    //
    @RequestMapping(value = "/findAllFollowerByID", method = RequestMethod.POST)
    public ResponseEntity<?> findFollowerByID(@RequestBody int param) {
        return ResponseEntity.ok().headers(new HttpHeaders()).body(friendService.findAllFromID(param));
    }

    @RequestMapping(value = "/deleteFollowerById", method = RequestMethod.POST)
    public ResponseEntity<?> deletebyID(@RequestBody int deleteFollower, final HttpServletRequest request) {

        Account user = null;

        final String requestTokenHeader = request.getHeader(Constants.JWT_TOKEN_AUTHORIZATION_KEY);
        if (requestTokenHeader != null && requestTokenHeader.startsWith(Constants.JWT_TOKEN_AUTHORIZATION_VALUE)) {
            String jwtToken = requestTokenHeader.substring(Constants.JWT_TOKEN_AUTHORIZATION_VALUE.length());
            String username = jwtTokenUtil.getUsernameFromToken(jwtToken);
            user = loadAccountData(username);

            List<Friends> follower = friendService.findAllFromID(user.getPid());
            LOGGER.error("Löschender Follower: " + deleteFollower);

            for (Friends tempFollower : follower) {
                LOGGER.error(tempFollower.getIdfriend() + "");
                if(tempFollower.getIdfriend() == deleteFollower){
                    LOGGER.error("PID: " + tempFollower.getPid());
                    friendService.deleteById(tempFollower);
                    return ResponseEntity.ok().headers(new HttpHeaders()).body(tempFollower.getIdfriend() + " wurde gelöscht");
                }
            }

        }
        // 5 -> Daten konnten nicht gelöscht werden
        return ResponseEntity.badRequest().headers(new HttpHeaders()).body("5");

    }

}