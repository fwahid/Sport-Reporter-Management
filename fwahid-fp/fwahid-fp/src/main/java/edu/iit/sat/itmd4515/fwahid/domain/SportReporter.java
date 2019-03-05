/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.fwahid.domain;

import edu.iit.sat.itmd4515.fwahid.domain.security.User;
import edu.iit.sat.itmd4515.fwahid.domain.SportShow;
import edu.iit.sat.itmd4515.fwahid.domain.security.User;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;

/**
 * This is the Sport Reporter class for my domain package.
 *
 * @author fwahid
 */
@Entity
@NamedQueries({
    @NamedQuery(
            name = "SportReporter.findByName",
            query = "select sr from SportReporter sr where sr.name = :name")
    ,
    @NamedQuery(
            name = "SportReporter.findAll",
            query = "select sr from SportReporter sr")
    ,
    @NamedQuery(
            name = "SportReporter.findByUsername",
            query = "select sr from SportReporter sr where sr.user.userName = :username")
})
public class SportReporter extends AbstractIdentifiedEntity {

    @ManyToOne
    @JoinColumn(name = "SR_ID") //Sport Reporter ID
    private SportChannel sportChannel;

    @ManyToMany(mappedBy = "sportReporters")
    private List<SportShow> sportShows = new ArrayList<>();

    @NotBlank
    private String name;

    @PastOrPresent
    @Temporal(TemporalType.DATE)
    private Date dateHired;

    @Email
    private String email;

    @OneToOne
    @JoinColumn(name = "USERNAME")
    private User user;

    public SportReporter() {
    }

    public SportReporter(String name, Date dateHired) {
        this.name = name;
        this.dateHired = dateHired;
    }

    public SportReporter(String name, Date dateHired, String email) {
        this.name = name;
        this.dateHired = dateHired;
        this.email = email;
    }

    /*
     * Helper methods to help manage BOTH sides of the bi-directional relationship
     */
    public void addSportShow(SportShow ss) {
        if (!this.sportShows.contains(ss)) {
            this.sportShows.add(ss);
        }
        if (!ss.getSportReporters().contains(this)) {
            ss.getSportReporters().add(this);
        }
    }

    public void removeSportChannel(){
        this.sportChannel.getSportReporters().remove(this);
        this.sportChannel = null;
    }
    
    public SportChannel getSportChannel() {
        return sportChannel;
    }

    public void setSportChannel(SportChannel sportChannel) {
        this.sportChannel = sportChannel;

        if (!this.sportChannel.getSportReporters().contains(this)) {
            this.sportChannel.getSportReporters().add(this);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateHired() {
        return dateHired;
    }

    public void setDateHired(Date dateHired) {
        this.dateHired = dateHired;
    }

    public List<SportShow> getSportShows() {
        return sportShows;
    }

    public void setSportShows(List<SportShow> sportShows) {
        this.sportShows = sportShows;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "SportReporter{" + "name=" + name + ", dateHired=" + dateHired + ", email=" + email + '}';
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}