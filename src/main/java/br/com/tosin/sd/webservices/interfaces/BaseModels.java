package br.com.tosin.sd.webservices.interfaces;

import java.sql.ResultSet;
import java.util.List;

import br.com.tosin.sd.webservices.models.Book;

public interface BaseModels {

	Book getBookById(Long id);
	List<Book> findByName(String name);
	List<Book> getBooks();
	Book createBook(ResultSet rs);
	boolean save(Book book);
	boolean delete(Book book);
}