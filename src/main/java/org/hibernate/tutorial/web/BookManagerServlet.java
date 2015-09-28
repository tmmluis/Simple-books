package org.hibernate.tutorial.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.tutorial.simple_books.Book;
import org.hibernate.tutorial.util.HibernateUtil;

public class BookManagerServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			// Begin unit of work
			HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();

			// Process request and render page
			// Write HTML header
			PrintWriter out = resp.getWriter();
			out.println("<html><head><title>Book Manager</title></head><body>");

			// Handle actions
			if ("store".equals(req.getParameter("action"))) {

				String bookTitle = req.getParameter("bookTitle");
				String bookLanguage = req.getParameter("bookLanguage");

				if ("".equals(bookTitle) || "".equals(bookLanguage)) {
					out.println("<b><i>Please enter event title and date.</i></b>");
				} else {
					createAndStoreBook(bookTitle, bookLanguage);
					out.println("<b><i>Added event.</i></b>");
				}
			}

			// Print page
			printBookForm(out);
			listBooks(out);

			// Write HTML footer
			out.println("</body></html>");
			out.flush();
			out.close();

			// End unit of work
			HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
		} catch (Exception ex) {
			HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().rollback();
			if (ServletException.class.isInstance(ex)) {
				throw (ServletException) ex;
			} else {
				throw new ServletException(ex);
			}
		}
	}

	private void printBookForm(PrintWriter out) {
		out.println("<h2>Add new book:</h2>");
		out.println("<form>");
		out.println("Title: <input name='bookTitle' length='50'/><br/>");
		out.println("Language: <input name='bookLanguage' length='50'/><br/>");
		out.println("<input type='submit' name='action' value='store'/>");
		out.println("</form>");
	}

	private void listBooks(PrintWriter out) {

		List<Book> result = HibernateUtil.getSessionFactory().getCurrentSession().createCriteria(Book.class).list();
		if (result.size() > 0) {
			out.println("<h2>Books in database:</h2>");
			out.println("<table border='1'>");
			out.println("<tr>");
			out.println("<th>Book title</th>");
			out.println("<th>Book language</th>");
			out.println("</tr>");
			Iterator<Book> it = result.iterator();
			while (it.hasNext()) {
				Book book = (Book) it.next();
				out.println("<tr>");
				out.println("<td>" + book.getTitle() + "</td>");
				out.println("<td>" + book.getLanguage() + "</td>");
				out.println("</tr>");
			}
			out.println("</table>");
		}
	}

	protected void createAndStoreBook(String title, String language) {
		Book theBook = new Book();
		theBook.setTitle(title);
		theBook.setLanguage(language);

		HibernateUtil.getSessionFactory().getCurrentSession().save(theBook);
	}

}
