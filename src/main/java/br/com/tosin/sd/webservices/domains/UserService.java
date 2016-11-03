package br.com.tosin.sd.webservices.domains;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.tosin.sd.webservices.models.User;

@Component
public class UserService {

	@Autowired
	private UserDAO db;
	
	/**
	 * Busca a lista de usuarios
	 * @param username
	 * @param pass
	 * @return
	 */
	public User fetchUser(String username, String pass) {
		return db.fetchUser(username, pass);
	}
	
	/**
	 * Busca a lista de usuarios
	 * @param id
	 * @return
	 */
	public User fetchUser(long id) {
		return db.fetchUser(id);
	}
	
	/**
	 * Busca a lista de usuarios
	 * @param authorization
	 * @return
	 */
	public User fetchUser(String authorization) {
		return db.fetchUser(authorization);
	}
	
	/**
	 * Cria ou atualiza o usuario
	 * @param username
	 * @param pass
	 * @return 1 se criou, 0 se atualizou
	 */
	public long createOrUpdate(String username, String pass) {
		return db.createOrUpdate(username, pass);
	}
	
	/**
	 * Remove um usuario
	 * @param username
	 * @param pass
	 * @return
	 */
	public boolean delete(String username, String pass) {
		return db.delete(username, pass);
	}
	
}
