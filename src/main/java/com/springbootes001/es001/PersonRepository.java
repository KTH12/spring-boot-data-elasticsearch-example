package com.springbootes001.es001;

import org.springframework.data.repository.Repository;

import java.util.List;

public interface PersonRepository extends Repository<Person, String> {
    List<Person> findByLastName(String lastName);

    Person findById(Long id);
}
