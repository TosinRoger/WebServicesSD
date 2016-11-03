package br.com.tosin.sd.webservices.interfaces;

import java.sql.ResultSet;
import java.util.List;

import br.com.tosin.sd.webservices.models.Book;
import br.com.tosin.sd.webservices.models.ManagementBook;

public interface BaseModels {

	ManagementBook getBookById(Long id);
	List<ManagementBook> findByName(String name);
	List<ManagementBook> getBooks();
	Book createBook(ResultSet rs);
	boolean save(ManagementBook book);
	boolean delete(ManagementBook book);
}