package de.touch.mobile_api.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table(name = "Message")
public class Message implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 6529765931003819893L;

    /**
     *
     */
    // need default constructor for JSON Parsing
    // Hibernate requires no-args constructor
    public Message() {

    }

    public Message(int idaccount, String textMessage, Date date) {
        this.setIdaccount(idaccount);
        this.setTextMessage(textMessage);
        this.setDate(date);
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pid;

    @Column(name = "idaccount")
    private int idaccount;

    @Column(name = "textMessage")
    private String textMessage;

    @Column(name = "date")
    private Date date;

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

    public String getTextMessage() {
        return textMessage;
    }

    public void setTextMessage(String textMessage) {
        this.textMessage = textMessage;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}