package com.qa.puppies.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.qa.puppies.domain.Puppies;
import com.qa.puppies.service.pups.PuppiesService;

@RestController
@CrossOrigin // enable 'CORS' (so that scripts running on a browser client can interact with
				// resources from a different origin); else error = No
				// 'Access-Control-Allow-Origin'
public class PuppiesController {

	private PuppiesService service; // <- dependency

	public PuppiesController(PuppiesService service) {
		super();
		this.service = service;
	}

//	private List<Puppies> pups = new ArrayList<>(); // <- dependency

//	public PuppiesController(List<Puppies> pups) {
//		super();
//		this.pups = pups;
//	}

//	// Create -> POST = create an entry
//	@PostMapping("/createPups")
//	public void createPups(@RequestBody Puppies pup) {
//		this.pups.add(pup);
//	}

	// Set status code (seen in postman)= before was: "200 Ok" --> changed to "201
	// Created"
	@PostMapping("/createPups")
	public ResponseEntity<Puppies> createPups(@RequestBody Puppies pup) { // json format
//	public ResponseEntity<Puppies> createPups( Puppies pup) { //x-www-form-urlencoded format
//		this.pups.add(pup);
//		Puppies added = this.pups.get(this.pups.size() - 1);
//		return new ResponseEntity<Puppies>(added, HttpStatus.CREATED);
		return new ResponseEntity<Puppies>(this.service.createPups(pup), HttpStatus.CREATED);
	}

//	// Read -> GET = retrieve all data
//	@GetMapping("/getPuppies")
//	public List<Puppies> getPuppies() {
//		return this.pups;
//	}

	@GetMapping("/getPuppies")
	public ResponseEntity<List<Puppies>> getPuppies() {
//		return ResponseEntity.ok(this.pups);// do like this because passing in a List to ResponseEntity
		return ResponseEntity.ok(this.service.getPuppies());
//		return new ResponseEntity<List<Puppies>>(this.service.getPuppies(),HttpStatus.OK);
	}

//	// Read -> GET = retrieve single data
//	@GetMapping("/getPup/{id}")
//	public Puppies getPup(@PathVariable int id) {
//		return this.pups.get(id);
//	}

	@GetMapping("/getPup/{id}")
	public ResponseEntity<Puppies> getPup(@PathVariable Long id) {
//		return new ResponseEntity<Puppies>(this.pups.get(id), HttpStatus.OK);
		return new ResponseEntity<Puppies>(this.service.getPup(id), HttpStatus.OK);
//		return ResponseEntity.ok(this.service.getPup(id));
	}

	// Delete -> DELETE = remove
	@DeleteMapping("/removePup/{id}")
	public boolean removePup(@PathVariable Long id) {
//		return this.pups.remove(id);
//		return !this.service.removePup(id); // return true if doesn't exist
		return this.service.removePup(id);// return false if it doesn't exist
	}

	// Update -> PUT = replacing
//	@PutMapping("/updatePup")
//	public Puppies updatePup(@PathParam("id") Long id, @RequestBody Puppies pup) {
//		// remove existing pup with this id
//		this.pups.remove(id);
//		// add a new pup in it's place
//		this.pups.add(id, pup);
//		// get the updated version
//		return this.pups.get(id);
	@PutMapping("/updatePup/{id}")
	public Puppies updatePup(@PathVariable Long id, @RequestBody Puppies pup) {
		return this.service.updatePup(id, pup);
	}
}
