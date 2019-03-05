/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.fwahid.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;



@Entity
@Table(name = "SPORT_SHOW")
public class SportShow extends AbstractIdentifiedEntity {

    @ManyToMany
    @JoinTable(name = "SR_SHOW",
            joinColumns = @JoinColumn(name = "SHOW_ID"),
            inverseJoinColumns = @JoinColumn(name = "SR_ID"))
    private List<SportReporter> sportReporters = new ArrayList<>();

    private String name;

    public SportShow(String name) {
        this.name = name;
    }

    /*
     * Helper methods to help manage BOTH sides of the bi-directional relationship
     */
    public void addSportReporter(SportReporter sr) {
        if (!this.sportReporters.contains(sr)) {
            this.sportReporters.add(sr);
        }
        if (!sr.getSportShows().contains(this)) {
            sr.getSportShows().add(this);
        }
    }

    public SportShow() {
    }

    public List<SportReporter> getSportReporters() {
        return sportReporters;
    }

    public void setSportReporters(List<SportReporter> sportReporters) {
        this.sportReporters = sportReporters;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "SportShow{" + "sportReporters=" + sportReporters + ", name=" + name + '}';
    }

}