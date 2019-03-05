/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.fwahid.service;

import edu.iit.sat.itmd4515.fwahid.domain.SportShow;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;

/**
 * This is the SportShow class for my service package.
 * @author fwahid
 */
@Named
@Stateless
public class SportShowService extends AbstractService<SportShow> {

    /**
     *
     */
    public SportShowService() {
        super(SportShow.class);
    }

    /**
     *
     * @return
     */
    @Override
    public List<SportShow> findAll() {
        return getEntityManager().createNamedQuery("SportShow.findAll", SportShow.class).getResultList();
    }

}
