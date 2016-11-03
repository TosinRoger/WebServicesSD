package br.com.tosin.sd.webservices.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import br.com.tosin.sd.webservices.domains.BookService;
import br.com.tosin.sd.webservices.models.Book;
import br.com.tosin.sd.webservices.models.ManagementBook;
import br.com.tosin.sd.webservices.models.Response;

@Path("/books")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Component
public class BookResource {

	@Autowired
	private BookService bookService;
	
	@GET
	public List<Book> getBook() {
		List<ManagementBook> managementBooks = bookService.getBooks();
		
		return formatList(managementBooks);
	}
	
	@GET
	@Path("{id}")
	public Book getBookById(@PathParam ("id") long id) {
		Book book = bookService.getBook(id).getBook();
		return book;
	}
	
	@GET
	@Path("/name/{name}")
	public List<Book> getBookByName(@PathParam ("name") String name) {
		List<ManagementBook> books = bookService.getBooks(name);
		return formatList(books);
	}
	
	@POST 
	@ResponseBody
	public Response test(@RequestBody String json) {
		JsonParser parser = new JsonParser();
		JsonObject object = parser.parse(json).getAsJsonObject();
		
		String text = object.has("text") ? object.get("text").getAsString() : "NotText";
		long id = object.has("id") ? object.get("id").getAsLong() : -1;
		
		if (id == -1) {
			return Response.Error("Id inválido");
		}
		
		return Response.Ok("Funfou: " + text + ", id: " + id);
	}
	
	private List<Book> formatList(List<ManagementBook> managementBooks) {
		List<Book> books = new ArrayList<>();
		for(Book item : managementBooks)
			books.add(item);
		return books;
	}
}
