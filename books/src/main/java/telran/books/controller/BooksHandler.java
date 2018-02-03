package telran.books.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import telran.books.entities.Author;
import telran.books.entities.Book;
import telran.books.entities.Publisher;
import telran.books.interfaces.IBookConstants;
import telran.books.interfaces.IBooks;

@RestController
public class BooksHandler {
	@Autowired
	IBooks books;

	@PostMapping(IBookConstants.BOOK)
	@CrossOrigin
	public boolean addBook(@RequestBody Book book) {
		return books.addBook(book);
	}

	@GetMapping({ IBookConstants.BOOK + "/{isbn}" })
	@CrossOrigin
	public Book getBook(@PathVariable long isbn) {
		return books.getBookByIsbn(isbn);
	}

	@DeleteMapping({ IBookConstants.BOOK + "/{isbn}" })
	@CrossOrigin
	public boolean removeBook(@PathVariable long isbn) {
		return books.removeBook(isbn);
	}

	@GetMapping(IBookConstants.AUTHOR + "/{name}")
	@CrossOrigin
	public Iterable<Book> booksByAuthor(@PathVariable String name) {
		return books.getBooksByAuthor(name);
	}

	@GetMapping(IBookConstants.PUBLISHER + "/{name}")
	@CrossOrigin
	public Iterable<Book> booksByPublisher(@PathVariable String name) {
		return books.getBooksByPublisher(name);
	}

	@GetMapping(IBookConstants.AUTHORS + "/{isbn}")
	public Iterable<Author> getAuthors(@PathVariable long isbn) {
		return books.getBookAuthors(isbn);
	}

	@GetMapping(IBookConstants.PUBLISHERS + "/{name}")
	@CrossOrigin
	public Iterable<Publisher> getPublishers(@PathVariable String name) {
		return books.getPublishersByAuthor(name);
	}

}
