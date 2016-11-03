package br.com.tosin.sd.webservices.domains;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.tosin.sd.webservices.models.User;

@Component
public class UserService {

	@Autowired
	private UserDAO db;
	
	public User fetchUser(String username, String pass) {
		return db.fetchUser(username, pass);
	}
	
	public User fetchUser(long id) {
		return db.fetchUser(id);
	}
	
	public long createOrUpdate(String username, String pass) {
		return db.createOrUpdate(username, pass);
	}
	
	public boolean delete(String username, String pass) {
		return db.delete(username, pass);
	}
	
	public User fetchUser(String authorization) {
		return db.fetchUser(authorization);
	}
}
