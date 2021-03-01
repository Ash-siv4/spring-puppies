//package com.qa.puppies.service.pups;
//
//import java.util.List;
//
////import org.springframework.stereotype.Service;
//
//import com.qa.puppies.domain.Puppies;
//
////@Service
//public class PuppiesServiceList implements PuppiesService {
//
//	private List<Puppies> pups; // <- dependency
//
//	public PuppiesServiceList(List<Puppies> pups) {
//		super();
//		this.pups = pups;
//	}
//
//	public Puppies createPups(Puppies pup) {
//		this.pups.add(pup);
//		Puppies added = this.pups.get(this.pups.size() - 1);
//		return added;
//	}
//
//	public List<Puppies> getPuppies() {
//		return this.pups;
//	}
//
//	public Puppies getPup(int id) {
//		return this.pups.get(id);
//	}
//
//	public Puppies removePup(int id) {
//		return this.pups.remove(id);
//	}
//
//	public Puppies updatePup(int id, Puppies pup) {
//		this.pups.remove(id);
//		this.pups.add(id, pup);
//		return this.pups.get(id);
//	}
//
//}
