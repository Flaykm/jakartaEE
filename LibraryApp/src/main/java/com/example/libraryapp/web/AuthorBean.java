package com.example.libraryapp.web;

import com.example.libraryapp.entity.Author;
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
public class AuthorBean implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Inject
    private AuthorService authorService;

    private Author author = new Author();
    private List<Author> authors;

    @PostConstruct
    public void init() {
        authors = authorService.getAllAuthors();
    }

    public Author getAuthor() { return author; }
    public void setAuthor(Author author) { this.author = author; }

    public List<Author> getAuthors() { return authors; }

    public void saveAuthor() {
        if (author.getId() == null) {
            authorService.addAuthor(author);
        } else {
            authorService.updateAuthor(author);
        }
        author = new Author();
        authors = authorService.getAllAuthors();
    }

    public void editAuthor(Author a) {
        this.author = a;
    }

    public void deleteAuthor(Author a) {
        authorService.deleteAuthor(a);
        authors = authorService.getAllAuthors();
    }
}