/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.fwahid.service;

import edu.iit.sat.itmd4515.fwahid.domain.Listener;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;

/**
 * This is the Listener class for my service package.
 * @author fwahid
 */

@Named
@Stateless
public class ListenerService extends AbstractService<Listener> {

    public ListenerService() {
        super(Listener.class);
    }

    @Override
    public List<Listener> findAll() {
        return getEntityManager().createNamedQuery("Listener.findAll", Listener.class).getResultList();
    }

    public Listener findByUserName(String username) {
        return getEntityManager().createNamedQuery("Listener.findByUsername", Listener.class).setParameter("username", username).getSingleResult();
    }
}
