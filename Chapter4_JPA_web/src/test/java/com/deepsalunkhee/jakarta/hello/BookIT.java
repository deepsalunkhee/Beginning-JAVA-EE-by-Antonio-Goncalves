package com.deepsalunkhee.jakarta.hello;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.validation.ConstraintViolationException;

public class BookIT {

    private static EntityManagerFactory emf = 
        Persistence.createEntityManagerFactory("chapter04TestPU");
    private EntityManager em;
    private EntityTransaction tx;

    @Before
    public void initEntityManager() {
        em = emf.createEntityManager();
        tx = em.getTransaction();
    }

    @After
    public void closeEntityManager() {
        if (em != null) em.close();
    }

    @Test
    public void shouldFindJavaEE7Book() {
        // Insert test data
        tx.begin();
        Book book = new Book("Beginning Java EE 7", "A Java EE 7 book", 45.0F, "123-456", 500, false);
        em.persist(book);
        tx.commit();

        // Test find by ID
        Book found = em.find(Book.class, book.getId());
        assertNotNull("Book should exist", found);
        assertEquals("Beginning Java EE 7", found.getTitle());
    }

    @Test
    public void shouldCreateH2G2Book() {
        Book book = new Book("H2G2", "The Hitchhiker's Guide to the Galaxy",
                             12.5F, "1-84023-742-2", 354, false);

        tx.begin();
        em.persist(book);
        tx.commit();

        assertNotNull("ID should not be null", book.getId());

        // Named query test
        List<Book> books = em.createNamedQuery("findBookH2G2", Book.class).getResultList();
        assertEquals(1, books.size());

        Book retrieved = em.createNamedQuery("findBookH2G2", Book.class).getSingleResult();
        assertEquals("The Hitchhiker's Guide to the Galaxy", retrieved.getDescription());
    }

    @Test(expected = ConstraintViolationException.class)
    public void shouldRaiseConstraintViolationCauseNullTitle() {
        Book book = new Book(null, "Null title, should fail", 12.5F, "1-84023-742-2", 354, false);

        tx.begin();
        try {
            em.persist(book);
            em.flush(); // Force validation
        } finally {
            tx.rollback(); // Rollback so DB remains clean
        }
    }
}
