/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.fwahid.service;

import edu.iit.sat.itmd4515.fwahid.domain.security.Group;
import edu.iit.sat.itmd4515.fwahid.domain.security.User;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 * This is the Seed Database class for my service package.
 * @author fwahid
 */

@Startup
@Singleton
public class StartupSeedDatabase {
    
    @EJB private UserService userService;
    @EJB private GroupService groupService;
    
    @PostConstruct
    private void seedDatabase(){
        Group adminGroup = new Group("ADMINS", "Users in this group are administrators of the system.");
        User admin = new User("admin", "admin", true);
        admin.addGroup(adminGroup);
        
        groupService.create(adminGroup);
        userService.create(admin);
    }
    
}
