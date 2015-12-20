package com.epam.jamp.patterns.factory.person;


import com.epam.jamp.patterns.db.PersonDao;
import com.epam.jamp.patterns.model.Person;

import java.util.List;

public class DBPersonService implements PersonService {

    private final PersonDao personDao = new PersonDao();

    @Override
    public void write(Person person) {
        Validator.validatePerson(person);
        try {
            personDao.save(person);
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    @Override
    public void rewrite(Person person){
        Validator.validatePerson(person);
        try {
            personDao.update(person);
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    @Override
    public List<Person> readAll() {
        List<Person> persons = null;
        try {
            persons = personDao.getAll();
        } catch (Exception e) {
            System.err.println(e);
        }
        return persons;
    }

    @Override
    public List<Person> read(String name) {
        Validator.validateName(name);
        List<Person> persons = null;
        try {
            persons = personDao.find(name);
        } catch (Exception e) {
            System.err.println(e);
        }
        return persons;
    }
}
