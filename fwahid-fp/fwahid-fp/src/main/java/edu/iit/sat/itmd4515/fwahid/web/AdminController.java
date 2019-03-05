/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.fwahid.web;

import edu.iit.sat.itmd4515.fwahid.domain.SportReporter;
import edu.iit.sat.itmd4515.fwahid.domain.SportChannel;
import edu.iit.sat.itmd4515.fwahid.service.SportReporterService;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author fwahid
 */
@Named
@RequestScoped
public class AdminController extends AbstractController {

    private static final Logger LOG = Logger.getLogger(AdminController.class.getName());
    // a single Sport Reporter to function as model for view/edit forms
    private SportReporter sr;

    @EJB
    SportReporterService srSvc;
    // a list to hold all the Sport Reporters in the database for use as a model in the admin views
    private List<SportReporter> srs;

    @Override
    @PostConstruct
    protected void postConstruct() {
        srs = srSvc.findAll();
        sr = new SportReporter();
        sr.setSportChannel(new SportChannel());
        LOG.info("Inside AdminController.postConstruct()");
        super.postConstruct();
    }

    // action methods
    public String doCreateSr() {
        LOG.info("Inside AdminController.doCreateSr() with " + sr.toString());
        return "/admin/createSr";
    }

    public String doViewSr(SportReporter sr) {
        this.sr = sr;
        LOG.info("Inside AdminController.doViewSr() with " + sr.toString());
        return "/admin/viewSr";
    }

    public String doEditSr(SportReporter sr) {
        this.sr = sr;
        LOG.info("Inside AdminController.doEditSr() with " + sr.toString());
        return "/admin/editSr";
    }

    // not a 2 stage operation - pass what to delete, and delete it
    // (but could be a 2 stage operation if you want a confirm)
    public String doDeleteSr(SportReporter sr) {
        LOG.info("Inside AdminController.doDeleteSr() with " + sr.toString());

        // call the service layer to delete
        srSvc.remove(sr);

        // refresh the model
        srs = srSvc.findAll();

        return "/admin/welcome";
    }

    public String executeSaveSr() {
        LOG.info("Are you SURE that you have managed all your entities and set BOTH sides of your relationships?!?!? ");

        if (this.sr.getId() != null) {
            LOG.info("Warning!  Warning!  We are about to UPDATE " + sr.toString());
            // call the service layer to make the update
            srSvc.update(sr);
        } else {
            LOG.info("Warning!  Warning!  We are about to CREATE " + sr.toString());
            // call the service layer to create
            srSvc.create(sr);
        }

        // refresh the model
        srs = srSvc.findAll();
        return "/admin/welcome";
    }

    // accessors and mutators go here
    public SportReporter getSr() {
        return sr;
    }

    public void setSr(SportReporter sr) {
        this.sr = sr;
    }

    public List<SportReporter> getSrs() {
        return srs;
    }

    public void setSrs(List<SportReporter> srs) {
        this.srs = srs;
    }

}
