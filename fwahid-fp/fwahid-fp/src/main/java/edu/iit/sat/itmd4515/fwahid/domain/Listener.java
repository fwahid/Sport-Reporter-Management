/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.fwahid.domain;

import edu.iit.sat.itmd4515.fwahid.domain.security.User;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

/**
 * This is the Listener class for my domain package.
 *
 * @author fwahid
 */

@Entity
@NamedQueries({
    @NamedQuery(
            name = "Listener.findByName",
            query = "select l from Listener l where l.name = :name")
    ,
    @NamedQuery(
            name = "Listener.findAll",
            query = "select l from Listener l"),
    @NamedQuery(
            name = "Listener.findByUsername",
            query = "select l from Listener l where l.user.userName = :username")    
})
public class Listener extends AbstractIdentifiedEntity {

    private String name;

    @OneToOne
    @JoinColumn(name = "USERNAME")
    private User user;

    public Listener() {
    }

    /**
     * Get the value of name
     *
     * @return the value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the value of name
     *
     * @param name new value of name
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Listener{" + "name=" + name + '}';
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
