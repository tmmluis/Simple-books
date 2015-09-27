package org.hibernate.tutorial.simple_books;

/**
 * This class uses standard JavaBean naming conventions for property getter and
 * setter methods, as well as private visibility for the fields. Although this
 * is the recommended design, it is not required. Hibernate can also access
 * fields directly, the benefit of accessor methods is robustness for
 * refactoring.
 * 
 * @author tmmluis
 *
 */
public class Book {

	/*
	 * It is recommended that we declare consistently-named identifier
	 * properties on persistent classes and that we use a nullable (i.e.,
	 * non-primitive) type."
	 */
	private Long id;

	private String title;
	private String language;

	/*
	 * Implement a no-argument constructor. All persistent classes must have a
	 * default constructor (which can be non-public) so that Hibernate can
	 * instantiate them using java.lang.reflect.Constructor.newInstance(). It is
	 * recommended that this constructor be defined with at least package
	 * visibility in order for runtime proxy generation to work properly.
	 * 
	 */
	public Book() {
	}

	public Long getId() {
		return id;
	}

	/*
	 * we usually do not manipulate the identity of an object, hence the setter
	 * method should be private. Only Hibernate will assign identifiers when an
	 * object is saved. Hibernate can access public, private, and protected
	 * accessor methods, as well as public, private and protected fields
	 * directly. The choice is up to you and you can match it to fit your
	 * application design.
	 */
	private void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

}
