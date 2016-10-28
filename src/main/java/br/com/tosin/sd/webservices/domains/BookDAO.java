package br.com.tosin.sd.webservices.domains;


import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.tosin.sd.webservices.interfaces.BaseModels;
import br.com.tosin.sd.webservices.models.Book;

/**
 * Classe que faz a logica de conexao com o banco de dados. 
 * Nesse exemplo os dados sao criados estaticamente, 
 * Mas uma boa implementacao exige um BD como o MySQL
 * @author tosin
 *
 */
@Component
public class BookDAO implements BaseModels {

	private int count = 0;
	private List<Book> books;
	
	public BookDAO() {
		// cria uma lista com 5 livro 
		for (int i = 0; i < 5; i++) {
			save(createBook(null));
		}
	}
	
	public Book getBookById(Long id) {
		for (Book book : books) {
			if (book.getId() == id)
				return book;
		}
		return null;
	}
	
	public List<Book> findByName(String name) {
		List<Book> result = new ArrayList<>();
		for (Book book : books) {
			if (book.getTitle().contains(name))
				result.add(book);
		}
		return result;
	}

	@Override
	public List<Book> getBooks() {
		// TODO Auto-generated method stub
		if(books == null)
			books = new ArrayList<>();
		return books;
	}

	@Override
	public Book createBook(ResultSet rs) {
		// TODO Auto-generated method stub
		
		// tem que ser melhorado
		Book book = new Book(count, 
				"Title " + count, 
				"author " + count, 
				"Historia para ser contada " + count, 
				true, 
				0);
		count++;
		return book;
	}

	@Override
	public boolean save(Book book) {
		// TODO Auto-generated method stub
		for (Book item : getBooks()) {
			if (item.getId() == book.getId()) {
				// tem que atualizar
				
				return false;
			}
		}
		// salva
		return getBooks().add(book);
	}

	@Override
	public boolean delete(Book book) {
		// TODO Auto-generated method stub
		return getBooks().remove(book);
	}
	
	
}