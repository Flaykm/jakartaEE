package com.example.libraryapp.service;

import com.example.libraryapp.entity.Author;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class AuthorService {

    @PersistenceContext(unitName = "LibraryAppPU")
    private EntityManager em;

    public void addAuthor(Author author) {
        em.persist(author);
    }

    public void updateAuthor(Author author) {
        em.merge(author);
    }

    public void deleteAuthor(Author author) {
        em.remove(em.contains(author) ? author : em.merge(author));
    }

    public List<Author> getAllAuthors() {
        return em.createQuery("SELECT a FROM Author a", Author.class).getResultList();
    }
}