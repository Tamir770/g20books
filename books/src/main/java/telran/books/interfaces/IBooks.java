package telran.books.interfaces;

import telran.books.entities.Author;
import telran.books.entities.Book;
import telran.books.entities.Publisher;

public interface IBooks {
	boolean addBook(Book book);

	boolean removeBook(long isbn);

	Book getBookByIsbn(long isbn);

	Iterable<Book> getBooksByAuthor(String authorName);

	Iterable<Book> getBooksByPublisher(String publisherName);

	Iterable<Author> getBookAuthors(long isbn);

	Iterable<Publisher> getPublishersByAuthor(String authorName);

}
