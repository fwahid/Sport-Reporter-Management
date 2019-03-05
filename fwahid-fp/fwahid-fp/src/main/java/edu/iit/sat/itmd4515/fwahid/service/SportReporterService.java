/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.fwahid.service;

import edu.iit.sat.itmd4515.fwahid.domain.SportChannel;
import edu.iit.sat.itmd4515.fwahid.domain.SportReporter;
import edu.iit.sat.itmd4515.fwahid.domain.SportShow;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * This is the SportReporter class for my service package.
 * @author fwahid
 */

@Named
@Stateless
public class SportReporterService extends AbstractService<SportReporter> {

    /**
     *
     */
    public SportReporterService() {
        super(SportReporter.class);
    }

    @Override
    public void update(SportReporter updatedSr) {

        
        SportReporter oldSr = find(updatedSr.getId());

        SportChannel sc = getEntityManager().getReference(SportChannel.class, updatedSr.getSportChannel().getId());
        
        // make sure we have all relationships set before updating
        updatedSr.setUser(oldSr.getUser());
        updatedSr.setSportShows(oldSr.getSportShows());
        updatedSr.setSportChannel(sc);
        
        // finally update
        super.update(updatedSr);
    }

    @Override
    public void remove(SportReporter sr) {
        
        
        sr = getEntityManager().getReference(SportReporter.class, sr.getId());
        sr.removeSportChannel();
        
        for(SportShow ss : sr.getSportShows() ){
            ss.getSportReporters().remove(sr);
        }
        sr.setSportShows(null);
        
        getEntityManager().remove(sr);
    }
    
    /**
     *
     * @return
     */
    @Override
    public List<SportReporter> findAll() {
        return getEntityManager().createNamedQuery("SportReporter.findAll", SportReporter.class).getResultList();
    }

    public SportReporter findByUserName(String username) {
        return getEntityManager().createNamedQuery("SportReporter.findByUsername", SportReporter.class).setParameter("username", username).getSingleResult();
    }


}
