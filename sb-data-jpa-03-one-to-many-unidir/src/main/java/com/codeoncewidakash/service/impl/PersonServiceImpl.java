package com.codeoncewidakash.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codeoncewidakash.entity.Person;
import com.codeoncewidakash.exception.CardDetailsNotFoundException;
import com.codeoncewidakash.exception.PersonNotFoundException;
import com.codeoncewidakash.repo.PersonRepository;
import com.codeoncewidakash.service.IPersonService;

@Service
public class PersonServiceImpl implements IPersonService {
	
	@Autowired
	private PersonRepository personRepository;
	
	@Override
	public String registerPersonDetails(Person person) {
		var registeredPerson = personRepository.save(person);
		return new StringBuilder("Person registered with id ").append(registeredPerson.getPersonId()).toString();
	}
	
	@Override
	public Person fetchPersonDetails(Long id) {
		return personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException("Person Not Found With Id: "+ id));
	}
	
	@Override
	public String deleteOneOfTheCardDetailFromCollectionOfCardIdAssociateWithPerson(Long personId, Long cardId) {
		var person = personRepository.findById(personId).orElseThrow(() -> new PersonNotFoundException("Person Not Found With Id: "+ personId));
		if(person.getIdCards().removeIf(c -> c.getId().equals(cardId)))
			personRepository.save(person);
		else 
			throw new CardDetailsNotFoundException("Card Details Not Found With Id: "+cardId);
		
		return new StringBuilder("Card details are deleted for the id: ").append(cardId).toString();
	}
}
