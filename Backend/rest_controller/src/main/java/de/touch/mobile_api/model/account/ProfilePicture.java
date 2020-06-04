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

/**
 *
 * @author Eric G. Werner <gruschtelapps@gmail.com>
 */
@Entity
@Table(name = "Profilbild")
public class ProfilePicture implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 2157370824045550126L;

    @Id
    @Column(name = "Pid")
    private int pid;

    @Column(name = "Dateiname")
    private String filename = "default_profil_picture.png";

    @Column(name = "image")
    private String image;

    //
    //
    //

    //
    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    //
    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    //
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
