package br.com.tosin.sd.webservices;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.tosin.sd.webservices.domains.BookService;
import br.com.tosin.sd.webservices.models.Book;
import junit.framework.TestCase;

public class BookTest extends TestCase {

	// private BookService service = new BookService();
	@Autowired
	private BookService service;

	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		
		service = (BookService) SpringUtil.getInstance().getBean(BookService.class);
	}

	@Test
	public void testFindBook() {
		List<Book> books = service.getBooks();
		assertNotNull(books);
		assertTrue(books.size() > 0);

		Book book1 = service.getBook(1L);
		assertEquals("Title 1", book1.getTitle());

		Book book2 = service.getBooks("Title 2").get(0);
		assertEquals("Historia para ser contada 2", book2.getAbout());
	}

	@Test
	public void testSaveUpdateDeleteBook() {
		Book book = new Book(1000, "Faxe", "Beer", "Sobre cerveja", true, 0);

		boolean save = service.save(book);
		assertTrue(save);

		Book fetch = service.getBook(1000L);
		assertNotNull(fetch);

		boolean delete = service.delete(book);
		assertTrue(delete);

		Book fetch2 = service.getBook(1000L);
		assertNull(fetch2);

	}
}