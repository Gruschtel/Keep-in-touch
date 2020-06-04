package de.touch.mobile_api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import de.touch.mobile_api.model.Message;
import de.touch.mobile_api.model.account.Account;
import de.touch.mobile_api.repository.AccountRepository;
import de.touch.mobile_api.repository.MessageRepository;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public void save(Message message) {
        messageRepository.save(message);
    }

    public List<Message> loadMessageFrom(int idaccount) {
        return messageRepository.findByIdaccount(idaccount);
    }

    /**
     * 
     * @return
     */
    public List<Message> findAll() {
        Iterable<Message> it = messageRepository.findAll();
        List<Message> users = new ArrayList<Message>();
        it.forEach(e -> users.add(e));
        return users;
    }

}