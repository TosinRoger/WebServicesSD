package br.com.tosin.sd.webservices.rest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import br.com.tosin.sd.webservices.domains.UserService;
import br.com.tosin.sd.webservices.models.Response;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Component
public class UserResource {

	@Autowired
	private UserService service;
	
	@POST
	@ResponseBody
	public Response createOrUpdate(@RequestBody String json) {
		JsonParser parser = new JsonParser();
		JsonObject object = parser.parse(json).getAsJsonObject();
		
		
		String pass = object.has("password") ? object.get("password").getAsString() : "";
		String username = object.has("username") ? object.get("username").getAsString() : "";
		
		if (pass.isEmpty() || username.isEmpty()) {
			return Response.Error("Dados inválido");
		}
		
		if (service.createOrUpdate(username, pass) == 1) {
			return Response.Ok("Usuário criado");
		}
		else 
			return Response.Ok("Senha atualizado");
		
	}
	
	@DELETE
	@ResponseBody
	public Response delete(@RequestBody String json) {
		JsonParser parser = new JsonParser();
		JsonObject object = parser.parse(json).getAsJsonObject();
		
		
		String pass = object.has("password") ? object.get("password").getAsString() : "";
		String username = object.has("username") ? object.get("username").getAsString() : "";
		
		if (pass.isEmpty() || username.isEmpty()) {
			return Response.Error("Dados inválido");
		}
		
		if (service.delete(username, pass)) {
			return Response.Ok("Usuário deletado");
		}
		else 
			return Response.Ok("Não foi possível deletar o usuário");
	}
}
