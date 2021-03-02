package com.qa.puppies.service.pups;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.qa.puppies.domain.Puppies;
import com.qa.puppies.repos.PuppiesRepo;

@Service
public class PuppiesServiceDB implements PuppiesService {

	private PuppiesRepo repo;

	public PuppiesServiceDB(PuppiesRepo repo) {
		super();
		this.repo = repo;
	}

	@Override
	public Puppies createPups(Puppies pup) { // pup with no id (not saved)
		return this.repo.save(pup); // pup with an id (has been saved)
	}

	@Override
	public List<Puppies> getPuppies() {
		return this.repo.findAll();
	}

	@Override
	public Puppies getPup(Long id) {
		Optional<Puppies> optPups = this.repo.findById(id);
		return optPups.orElse(null);
	}

	@Override
	public boolean removePup(Long id) {
		this.repo.deleteById(id);
//		return this.repo.existsById(id); // returns true if not removed pup 
		return !this.repo.existsById(id); // returns false if not removed pup
	}

	@Override
	public Puppies updatePup(Long id, Puppies pup) {
		Optional<Puppies> optionalPup = this.repo.findById(id);
		Puppies exist = optionalPup.get();
//		Puppies exist = this.getPup(id);
		exist.setAge(pup.getAge());
		exist.setName(pup.getName());
		exist.setBreed(pup.getBreed());
		return this.repo.save(exist);
	}

}
