package br.com.tosin.sd.webservices.models;

/**
 * Classe Item da lista de reservas. Ela associa o cliente ao livro
 * @author tosin
 *
 */
public class Reservation {
	private User client;
	private Book book;

	public Reservation(User client, Book book) {
		super();
		this.client = client;
		this.book = book;
	}

	public User getClient() {
		return client;
	}

	public Book getBook() {
		return book;
	}
}
