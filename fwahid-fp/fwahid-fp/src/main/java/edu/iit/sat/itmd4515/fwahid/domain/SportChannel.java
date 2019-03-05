/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.fwahid.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * This is the code business entity of my Sport Channel Management System.My users will be Sport Reporters and Listeners.
 *
 * @author fwahid
 */
@Entity
@Table(name = "sportchannel")
@NamedQueries({
    @NamedQuery(
            name = "SportChannel.findByName",
            query = "select s from SportChannel s where s.name = :name"),
    @NamedQuery(
            name = "SportChannel.findAll",
            query = "select s from SportChannel s")
})
public class SportChannel extends AbstractIdentifiedEntity {

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "date_founded")
    @Temporal(TemporalType.DATE)
    private Date dateFounded;

    @Transient
    private int youWillNotBeAddedToTheTable;

    @OneToMany(mappedBy = "sportChannel")
    private List<SportReporter> sportReporters = new ArrayList<>();

    public SportChannel() {
    }

    public SportChannel(String name, Date dateFounded) {
        this.name = name;
        this.dateFounded = dateFounded;
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

    public Date getDateFounded() {
        return dateFounded;
    }

    public void setDateFounded(Date dateFounded) {
        this.dateFounded = dateFounded;
    }

    @Override
    public String toString() {
        return "SportChannel{" + "id=" + id + ", name=" + name + ", dateFounded=" + dateFounded + '}';
    }

    public List<SportReporter> getSportReporters() {
        return sportReporters;
    }

    public void setSportReporters(List<SportReporter> sportReporters) {
        this.sportReporters = sportReporters;
    }

}
