package de.touch.mobile_api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import de.touch.mobile_api.model.account.Account;
import de.touch.mobile_api.repository.AccountRepository;

@Service
public class AccountService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    /**
     * 
     * @return
     */
    public List<Account> findAll() {
        Iterable<Account> it = accountRepository.findAll();
        List<Account> users = new ArrayList<Account>();
        it.forEach(e -> users.add(e));
        return users;
    }

    public Long count() {
        return accountRepository.count();
    }

    public void deleteById(Long userId) {
        accountRepository.deleteById(userId);
    }

    public void save(Account n) {
        accountRepository.save(n);
    }

    public boolean checkUsername(String username) {
        String user = accountRepository.findUsernameByUsername(username);
        if (user == null) {
            return true;
        }
        return false;
    }

    public boolean checkEmail(String email) {
        String user = accountRepository.findEmailByEmail(email);
        if (user == null) {
            return true;
        }
        return false;
    }

    public String loadUsernameById(int pid) {
        return accountRepository.findUsernameById(pid);
    }

    /**
     * 
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    public Account loadUserData(String username) throws UsernameNotFoundException {
        Account user = accountRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return user;
    }

    /**
     * 
     * @param username
     * 
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account user = accountRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                new ArrayList<>());
    }

    public void updateUser(Account account) {
        accountRepository.save(account);
    }

}