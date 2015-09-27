package org.hibernate.tutorial;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.tutorial.simple_books.Author;
import org.hibernate.tutorial.simple_books.Book;
import org.hibernate.tutorial.util.HibernateUtil;

public class BookManager {

	public static void main(String[] args) {
		BookManager mgr = new BookManager();

		if (args[0].equals("store")) {
			mgr.createAndStoreBook("Thinking in Java", "English");
		} else if (args[0].equals("list")) {
			List<Book> books = mgr.listBooks();
			for (Book theBook : books) {
				System.out.println("Book: " + theBook.getTitle() + " Author: " + " Language: "
						+ theBook.getLanguage());
			}
		} else if (args[0].equals("addauthortobook")) {
            Long eventId = mgr.createAndStoreBook("Effective Java", "English");
            Long personId = mgr.createAndStoreAuthor("Joshua", "Bloch", "USA");
            mgr.addAuthorToBook(personId, eventId);
            System.out.println("Added person " + personId + " to event " + eventId);
        }

		HibernateUtil.getSessionFactory().close();
	}

	private List<Book> listBooks() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        
        // Need to revisit this expression to ensure type safety.
        List<Book> result = session.createQuery("from Book").list();
        session.getTransaction().commit();
        return result;
	}

	private Long createAndStoreBook(String title, String language) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		Book theBook = new Book();
		theBook.setTitle(title);
		theBook.setLanguage(language);

		Long theBookId = (Long) session.save(theBook);

		session.getTransaction().commit();
		return theBookId;
	}
	
	private Long createAndStoreAuthor(String firstName, String lastName, String country) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		Author theAuthor = new Author();
		theAuthor.setFirstName(firstName);
		theAuthor.setLastName(lastName);
		theAuthor.setCountry(country);
		
		Long theAuthorId = (Long) session.save(theAuthor);
		
		session.getTransaction().commit();
		return theAuthorId;
	}
	
	private void addAuthorToBook(Long authorId, Long bookId) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Author anAuthor = (Author) session.load(Author.class, authorId);
        Book aBook = (Book) session.load(Book.class, bookId);
        anAuthor.getBooks().add(aBook);

        session.getTransaction().commit();
    }

}