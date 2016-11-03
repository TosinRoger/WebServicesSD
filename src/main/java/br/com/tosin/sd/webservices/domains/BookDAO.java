package br.com.tosin.sd.webservices.domains;


import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.tosin.sd.webservices.interfaces.BaseModels;
import br.com.tosin.sd.webservices.models.Book;
import br.com.tosin.sd.webservices.models.ManagementBook;

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
	private List<ManagementBook> books;
	
	public BookDAO() {
		// cria uma lista com 5 livro 
		for (int i = 0; i < 5; i++) {
			save(createBook(null));
		}
	}
	
	public ManagementBook getBookById(Long id) {
		for (ManagementBook book : books) {
			if (book.getId() == id)
				return book;
		}
		return null;
	}
	
	public List<ManagementBook> findByName(String name) {
		List<ManagementBook> result = new ArrayList<>();
		for (ManagementBook book : books) {
			if (book.getTitle().contains(name))
				result.add(book);
		}
		return result;
	}

	@Override
	public List<ManagementBook> getBooks() {
		// TODO Auto-generated method stub
		if(books == null)
			books = new ArrayList<>();
		return books;
	}

	@Override
	public ManagementBook createBook(ResultSet rs) {
		// TODO Auto-generated method stub
		
		// tem que ser melhorado
		ManagementBook book = new ManagementBook(count, 
				"Title " + count, 
				"author " + count, 
				"Historia para ser contada " + count, 
				true, 
				0);
		count++;
		return book;
	}

	@Override
	public boolean save(ManagementBook book) {
		// TODO Auto-generated method stub
		for (ManagementBook item : getBooks()) {
			if (item.getId() == book.getId()) {
				// tem que atualizar
				
				return false;
			}
		}
		// salva
		return getBooks().add(book);
	}

	@Override
	public boolean delete(ManagementBook book) {
		// TODO Auto-generated method stub
		return getBooks().remove(book);
	}
	
	
}