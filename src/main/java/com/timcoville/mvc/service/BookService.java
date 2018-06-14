package com.timcoville.mvc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.timcoville.mvc.models.Book;
import com.timcoville.mvc.repositories.BookRepository;

@Service
public class BookService {
	private final BookRepository bookRepository;
	public BookService(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}
	
	// returns all the books
    public List<Book> allBooks() {
        return bookRepository.findAll();
    }
    // creates a book
    public Book createBook(Book b) {
        return bookRepository.save(b);
    }
    // retrieves a book
    public Book findBook(Long id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if(optionalBook.isPresent()) {
            return optionalBook.get();
        } else {
            return null;
        }
    }
    public Book updateBook(Long id, String title, String desc, String lang, Integer numOfPages) {
        Book book = findBook(id);
        if (book != null) {
	        book.setTitle(title);
	        book.setDescription(desc);
	        book.setLanguage(lang);
	        book.setNumberOfPages(numOfPages);
	        return bookRepository.save(book);
        }
        else {
        	return null;
        }
    }
    public void deleteBook(Long id) {
    	Book book = findBook(id);
    	if (book != null) {
    		bookRepository.deleteById(id);
    	}
    }
}
