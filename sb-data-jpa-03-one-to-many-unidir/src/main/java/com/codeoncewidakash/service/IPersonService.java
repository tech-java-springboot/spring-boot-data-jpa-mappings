package com.codeoncewidakash.service;

import com.codeoncewidakash.entity.Person;

public interface IPersonService {
	public String registerPersonDetails(Person person);
	public Person fetchPersonDetails(Long id);
}
