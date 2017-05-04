package org.xx.recommender;

public class Book {
	private final int id;
	private final String name;

	public Book(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public String toString(){
		return name;
	}
}
