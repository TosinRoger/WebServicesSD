package br.com.tosin.sd.webservices;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.tosin.sd.webservices.domains.BookService;
import br.com.tosin.sd.webservices.models.ManagementBook;
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
		List<ManagementBook> books = service.getBooks();
		assertNotNull(books);
		assertTrue(books.size() > 0);

		ManagementBook book1 = service.getBook(1L);
		assertEquals("Title 1", book1.getTitle());

		ManagementBook book2 = service.getBooks("Title 2").get(0);
		assertEquals("Historia para ser contada 2", book2.getAbout());
	}

	@Test
	public void testSaveUpdateDeleteBook() {
		ManagementBook book = new ManagementBook(1000, "Faxe", "Beer", "Sobre cerveja", true, 0);

		boolean save = service.save(book);
		assertTrue(save);

		ManagementBook fetch = service.getBook(1000L);
		assertNotNull(fetch);

		boolean delete = service.delete(book);
		assertTrue(delete);

		ManagementBook fetch2 = service.getBook(1000L);
		assertNull(fetch2);

	}
}