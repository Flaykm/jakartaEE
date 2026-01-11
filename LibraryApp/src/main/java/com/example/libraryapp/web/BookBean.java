package com.example.libraryapp.web;

import com.example.libraryapp.entity.Book;
import com.example.libraryapp.entity.Author;
import com.example.libraryapp.service.BookService;
import com.example.libraryapp.service.AuthorService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class BookBean implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Inject
    private BookService bookService;

    @Inject
    private AuthorService authorService;

    private Book book = new Book();
    private List<Book> books;
    private List<Author> authors;

    @PostConstruct
    public void init() {
        books = bookService.getAllBooks();
        authors = authorService.getAllAuthors();
    }

    public Book getBook() { return book; }
    public void setBook(Book book) { this.book = book; }

    public List<Book> getBooks() { return books; }
    public List<Author> getAuthors() { return authors; }

    public void saveBook() {
        if (book.getId() == null) {
            bookService.addBook(book);
        } else {
            bookService.updateBook(book);
        }
        book = new Book();
        books = bookService.getAllBooks();
    }

    public void editBook(Book b) {
        this.book = b;
    }

    public void deleteBook(Book b) {
        bookService.deleteBook(b);
        books = bookService.getAllBooks();
    }
}