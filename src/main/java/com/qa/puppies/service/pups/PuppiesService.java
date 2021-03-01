package com.qa.puppies.service.pups;

import java.util.List;

import com.qa.puppies.domain.Puppies;

public interface PuppiesService {

	Puppies createPups(Puppies pup);

	List<Puppies> getPuppies();

	Puppies getPup(Long id);

	boolean removePup(Long id);

	Puppies updatePup(Long id, Puppies pup);

//	// OLD VERSION - for PuppiesServiceList
//	Puppies createPups(Puppies pup);
//	List<Puppies> getPuppies();
//	Puppies getPup(int id);
//	Puppies removePup(int id);
//	Puppies updatePup(int id, Puppies pup);
}
