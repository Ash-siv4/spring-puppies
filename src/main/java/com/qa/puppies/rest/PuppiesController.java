package com.qa.puppies.rest;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
public class PuppiesController {

	private PuppiesService service; // <- dependency

	public PuppiesController(PuppiesService service) {
		super();
		this.service = service;
	}

	// Set status code (seen in postman)= before was: "200 Ok" --> changed to "201
	// Created"
	@PostMapping("/createPups")
	public ResponseEntity<Puppies> createPups(@RequestBody Puppies pup) {
		return new ResponseEntity<Puppies>(this.service.createPups(pup), HttpStatus.CREATED);
	}

	// Read -> GET = retrieve all data
	@GetMapping("/getPuppies")
	public ResponseEntity<List<Puppies>> getPuppies() {
		return ResponseEntity.ok(this.service.getPuppies());
	}

	// Read -> GET = retrieve single data
	@GetMapping("/getPup/{id}")
	public ResponseEntity<Puppies> getPup(@PathVariable Long id) {
		return new ResponseEntity<Puppies>(this.service.getPup(id), HttpStatus.OK);
	}

	// Delete -> DELETE = remove
	@DeleteMapping("/removePup/{id}")
	public boolean removePup(@PathVariable Long id) {
		return this.service.removePup(id);
	}

	// Update -> PUT = replacing
	@PutMapping("/updatePup/{id}")
	public Puppies updatePup(@PathVariable Long id, @RequestBody Puppies pup) {
		return this.service.updatePup(id, pup);
	}
}
