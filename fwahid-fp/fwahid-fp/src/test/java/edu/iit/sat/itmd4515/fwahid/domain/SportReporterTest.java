/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.fwahid.domain;

import java.util.GregorianCalendar;
import java.util.Set;
import javax.validation.ConstraintViolation;
import org.junit.After;
import org.junit.Before;
//import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * This is the SportReporter Test class for my domain package.
 * @author fwahid
 */

public class SportReporterTest extends AbstractJPATest {

    //@Test
    public void nameIsNull() {
        SportReporter sr = new SportReporter(null,
                new GregorianCalendar(1973, 8, 22).getTime());

        System.out.println(sr.toString());
        // an assertion is not a validation
        //assertNotNull(sr.getName());

        Set<ConstraintViolation<SportReporter>> constraintViolations = validator.validate(sr);
        assertEquals(1, constraintViolations.size());

        assertEquals(
                "must not be blank",
                constraintViolations.iterator().next().getMessage());

        for (ConstraintViolation<SportReporter> bad : constraintViolations) {
            System.out.println(bad.toString() + " " + bad.getPropertyPath() + " " + bad.getMessage());
        }

    }

    //@Test
    public void sportReporterIsValid() {
        SportReporter sr = new SportReporter("Sport Reporter Validated",
                new GregorianCalendar(1973, 8, 22).getTime());

        sr.setEmail("fwahid@test.test");

        System.out.println(sr.toString());
        Set<ConstraintViolation<SportReporter>> constraintViolations = validator.validate(sr);
        assertEquals(0, constraintViolations.size());

    }

    //@Test
    public void persistSportReporterTest() {
        SportReporter sr = new SportReporter("Sport Reporter Fahad",
                new GregorianCalendar(1973, 8, 22).getTime());

        SportChannel sc = new SportChannel("NBC",
                new GregorianCalendar(1956, 2, 3).getTime());

        SportShow show1 = new SportShow("Football");
        show1.addSportReporter(sr);
        SportShow show2 = new SportShow("Soccer");
        show2.addSportReporter(sr);
        SportShow show3 = new SportShow("My All Charles Mingus Grand Finale");
        show3.addSportReporter(sr);

        //rs.getDiscJockeys().add(sr);
        //sr.setRadioStation(rs);
        // instead, what you really want to be calling is: sc.addDiscJockey(sr) or manage both sides in dj.setRadioStation
        sr.setSportChannel(sc);

        tx.begin();
        em.persist(sc);
        em.persist(show1);
        em.persist(show2);
        em.persist(show3);
        em.persist(sr);
        tx.commit();

        // what do we see in the persistence context?
        // this is from the inverse side
        SportChannel findMe = em.find(SportChannel.class, 1l);
        System.out.println("From the inverse side... SportChannel name is: \t" + findMe.getName());
        System.out.println("From the inverse side... SportReporter name is: \t" + findMe.getSportReporters().get(0).getName());

        // this is from the owning side
        SportReporter findTheSR = em.find(SportReporter.class, 1l);
        System.out.println("From the owning side... SportReporter name is: \t" + findTheSR.getName());
        System.out.println("From the owning side... SportChannel name is: \t" + findTheSR.getSportChannel().getName());

        // test the inverse collection
        assertTrue(sc.getSportReporters().size() == 1);

        // test the owning side of the relationship
        assertNotNull(findTheSR.getSportChannel().getName());
        assertEquals("NBC", findTheSR.getSportChannel().getName());
    }

}
