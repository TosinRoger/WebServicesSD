package br.com.tosin.sd.webservices.domains;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.tosin.sd.webservices.controllers.Controller;
import br.com.tosin.sd.webservices.models.Book;
import br.com.tosin.sd.webservices.models.User;

@Component
public class LoanService {
	
	@Autowired
	private Controller controller;
	
	
	public String loan(User user, Book book) {
		if (!book.isAvailable()) {
			return "Livro não está disponível para emprestimo";
		}
		else if (user.isOverdue()) {
			return "O usuário possui pendecias com a biblioteca";
		}
		else if (controller.idOverdeu(user)) {
			return "Voce esta inadimplemente ate " + controller.buildTextOverdue(user);
		}
		else if(!controller.bookIsAvailable(book)) {
			return "Livro esta emprestado, voce podera entrar a lista de espera"; 
		}
		
		else {
			String done = controller.loanBook(user, book);
			if(done.contains("Emprestado"))
				return ("Livro emprestado");
			else if(done.equals("atingiu o limite"))
				return ("Voce ja atingui o limite de emprestimo");
			else if(done.equals("inadimplente"))
				return ("Voce esta inadimplemente ate " + controller.buildTextOverdue(user));
			else 
				return ("done");
		}
		
//		return "Deu ruim";
	}
	
	/**
	 * Metodo chamado pelo cliente para fazer a renovacao do livro
	 * @param Book é passado o livro que ele escolheu para  faze a renovacao
	 */
	public String renovation(User user, Book book) {
		// TODO Auto-generated method stub
		System.out.println("Server: renovation is request");
		int response = controller.renovation(user, book);
		if(1 == response) {
			return ("Livro renovado");
		}
		else if (response == 2){
			return ("O livro esta reservado");
		}
		else if (response == 3){
			return ("Voce atingiu o limite de reservas");
		}
		else {
			return ("Voce nao pode renovar o livro");
		}
	}

	/**
	 * Metodo chamado pelo cliente para fazer a devolucao
	 * @param Book é passado o livro que ele escolheu para faze a devolucao
	 */
	public String devolution(User user, Book book) {
		// TODO Auto-generated method stub
		System.out.println("Server: devolution is request");
		if(controller.devolution(book)) {
			return ("Livro devolvido");
		}
		else {
			return ("Não foi possivel devolver o livro");
		}
	}

	
	/**
	 * Metodo chamado pelo cliente para fazer a reserva de um livro
	 * @param Book é passado o livro que ele escolheu para ser reservado
	 */
	public String reservation(User user, Book book) {
		// TODO Auto-generated method stub
		System.out.println("Server: reservation is request");
		String msg = controller.reservation(user, book);
		return (msg);
		
	}

}
