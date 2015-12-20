package com.epam.jamp.patterns.factory.person;

import com.epam.jamp.patterns.model.Person;

import java.util.List;

public interface PersonService {

    void write(Person person);

    List<Person> readAll();

    List<Person> read(String name);

    void rewrite(Person person);
}
