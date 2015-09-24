package org.hibernate.tutorial;

import org.hibernate.Session;

import org.hibernate.tutorial.simple_books.Book;
import org.hibernate.tutorial.util.HibernateUtil;

public class BookManager {

    public static void main(String[] args) {
        BookManager mgr = new BookManager();

        if (args[0].equals("store")) {
            mgr.createAndStoreBook("Thinking in Java", "Bruce Eckel", "English");
        }

        HibernateUtil.getSessionFactory().close();
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