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
	
	/**
	 * Busca a lista de usuarios
	 * @param username
	 * @param pass
	 * @return
	 */
	public User fetchUser(String username, String pass) {
		for (User item : this.users) {
			if (item.getUsername().equals(username) && item.getPass().equals(pass)) {
				return item;
			}
		}
		return null;
	}
	
	/**
	 * Busca a lista de usuarios
	 * @param id
	 * @return
	 */
	public User fetchUser(long id) {
		for (User item : this.users) {
			if (item.getId() == id) {
				return item;
			}
		}
		return null;
	}
	
	/**
	 * Busca a lista de usuarios
	 * @param authorization
	 * @return
	 */
	public User fetchUser(String authorization) {
		for (User item : this.users) 
			if (item.getBase64().equals(authorization))
				return item;
		return null;
	}
	
	/**
	 * Cria ou atualiza um usuario
	 * @param username
	 * @param pass
	 * @return 1 se criou, 0 se atualizou
	 */
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
	
	/**
	 * Remove um usuario
	 * @param username
	 * @param pass
	 * @return
	 */
	public boolean delete(String username, String pass) {
		User user = fetchUser(username, pass);
		if (user == null)
			return false;
		else
			return this.users.remove(user);
	}
}
