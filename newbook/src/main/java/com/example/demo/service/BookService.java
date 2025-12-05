package com.example.demo.service;
import org.springframework.stereotype.Service;

import com.example.demo.model.Book;
import com.example.demo.repository.BookRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository repo;

    public BookService(BookRepository repo) {
        this.repo = repo;
    }

    public List<Book> getAllBooks() {
        return repo.findAll();
    }

    public Optional<Book> getBookById(Long id) {
        return repo.findById(id);
    }

    public Book createBook(Book book) {
        return repo.save(book);
    }

    public Book updateBook(Long id, Book updatedBook) {
        return repo.findById(id)
                .map(book -> {
                    book.setTitle(updatedBook.getTitle());
                    book.setAuthor(updatedBook.getAuthor());
                    book.setPrice(updatedBook.getPrice());
                    return repo.save(book);
                })
                .orElseThrow(() -> new RuntimeException("Book not found with id " + id));
    }

    public void deleteBook(Long id) {
        repo.deleteById(id);
    }
}

