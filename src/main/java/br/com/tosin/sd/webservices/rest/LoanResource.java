package br.com.tosin.sd.webservices.rest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import br.com.tosin.sd.webservices.domains.BookService;
import br.com.tosin.sd.webservices.domains.LoanService;
import br.com.tosin.sd.webservices.domains.UserService;
import br.com.tosin.sd.webservices.models.Book;
import br.com.tosin.sd.webservices.models.Response;
import br.com.tosin.sd.webservices.models.User;

@Path("/loan")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Component
public class LoanResource {
	
	@Autowired
	private UserService userService;
	@Autowired
	private BookService bookService;
	@Autowired
	private LoanService loanService;
	
	@POST
	@ResponseBody
	public Response loanBook(
			@HeaderParam(value = "Authorization") String authorization, 
			@RequestBody String json) {
		
		// verifica se recebeu a autenticacao e o id do livro
		JsonParser parser = new JsonParser();
		JsonObject object = parser.parse(json).getAsJsonObject();
		
		if (authorization.isEmpty()) 
			return Response.Error("Usuário não autorizado");
		
		long id = object.has("id") ? object.get("id").getAsLong() : -1;
		if (id == -1) {
			return Response.Error("Id inválido");
		}
		
		// procura o usuario
		
		User user = userService.fetchUser(authorization);
		
		if (user == null) {
			return Response.Error("usuario invalido");
		}
		
		// procura o livro
		
		Book book = bookService.getBook(id);
		
		if (book == null) {
			return Response.Error("Livro não encontrado");
		}
		
		//verifica se pode emprestar
		
		String result = loanService.loan(user, book);
		
		if (result.isEmpty())
			return Response.Error("Problemas no servidor interno");
		else
			return Response.Ok(result);
		
	}
	
	@POST
	@Path("/renovation")
	@ResponseBody
	public Response renovationBook(
			@HeaderParam(value = "Authorization") String authorization, 
			@RequestBody String json) {
		
		// verifica se recebeu a autenticacao e o id do livro
		JsonParser parser = new JsonParser();
		JsonObject object = parser.parse(json).getAsJsonObject();
		
		if (authorization.isEmpty()) 
			return Response.Error("Usuário não autorizado");
		
		long id = object.has("id") ? object.get("id").getAsLong() : -1;
		if (id == -1) {
			return Response.Error("Id inválido");
		}
		
		// procura o usuario
		
		User user = userService.fetchUser(authorization);
		
		if (user == null) {
			return Response.Error("usuario invalido");
		}
		
		// procura o livro
		
		Book book = bookService.getBook(id);
		
		if (book == null) {
			return Response.Error("Livro não encontrado");
		}
		
		//verifica se pode revovar
		
		String result = loanService.renovation(user, book);
		
		if (result.isEmpty())
			return Response.Error("Problemas no servidor interno");
		else
			return Response.Ok(result);
				
	}
	
	@POST
	@Path("/devolution")
	@ResponseBody
	public Response devolutionBook(
			@HeaderParam(value = "Authorization") String authorization, 
			@RequestBody String json) {
		
		// verifica se recebeu a autenticacao e o id do livro
		JsonParser parser = new JsonParser();
		JsonObject object = parser.parse(json).getAsJsonObject();
		
		if (authorization.isEmpty()) 
			return Response.Error("Usuário não autorizado");
		
		long id = object.has("id") ? object.get("id").getAsLong() : -1;
		if (id == -1) {
			return Response.Error("Id inválido");
		}
		
		// procura o usuario
		
		User user = userService.fetchUser(authorization);
		
		if (user == null) {
			return Response.Error("usuario invalido");
		}
		
		// procura o livro
		
		Book book = bookService.getBook(id);
		
		if (book == null) {
			return Response.Error("Livro não encontrado");
		}
		
		// devolve

		String result = loanService.devolution(user, book);
		
		if (result.isEmpty())
			return Response.Error("Problemas no servidor interno");
		else
			return Response.Ok(result);
				
	}
	
	@POST
	@Path("/reservation")
	@ResponseBody
	public Response reservationBook(
			@HeaderParam(value = "Authorization") String authorization, 
			@RequestBody String json) {
		
		// verifica se recebeu a autenticacao e o id do livro
		JsonParser parser = new JsonParser();
		JsonObject object = parser.parse(json).getAsJsonObject();
		
		if (authorization.isEmpty()) 
			return Response.Error("Usuário não autorizado");
		
		long id = object.has("id") ? object.get("id").getAsLong() : -1;
		if (id == -1) {
			return Response.Error("Id inválido");
		}
		
		// procura o usuario
		
		User user = userService.fetchUser(authorization);
		
		if (user == null) {
			return Response.Error("usuario invalido");
		}
		
		// procura o livro
		
		Book book = bookService.getBook(id);
		
		if (book == null) {
			return Response.Error("Livro não encontrado");
		}
		
		//verifica se pode reservar
		
		String result = loanService.reservation(user, book);
		
		if (result.isEmpty())
			return Response.Error("Problemas no servidor interno");
		else
			return Response.Ok(result);
		
	}
}
