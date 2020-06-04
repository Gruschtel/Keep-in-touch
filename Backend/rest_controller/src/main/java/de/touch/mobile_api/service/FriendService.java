package de.touch.mobile_api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import de.touch.mobile_api.model.account.Account;
import de.touch.mobile_api.model.account.Friends;
import de.touch.mobile_api.repository.FriendRepository;

@Service
public class FriendService {

    @Autowired
    private FriendRepository friendRepository ;

    public void save(Friends n) {
        friendRepository.save(n);
    }

    /**
     * 
     * @return
     */
    public List<Friends> findAll() {
        Iterable<Friends> it = friendRepository.findAll();
        List<Friends> follower = new ArrayList<Friends>();
        it.forEach(e -> follower.add(e));
        return follower;
    }

    public List<Friends> findAllFromID(int id) {
        //return null;
        return friendRepository.findByIdaccount(id);
        /*
         * Iterable<Friends> it = friendRepository.findAllFollowersById(id);
         * List<Friends> follower = new ArrayList<Friends>(); it.forEach(e ->
         * follower.add(e)); return follower;
         */
    }
    public void deleteById(Friends valueForDelete) {
        friendRepository.delete(valueForDelete);
    }

}