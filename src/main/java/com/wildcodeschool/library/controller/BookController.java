package com.wildcodeschool.library.controller;


import com.wildcodeschool.library.model.Book;
import com.wildcodeschool.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookRepository repository;

    @GetMapping("")
    public List<Book> getAll(){
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Book getById(@PathVariable Long id) {
        Book book = new Book();
        if (id != null) {
            Optional<Book> optionalBook = repository.findById(id);
            if (optionalBook.isPresent()) {
                book = optionalBook.get();
            }
        }
        return book;
    }

    @PostMapping("/search")
    public List<Book> search(@RequestBody Map<String, String> body) {
        String searchValue = body.get("text");
        System.out.println(searchValue);
        return repository.findByTitleContainingOrDescriptionContaining(searchValue, searchValue);
    }

    @PostMapping("")
    public Book create(@RequestBody Book book) {
        return repository.save(book);
    }

    @PutMapping("/{id}")
    public Book update(@PathVariable Long id, @RequestBody Book book) {
        Book bookToUpdate = repository.findById(id).get();
        if (book.getTitle() != null) {
            bookToUpdate.setTitle(book.getTitle());
        }
        if (book.getAuthor() != null) {
            bookToUpdate.setAuthor(book.getAuthor());
        }
        if (book.getDescription() != null) {
            bookToUpdate.setDescription(book.getDescription());
        }
        return repository.save(bookToUpdate);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Long id) {
        Optional<Book> optionalBook = repository.findById(id);
        if (optionalBook.isPresent()) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
