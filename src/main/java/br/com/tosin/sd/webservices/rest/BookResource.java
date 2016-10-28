package br.com.tosin.sd.webservices.rest;


import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.tosin.sd.webservices.domains.BookService;
import br.com.tosin.sd.webservices.models.Book;

@Path("/books")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Component
public class BookResource {

//	private BookService bookService = new BookService();
	@Autowired
	private BookService bookService;
	
	@GET
	@RequestMapping()
	@RolesAllowed("all")
	public List<Book> getBook(@HeaderParam(value = "Authorization") String authorization) {
		System.out.println("Authorization: " + (authorization.isEmpty() ? "Empty" : authorization));
		List<Book> books = bookService.getBooks();
		
		return books;
	}
	
	@GET
	@Path("{id}")
	public Book getBookById(@PathParam ("id") long id) {
		Book book = bookService.getBook(id);
		
		return book;
	}
	
	@GET
	@Path("/name/{name}")
	public List<Book> getBookByName(@PathParam ("name") String name) {
		List<Book> books = bookService.getBooks(name);
		
		return books;
	}
}
