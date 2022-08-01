package com.RESTful.bibli;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    BookRepository bookRepo;

    @PostMapping("/create")
    public Book createBook(String title, String author, String description){
        Book book = new Book(title, author, description);
        return  bookRepo.save(book);
    }

    @GetMapping("/read")
    public Book getBook(Long bookId){
        return bookRepo.findById(bookId).get();
    }

    @GetMapping("/readall")
    public List<Book> getAllBooks(){
        return bookRepo.findAll();
    }

    @GetMapping("/search")
    public List<Book> searchBooks(String searchTerm) {
        List<Book> searchedBooks = new ArrayList<Book>();
        List<Book> books = bookRepo.findAll();

        for(Book b: books) {
            if(b.getTitle().toLowerCase().contains(searchTerm.toLowerCase()) ||
                    b.getDescription().toLowerCase().contains(searchTerm.toLowerCase())) {
                searchedBooks.add(b);
            }
        }

        return searchedBooks;
    }

    @PutMapping("/update")
    public Book updateBook(Long bookId, String title, String author, String description){
        Book wilderToUpdate = bookRepo.findById(bookId).get();
        if(title != null){
            wilderToUpdate.setTitle(title);
        }
        if(author != null){
            wilderToUpdate.setAuthor(author);
        }
        if (description != null){
            wilderToUpdate.setDescription(description);
        }
        return bookRepo.save(wilderToUpdate);
    }

    @DeleteMapping("/delete")
    public void deleteWilder(Long bookId){
        bookRepo.deleteById(bookId);
    }

}
