/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.fwahid.service;

import edu.iit.sat.itmd4515.fwahid.domain.security.Group;
import java.util.List;
import javax.ejb.Stateless;

/**
 * This is the Group class for my service package.
 * @author fwahid
 */

@Stateless
public class GroupService extends AbstractService<Group> {

    public GroupService() {
        super(Group.class);
    }

    @Override
    public List<Group> findAll() {
        return getEntityManager().createNamedQuery("Group.findAll", Group.class).getResultList();
    }

}
