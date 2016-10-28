package br.com.tosin.sd.webservices.domains;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.tosin.sd.webservices.models.Book;

@Component
public class BookService {
	
//	private BookDAO db = new BookDAO();
	@Autowired
	private BookDAO db;

	public List<Book> getBooks() {
		// TODO Auto-generated method stub
		return db.getBooks();
	}
	
	public Book getBook(Long id) {
		// TODO Auto-generated method stub
		return db.getBookById(id);
	}

	public List<Book> getBooks(String name) {
		// TODO Auto-generated method stub
		return db.findByName(name);
	}

	public boolean  save(Book book) {
		// TODO Auto-generated method stub
		return db.save(book);
	}

	public boolean delete(Book book) {
		// TODO Auto-generated method stub
		return db.delete(book);
	}

}
