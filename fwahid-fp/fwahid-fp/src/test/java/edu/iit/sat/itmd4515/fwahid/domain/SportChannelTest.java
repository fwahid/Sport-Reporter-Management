/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.fwahid.domain;

import static edu.iit.sat.itmd4515.fwahid.domain.AbstractJPATest.emf;
import java.util.GregorianCalendar;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * This is the SportChannel Test class for my domain package.
 * @author fwahid
 */

public class SportChannelTest extends AbstractJPATest {

    @Before
    @Override
    public void beforeEachTest() {
        super.beforeEachTest();

        SportChannel seed = new SportChannel(
                "SEED",
                new GregorianCalendar(1973, 8, 29).getTime());

        tx.begin();
        em.persist(seed);
        tx.commit();
    }

    @After
    @Override
    public void afterEachTest() {
        SportChannel seed = em
                .createNamedQuery("SportChannel.findByName", SportChannel.class)
                .setParameter("name", "SEED")
                .getSingleResult();

        tx.begin();
        em.remove(seed);
        tx.commit();

        super.afterEachTest();
    }

    //@Test
    public void verifySeedDataTest() {
        List<SportChannel> seeds = em
                .createNamedQuery("SportChannel.findByName", SportChannel.class)
                .setParameter("name", "SEED")
                .getResultList();

        assertEquals(seeds.size(), 1);
        assertEquals("SEED", seeds.get(0).getName());
    }

    /*
     * Sunny day test for persist
     */
    //@Test
    public void persistNewSportChannelAndExpectSuccess() {
        SportChannel sc1 = new SportChannel(
                "ABC",
                new GregorianCalendar(1973, 8, 29).getTime());

        tx.begin();

        assertNull("ID should be null until after em.persist()", sc1.getId());
        em.persist(sc1);
        assertNull("ID should still be null after em.persist() before the tx.commit()", sc1.getId());
        tx.commit();
        assertNotNull("ID should NOT be null after em.persist() and after the tx.commit()", sc1.getId());
        assertTrue("ID should always be greater than 0", sc1.getId() > 0l);
    }

    /*
    * Rainy day test for persist
     */
    //@Test(expected = RollbackException.class)
    public void persistNewSportChannelAndExpectFailure() {
        SportChannel sc1 = new SportChannel(
                "SEED",
                new GregorianCalendar(1973, 8, 29).getTime());

        tx.begin();
        em.persist(sc1);
        tx.commit();
    }

//    //Read
//    //@Test
//    public void readNewSportsAndSuccess() {
//
//        /*EntityManagerFactory emf = Persistence.createEntityManagerFactory("itmd4515PU");
//      EntityManager em = emf.createEntityManager();
//      EntityTransaction tx = em.getTransaction();*/
//        tx.begin();
//
//        SportChannel s1 = em.find(SportChannel.class, 1L);
//        assertNotNull("Read results are not NULL which means entry does exist for PK 1", s1.getId());
//
//        tx.commit();
//
//    }
//
//    //update
//    //@Test
//    public void updateNewSportsAndSuccess() {
//
//        /*EntityManagerFactory emf = Persistence.createEntityManagerFactory("itmd4515PU");
//      EntityManager em = emf.createEntityManager();
//      EntityTransaction tx = em.getTransaction();*/
//        tx.begin();
//        SportChannel s1 = em.find(SportChannel.class, 1L);
//        s1.setName("Bears1");
//        assertNotNull("Name updated successfully", s1.getName());
//        tx.commit();
//
//    }
//
//    //delete
//    //@Test
//    public void deleteNewSportsAndSuccess() {
//
//        /*EntityManagerFactory emf = Persistence.createEntityManagerFactory("itmd4515PU");
//      EntityManager em = emf.createEntityManager();
//      EntityTransaction tx = em.getTransaction();*/
//        tx.begin();
//        SportChannel s1 = em.find(SportChannel.class, 1L);
//        em.remove(s1);
//        assertNotNull("Removed Successfully", s1.getName());
//        tx.commit();
//
//    }

    //@Test
    public void testAssertEquals() {
        assertEquals("failure - strings are not equal", "text", "text");
    }

    //@Test
    public void testAssertFalse() {
        assertFalse("failure - should be false", false);
    }

    //@Test
    public void testAssertNotNull() {
        assertNotNull("should not be null", new Object());
    }
}
