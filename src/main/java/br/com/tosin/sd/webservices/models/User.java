package br.com.tosin.sd.webservices.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.tomcat.util.codec.binary.Base64;

public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	private String username;
	private String pass;
	private List<Book> booksLoan;
	private boolean overdue;

	public User(String username, String pass) {
		super();
		this.username = username;
		this.pass = pass;
		this.id = new Random().nextInt(Integer.MAX_VALUE);
		this.overdue = false;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public boolean isOverdue() {
		return overdue;
	}

	public void setOverdue(boolean overdue) {
		this.overdue = overdue;
	}

	public List<Book> getBooksLoan() {
		if (this.booksLoan == null)
			this.booksLoan = new ArrayList<>();
		return booksLoan;
	}
	
	public String getBase64() {
		String temp = this.username + ":" + this.pass;

		byte[] encode = Base64.encodeBase64(temp.getBytes());
		String end = "Basic " + new String(encode);
		return end;
	}

}
