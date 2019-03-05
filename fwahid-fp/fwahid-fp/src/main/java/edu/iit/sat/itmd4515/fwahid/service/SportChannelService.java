/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.fwahid.service;
import edu.iit.sat.itmd4515.fwahid.domain.SportChannel;
import java.util.List;
import javax.ejb.Stateless;

/**
 * This is the SportChannel class for my service package.
 * @author fwahid
 */

@Stateless
public class SportChannelService extends AbstractService<SportChannel> {

    public SportChannelService() {
        super(SportChannel.class);
    }

    @Override
    public List<SportChannel> findAll() {
        return getEntityManager().createNamedQuery("SportChannel.findAll", SportChannel.class).getResultList();
    }

}

