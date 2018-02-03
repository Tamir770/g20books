package telran.books.dao;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import telran.books.entities.Author;
import telran.books.entities.Book;
import telran.books.entities.Publisher;
import telran.books.interfaces.IBooks;

@Repository
public class BooksRepository implements IBooks {
	@PersistenceContext
	EntityManager em;

	@Override
	@Transactional
	public boolean addBook(Book book) {
		if (getBookByIsbn(book.getIsbn()) != null) {
			return false;
		}
		Publisher publisher = book.getPublisher();
		if (em.find(Publisher.class, publisher.getPublisherName()) == null) {
			em.persist(publisher);
		}
		Set<Author> authors = book.getAuthors();
		for (Author author : authors) {
			if (em.find(Author.class, author.getName()) == null) {
				em.persist(author);
			}
		}

		em.persist(book);
		return true;
	}

	@Override
	public boolean removeBook(long isbn) {
		Book book = getBookByIsbn(isbn);
		if (book == null) {
			return false;
		}
		em.remove(book);
		return true;
	}

	@Override
	public Book getBookByIsbn(long isbn) {
		return em.find(Book.class, isbn);
	}

	@Override
	public Iterable<Book> getBooksByAuthor(String authorName) {
		TypedQuery<Book> query = em.createQuery("SELECT b FROM Book b JOIN b.authors a WHERE a.name=?1", Book.class);
		query.setParameter(1, authorName);
		return query.getResultList();
	}

	@Override
	public Iterable<Book> getBooksByPublisher(String publisherName) {
		TypedQuery<Book> query = em.createQuery("SELECT b FROM Book b JOIN b.publisher p WHERE p.publisherName=?1",Book.class);
		query.setParameter(1, publisherName);
		return query.getResultList();
	}

	@Override
	public Iterable<Author> getBookAuthors(long isbn) {
		Book book = getBookByIsbn(isbn);
		if(book == null) {
			return new HashSet<Author>();
		}
		return book.getAuthors();
	}

	@Override
	public Iterable<Publisher> getPublishersByAuthor(String authorName) {
		TypedQuery<Publisher> query = em.createQuery("SELECT p FROM Book b JOIN b.authors a JOIN b.publisher p WHERE a.name=?1", Publisher.class);
		query.setParameter(1, authorName);
		return query.getResultList();
	}

}
