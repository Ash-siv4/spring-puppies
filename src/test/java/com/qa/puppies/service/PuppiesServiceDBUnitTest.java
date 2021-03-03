package com.qa.puppies.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.qa.puppies.domain.Puppies;
import com.qa.puppies.repos.PuppiesRepo;
import com.qa.puppies.service.pups.PuppiesServiceDB;

@SpringBootTest
@ActiveProfiles("test")
public class PuppiesServiceDBUnitTest {

	@Autowired
	private PuppiesServiceDB service;

	@MockBean
	private PuppiesRepo repo;

	@Test
	void testCreate() {
		// GIVEN is our testing data - you can make this a final local variable if you
		// want, e.g.: final Puppies newPup = new Puppies("Sparky", 1, "Jack R");
		Puppies newPup = new Puppies("Sparky", 1, "Jack R");
		Puppies savedPup = new Puppies(1L, "Sparky", 1, "Jack R");

		// WHEN
		Mockito.when(this.repo.save(newPup)).thenReturn(savedPup);

		// THEN
		assertThat(this.service.createPups(newPup)).isEqualTo(savedPup);

		// verify that our repo was accessed exactly once
		Mockito.verify(this.repo, Mockito.times(1)).save(newPup);
	}

	@Test
	void testRead() {
		List<Puppies> puppies = new ArrayList<>();
		puppies.add(new Puppies("Lyca", 10, "Jack Russell"));

		Mockito.when(this.repo.findAll()).thenReturn(puppies);

		assertThat(this.service.getPuppies()).isEqualTo(puppies);

		Mockito.verify(this.repo, Mockito.times(1)).findAll();
	}

	@Test
	void testReadOne() {
		Long id = 1L;
		Optional<Puppies> optPup = Optional.of(new Puppies(id, "Lucky", 1, "Staffie"));

		Puppies readPup = new Puppies(id, "Lucky", 1, "Staffie");

		Mockito.when(this.repo.findById(id)).thenReturn(optPup);

		assertThat(this.service.getPup(id)).isEqualTo(readPup);

		Mockito.verify(this.repo, Mockito.times(1)).findById(id);
	}

	@Test
	void testDelete() {
		Long id = 1L;

		Mockito.when(this.repo.existsById(id)).thenReturn(true, false);

		assertThat(this.service.removePup(id)).isFalse();

		Mockito.verify(this.repo, Mockito.times(1)).deleteById(id);
		Mockito.verify(this.repo, Mockito.times(1)).existsById(id);
		;

	}

	@Test
	void testupdate() {
		// GIVEN
		// ID
		Long id = 1L;
		// NEW PUPPY DATA
		Puppies newPup = new Puppies("Snoopy", 1, "Jack R");
		// OPTIONAL PUPPY (basically existing penguin in a fancy wrapper)
		Optional<Puppies> optPup = Optional.of(new Puppies(id, null, 0, null));// can be any values in new Puppies(...)
		// UPDATED PUPPY
		Puppies updatedPup = new Puppies(id, newPup.getName(), newPup.getAge(), newPup.getBreed());

		// WHEN
		Mockito.when(this.repo.findById(id)).thenReturn(optPup);
		// MAKE SURE THE MOCK INPUT HAS AN equals() METHOD
		Mockito.when(this.repo.save(updatedPup)).thenReturn(updatedPup);

		// THEN
		assertThat(this.service.updatePup(id, newPup)).isEqualTo(updatedPup);

		// verify
		Mockito.verify(this.repo, Mockito.times(1)).findById(id);
		Mockito.verify(this.repo, Mockito.times(1)).save(updatedPup);
	}
}
