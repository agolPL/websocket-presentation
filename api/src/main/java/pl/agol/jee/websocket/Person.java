package pl.agol.jee.websocket;

import java.io.Serializable;

/**
 * 
 * @author Andrzej Goławski
 *
 */
public class Person implements Serializable {

	private static final long serialVersionUID = 1L;

	private final String name;

	private final String lastname;

	public Person(String name, String lastname) {
		this.name = name;
		this.lastname = lastname;
	}

	@Override
	public String toString() {
		return name + " " + lastname;
	}
}
