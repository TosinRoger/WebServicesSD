package br.com.tosin.sd.webservices.models;

/**
 * Classe Item da lista de inadimplentes. Ela associa o cliente ao livro.
 * @author tosin
 *
 */
public class Overdue {
	
	private User client;
	private long delayTime;
	
	
	public Overdue(User client, long delayTime) {
		super();
		this.client = client;
		this.delayTime = delayTime;
	}


	public User getClient() {
		return client;
	}


	public long getDelayTime() {
		return delayTime;
	}
	
	
}
