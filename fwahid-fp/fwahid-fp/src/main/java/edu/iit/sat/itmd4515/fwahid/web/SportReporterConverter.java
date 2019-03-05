/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.fwahid.web;

import edu.iit.sat.itmd4515.fwahid.domain.SportReporter;
import edu.iit.sat.itmd4515.fwahid.service.SportReporterService;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;

/**
 * This is the Sport Reporter Converter class for my web package.
 * @author fwahid
 */
@Named
public class SportReporterConverter implements Converter {

    @EJB
    private SportReporterService srSvc;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        return srSvc.find(Long.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "";
        }
        return String.valueOf(((SportReporter) value).getId());
    }

}
