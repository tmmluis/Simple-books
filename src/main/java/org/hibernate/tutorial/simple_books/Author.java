package org.hibernate.tutorial.simple_books;

import java.util.HashSet;
import java.util.Set;

public class Author {
	
	private Long id;
	private String firstName;
	private String lastName;
	private String country;
	
	private Set<Book> books = new HashSet<Book>();
	
	public Author() {	
	}

	public Long getId() {
		return id;
	}

	private void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Set<Book> getBooks() {
		return books;
	}

	public void setBooks(Set<Book> books) {
		this.books = books;
	}
	
	

}
