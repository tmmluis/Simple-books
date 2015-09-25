package org.hibernate.tutorial;

import java.util.List;

import org.hibernate.Session;

import org.hibernate.tutorial.simple_books.Book;
import org.hibernate.tutorial.util.HibernateUtil;

public class BookManager {

	public static void main(String[] args) {
		BookManager mgr = new BookManager();

		if (args[0].equals("store")) {
			mgr.createAndStoreBook("Thinking in Java", "Bruce Eckel", "English");
		} else if (args[0].equals("list")) {
			List<Book> books = mgr.listBooks();
			for (Book theBook : books) {
				System.out.println("Book: " + theBook.getTitle() + " Author: " + theBook.getAuthor() + " Language: "
						+ theBook.getLanguage());
			}
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

	private void createAndStoreBook(String title, String author, String language) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		Book theBook = new Book();
		theBook.setTitle(title);
		theBook.setAuthor(author);
		theBook.setLanguage(language);

		session.save(theBook);

		session.getTransaction().commit();
	}

}