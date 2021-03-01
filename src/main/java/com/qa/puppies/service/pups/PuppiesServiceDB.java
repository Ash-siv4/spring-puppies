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
	public Puppies createPups(Puppies pup) {
		// TODO Auto-generated method stub
		return this.repo.save(pup);
	}

	@Override
	public List<Puppies> getPuppies() {
		// TODO Auto-generated method stub
		return this.repo.findAll();
	}

	@Override
	public Puppies getPup(Long id) {
		// TODO Auto-generated method stub
		Optional <Puppies> optPups = this.repo.findById(id);
		return optPups.orElse(null);
	}

	@Override
	public boolean removePup(Long id) {
		// TODO Auto-generated method stub
		this.repo.deleteById(id);
		return this.repo.existsById(id);
	}

	@Override
	public Puppies updatePup(Long id, Puppies pup) {
		// TODO Auto-generated method stub
		Puppies exist = this.getPup(id);
		exist.setAge(pup.getAge());
		exist.setName(pup.getName());
		exist.setBreed(pup.getBreed());
		return this.repo.save(exist);
	}

}
