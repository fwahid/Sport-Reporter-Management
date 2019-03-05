/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.fwahid.web;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;

/**
 * This is the Login Controller class for my web package.
 * @author fwahid
 */

@Named
@RequestScoped
public class LoginController extends AbstractController {

    private static final Logger LOG = Logger.getLogger(LoginController.class.getName());

    @NotBlank(message = "You must enter a username")
    private String username;
    @NotBlank(message = "You must enter a password")
    private String password;

    public LoginController() {
    }

    // action methods
    public String doLogin() {
        try {
            HttpServletRequest req = (HttpServletRequest) context.getExternalContext().getRequest();
            req.login(username, password);
            return "/welcome?faces-redirect=true";
        } catch (ServletException ex) {
            LOG.log(Level.SEVERE, null, ex);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Bad Login", "Please check your username or password and try again."));
            return "/login";
        }
    }

    public String doLogout() {
        try {
            HttpServletRequest req = (HttpServletRequest) context.getExternalContext().getRequest();
            req.logout();

        } catch (ServletException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }

        return "/login?faces-redirect=true";

    }

    // helper methods for login process
    public String getRemoteUser() {
        return context.getExternalContext().getRemoteUser();
    }

    public boolean isListener() {
        return context.getExternalContext().isUserInRole("LISTENER_ROLE");
    }

    public boolean isSportReporter() {
        return context.getExternalContext().isUserInRole("SR_ROLE");
    }

    public boolean isAdmin() {
        return context.getExternalContext().isUserInRole("ADMIN_ROLE");
    }

    /**
     * Get the value of username
     *
     * @return the value of username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set the value of username
     *
     * @param username new value of username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}

