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

	public Puppies(Long id, String name, int age, String breed) {
		super();
		this.id = id;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + ((breed == null) ? 0 : breed.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	// need this - else in PuppiesServiceDBUnitTest, it may return a null for some
	// methods when comparing 2 objects which are equivalent but not literally the
	// same
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Puppies other = (Puppies) obj;
		if (age != other.age)
			return false;
		if (breed == null) {
			if (other.breed != null)
				return false;
		} else if (!breed.equals(other.breed))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Puppies [id=" + id + ", name=" + name + ", age=" + age + ", breed=" + breed + "]";
	}

}
