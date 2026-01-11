package com.example.libraryapp.service;

import com.example.libraryapp.entity.Book;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class BookService {

    @PersistenceContext(unitName = "LibraryAppPU")
    private EntityManager em;

    public void addBook(Book book) {
        em.persist(book);
    }

    public void updateBook(Book book) {
        em.merge(book);
    }

    public void deleteBook(Book book) {
        em.remove(em.contains(book) ? book : em.merge(book));
    }

    public List<Book> getAllBooks() {
        return em.createQuery("SELECT b FROM Book b", Book.class).getResultList();
    }
}