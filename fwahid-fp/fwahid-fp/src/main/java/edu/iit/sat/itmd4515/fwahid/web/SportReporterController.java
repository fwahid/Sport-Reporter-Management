/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.fwahid.web;

import edu.iit.sat.itmd4515.fwahid.domain.SportReporter;
import edu.iit.sat.itmd4515.fwahid.domain.SportShow;
import edu.iit.sat.itmd4515.fwahid.service.SportReporterService;
import edu.iit.sat.itmd4515.fwahid.service.SportShowService;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * This is the SportReporter Controller class for my web package.
 * @author fwahid
 */

@Named
@RequestScoped
public class SportReporterController extends AbstractController {

    private static final Logger LOG = Logger.getLogger(SportReporterController.class.getName());

    private SportReporter sr;
    private SportShow sportShow;

    @EJB
    private SportReporterService srSvc;

    @EJB
    private SportShowService showSvc;

    @Inject
    private LoginController loginController;

    @Override
    @PostConstruct
    protected void postConstruct() {
        super.postConstruct();
        LOG.info("Inside SportReporterController postConstruct");
        sr = srSvc.findByUserName(loginController.getRemoteUser());
        sportShow = new SportShow();
    }

    // helper method
    public String getShowSportReportersFormatted(SportShow show) {
        List<String> names = new ArrayList<>();
        for (SportReporter sr : show.getSportReporters()) {
            names.add(sr.getName());
        }
        return String.join(",", names);
    }

    // action methods here
    public String doViewShow(SportShow show) {
        this.sportShow = show;
        LOG.info("We are about to view the information for sport show " + sportShow.toString());
        return "/sr/viewShow";
    }

    public String doEditShow(SportShow show) {
        this.sportShow = show;
        LOG.info("We are about to edit the information for sport show " + sportShow.toString());
        return "/sr/editShow";
    }

    public String doDeleteShow() {
        LOG.info("We are about to delete the information for sport show " + sportShow.toString());
        return "/sr/welcome";
    }

    public String doCreateShow() {
        LOG.info("We are about to create a new sport show " + sportShow.toString());
        return "/sr/welcome";
    }

    public String executeSaveSR() {
        LOG.info("Inside SportReporterController executeSaveSR to save " + this.sr.toString());
        return "/sr/welcome";
    }

    public String executeSaveSportShow() {
        LOG.info("Inside SportReporterController executeSaveSportShow to save " + this.sportShow.toString());

        // invoke the service layer
        showSvc.update(sportShow);

        return "/sr/welcome";
    }

    /**
     * Get the value of sr
     *
     * @return the value of sr
     */
    public SportReporter getSr() {
        return sr;
    }

    /**
     * Set the value of sr
     *
     * @param sr new value of sr
     */
    public void setSr(SportReporter sr) {
        this.sr = sr;
    }

    public SportShow getSportShow() {
        return sportShow;
    }

    public void setSportShow(SportShow sportShow) {
        this.sportShow = sportShow;
    }

}
