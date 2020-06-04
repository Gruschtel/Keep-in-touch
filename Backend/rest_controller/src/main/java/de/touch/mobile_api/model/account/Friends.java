/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.touch.mobile_api.model.account;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 *
 * @author Eric G. Werner <gruschtelapps@gmail.com>
 */
@Entity
@Table(name = "Friends")
public class Friends implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 3604779760306020530L;

    /**
     *
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pid;

    @Column(name = "idaccount")
    private int idaccount;

    @Column(name = "idfriend")
    private int idfriend;

    //
    //
    //

    // need default constructor for JSON Parsing
    // Hibernate requires no-args constructor
    public Friends() {

    }


    public Friends(int idaccount, int idfriend) {
        this.setIdaccount(idaccount);
        this.setIdfriend(idfriend);
    }

      
    //
    // Getter & Setter
    //

    public int getPid() {
        return pid;
    }

    public void setPid(final int pid) {
        this.pid = pid;
    }

    public int getIdaccount() {
        return idaccount;
    }

    public void setIdaccount(final int idaccount) {
        this.idaccount = idaccount;
    }

    public int getIdfriend() {
        return idfriend;
    }

    public void setIdfriend(final int idfriend) {
        this.idfriend = idfriend;
    }    
}
