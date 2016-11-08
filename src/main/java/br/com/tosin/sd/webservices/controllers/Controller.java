package br.com.tosin.sd.webservices.controllers;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.tosin.sd.webservices.domains.BookDAO;
import br.com.tosin.sd.webservices.models.*;
import br.com.tosin.sd.webservices.utils.Constants;
import br.com.tosin.sd.webservices.utils.Util;

@Component
public class Controller {
	@Autowired
	private BookDAO db;
	private List<Reservation> reservations = new ArrayList<>();
	private OverdueList overdueList;


	public Controller() {
		super();
		this.overdueList = new OverdueList();
	}

	// ===============================================================================
	// CONSULT
	// ===============================================================================

	/**
	 * Verifica se o cliente esta da lista de inadimplentes
	 * 
	 * @param client
	 * @return TRUE Cliente inadimplente, FALSE sem prendencias
	 */
	public boolean idOverdeu(User client) {
		return overdueList.consultClient(client);
	}

	/**
	 * Verifica se o livro esta disponivel, ou seja, nao esta empresado e nao tem reservas.
	 * 
	 * @param book
	 * @return TRUE livro esta disponivel, FALSE o livro nao esta disponivel
	 */
	public boolean bookIsAvailable(Book book) {
		for (ManagementBook item : db.getBooks())
			if (book.getId() == item.getId())
				return item.isAvailable();
		return true;
	}
	
	/**
	 * Renova o livro caso seja possivel, 
	 * senao for adiciona o cliente na lista de usuario
	 * @param client
	 * @param book
	 * @return 0 Nao eh possivel renovar, 1 eh possivel renovar, 2 = ha uma reserva para o livro
	 * 3 o limite de renovacoes foi atingida.
	 * default 0
	 */
	public int renovation(User client, Book book) {
		
		//verifica se tem reserva
		if (hasReservation(book, client)){ 
			return 2;
		}

		for (ManagementBook item : db.getBooks()) {
			if (book.getId() == item.getId() && item.getClient() != null && item.getClient().getId() == client.getId()) {
				if(item.getNumRenovation() > Constants.NUM_RENOVATION) {
					return 3;
				}
				else if (Util.canRenovation(item) ) {
					item.setLoan();
					return 1;
				}
				else {
					overdueList.addClient(client);
					return 0;
				}
			}
		}
		return 0;
	}
	
	/**
	 * Adiciona livro na lista do servidor, esse metodo eh chamada pelo botao da tela para adicionar item
	 */
	public void addBook() {
		int id = db.getBooks().size();
		db.getBooks().add(Util.createNewBook(++id));
	}
	

	/**
	 * Metodo para emprestar um livro
	 * @param client
	 * @param book
	 * @return uma string com a mensagem do status do emprestivo
	 */
	public synchronized String loanBook(User client, Book book) {
		
		//verifica se tem reserva
		if (hasReservation(book, client))
			return "O livro possui uma reserva";
		int countBookClient = 0;
		
		for (ManagementBook item : db.getBooks()) {
			if (item.getClient() != null && item.getClient().equals(client)) {
				countBookClient++;
				// aproveita para ver se tem livros vencidos
				
				if(!Util.canRenovation(item)) {
					overdueList.addClient(client);
				}
			}
		}
		
		if (countBookClient > Constants.NUM_RENOVATION)
			return "Voce ja atingiu o limite";
		
		if (overdueList.consultClient(client)) {
			
			return "inadimplente";
		}
		
		for (ManagementBook managementBook : db.getBooks()) {
			if (managementBook.getId() == book.getId()) {
				managementBook.setLoan();
				managementBook.setClient(client);
				return "Emprestado";
			}
		}
		return "Nao foi possivel emprestar o livro";
	}
	

	/**
	 * Metodo de devolucao
	 * @param book
	 * @return TRUE livro foi devolvido, FALSE nao foi possivel devolver o livro
	 */
	public boolean devolution (Book book) {
		for (ManagementBook managementBook : db.getBooks()) {
			if (managementBook.getId() == book.getId()) {
				managementBook.resetLoan();
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Metodo que faz a reserva de um livro. Verifica se o livro ja esta na lista de reserva, 
	 * se o livro ja esta empresado senao adicionar o usuario da lista de reserva.
	 * @param client
	 * @param book
	 * @return Mensagem com o status da reserva, se foi possivel reservar ou nao
	 */
	public String reservation(User client, Book book) {
		
		for(Reservation item : reservations) {
			// livro ja esta reservado
			if (item.getBook().getId() == book.getId())
				return "Livro já esta reservado";
		}
		
		for (ManagementBook item : db.getBooks()) {
			if (item.getBook().getId() == book.getId()) {
				if (item.getClient() == null) {
					return "O livro esta disponivel para ser empresatado";
				}
				else if (item.getClient().getId() == client.getId())  
					return "Você já esta com este livro emprestado.";
				else {
					item.setReserve();
					item.removeReserve();
					reservations.add(new Reservation(client, book));
					return "Voce esta na lista de espera. Você será avisado quando o livro estiver diponível";
				}
			}
			
		}
		
		
		
		return "Não foi possível reservar o livro";
	}
	
	/**
	 * Atualiza o tempo de penalidade do cliente, 
	 * com isso a penalidade sempre eh renovada ate que os livros sejam devolvidos.
	 * @param client
	 * @return Data em que a penalidade eh valida no formato dd/mm/yyyy HH:mm
	 */
	public String buildTextOverdue(User client) {
		long time = overdueList.timeOverdue(client);
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(time);
		
		
		return Util.parseDate(calendar);
	}
	
	/**
	 * Verifica se um livro tem uma alguma reserva
	 * @param book
	 * @return TRUE livro tem reserva, FALSE o livro nao tem reserva
	 */
	private boolean hasReservation(Book book, User user) {
		for(Reservation item : reservations) {
			// livro ja esta reservado
			if (item.getBook().getId() == book.getId() && user.getId() != item.getClient().getId())
				return true;
		}
		return false;
	}

	// ===============================================================================
	// GETTERS
	// ===============================================================================

	/**
	 * List de livros com as informacoes basicas
	 * 
	 * @return
	 */
	public List<Book> getBooks() {
		List<Book> books = new ArrayList<>();
		for (ManagementBook item : db.getBooks()) {
			books.add(item.getBook());
		}
		return books;
	}
}
