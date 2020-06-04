package de.touch.mobile_api.model.Request;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import de.touch.mobile_api.model.Message;

public class RequestMessage implements Serializable {

    /**
     *
     */
    // need default constructor for JSON Parsing
    // Hibernate requires no-args constructor
    public RequestMessage() {

    }

    public RequestMessage(Message message, String username) {
        this.setPid(message.getPid());
        this.setIdaccount(message.getIdaccount());
        this.setMessage(message.getTextMessage());

        String pattern = "dd.MM.yyyy - HH:mm";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        this.setDate(simpleDateFormat.format(message.getDate()));
        this.setUsername(username);
    }

    private int pid;

    private int idaccount;

    private String username;

    private String textMessage;

    private String date;

    //
    // Getter & Setter
    //
    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getIdaccount() {
        return idaccount;
    }

    public void setIdaccount(int idaccount) {
        this.idaccount = idaccount;
    }

    public String getMessage() {
        return textMessage;
    }

    public void setMessage(String textMessage) {
        this.textMessage = textMessage;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}