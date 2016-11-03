package br.com.tosin.sd.webservices.domains;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.tosin.sd.webservices.models.User;

@Component
public class UserDAO {
	
	private List<User> users;
	
	public UserDAO() {
		this.users = new ArrayList<>();
	}
	
	public User fetchUser(String username, String pass) {
		for (User item : this.users) {
			if (item.getUsername().equals(username) && item.getPass().equals(pass)) {
				return item;
			}
		}
		return null;
	}
	
	public User fetchUser(long id) {
		for (User item : this.users) {
			if (item.getId() == id) {
				return item;
			}
		}
		return null;
	}
	
	public User fetchUser(String authorization) {
		for (User item : this.users) 
			if (item.getBase64().equals(authorization))
				return item;
		return null;
	}
	
	public long createOrUpdate(String username, String pass) {
		User user = fetchUser(username, pass);
		if (user == null) {
			user = new User(username, pass);
			this.users.add(user);
			return 1;
		}
		else {
			this.users.remove(user);
			user.setPass(pass);
			this.users.add(user);
			return 0;
		}
	}
	
	public boolean delete(String username, String pass) {
		User user = fetchUser(username, pass);
		if (user == null)
			return false;
		else
			return this.users.remove(user);
	}
}
