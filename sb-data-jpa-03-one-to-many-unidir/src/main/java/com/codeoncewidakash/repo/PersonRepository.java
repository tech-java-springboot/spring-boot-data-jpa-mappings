package com.codeoncewidakash.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codeoncewidakash.entity.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
