package com.codeoncewidakash.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codeoncewidakash.entity.Person;
import com.codeoncewidakash.exception.PersonNotFoundException;
import com.codeoncewidakash.service.IPersonService;

@RestController
@RequestMapping("/api/v1/person")
public class PersonController {

	@Autowired
	private IPersonService personService;

	@PostMapping("/register")
	public ResponseEntity<String> registerPersonDetails(@RequestBody Person person) {
		return new ResponseEntity<>(personService.registerPersonDetails(person), HttpStatus.CREATED);
	}

	@GetMapping("/fetch/{id}")
	public ResponseEntity<Person> fetchPersonDetails(@PathVariable("id") Long id) {
		Person fetchPersonDetails;
		try {
			fetchPersonDetails = personService.fetchPersonDetails(id);
		} catch (PersonNotFoundException pnfe) {
			throw pnfe;
		}
		return new ResponseEntity<>(fetchPersonDetails, HttpStatus.OK);
	}
}
