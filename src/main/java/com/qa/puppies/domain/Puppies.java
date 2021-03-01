package com.qa.puppies.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // marks as table
public class Puppies {
	@Id // marks as primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO INCREMENT
	private Long id;// get javax import

	private String name;
	private int age;
	private String breed;

	public Puppies() {
		super();
		// Default constructor
		// REQUIRED
	}

	public Puppies(String name, int age, String breed) {
		super();
		this.name = name;
		this.age = age;
		this.breed = breed;
	}

	// GETTERS AND SETTERS REQUIRED
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getBreed() {
		return breed;
	}

	public void setBreed(String breed) {
		this.breed = breed;
	}

}
