package com.epam.jamp.patterns.runner;

import com.epam.jamp.patterns.facade.PersonFacade;
import com.epam.jamp.patterns.factory.person.PersonService;
import com.epam.jamp.patterns.model.Person;

import java.util.List;

public class PersonOperator {

    private PersonFacade personFacade;

    public PersonOperator(PersonService personService) {
        this.personFacade = new PersonFacade(personService);
    }

    public void imitateOperations() {
        System.out.println("Adding person with name - Alex...");
        Person personAlex = new Person("Alex", "Smith", 25, 100);
        personFacade.write(personAlex);

        System.out.println("Adding another person with name - John...");
        Person personJohn = new Person("John", "Pavlov", 30, 95);
        personFacade.write(personJohn);

        System.out.println("Getting all persons from store...");
        List<Person> allPersons = personFacade.readAll();
        System.out.println("Found persons - " + allPersons.size());
        for (Person person : allPersons) {
            System.out.println("Person from store - " + person.getFirstName());
        }

        System.out.println("Search person by name - Tom...");
        searchPersonByName("Tom");

        System.out.println("Search person by name - Alex...");
        searchPersonByName("Alex");
    }

    public void imitateIqOperations(){
        List<Person> persons = personFacade.readAll();
        Person personOne = persons.get(0);
        Person personTwo = persons.get(1);
        personFacade.moveIqAndStore(personOne, personTwo, 2);
    }

    private void searchPersonByName(String name) {
        List<Person> persons = personFacade.read(name);
        if (persons.size() == 0) {
            System.out.println("Person with " + name + " Tom not found.");
        } else {
            System.out.println("Found " + persons.size() + " person(s) with name " + name);
        }
    }
}
