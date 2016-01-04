package com.epam.jamp.patterns.decorator;

import com.epam.jamp.patterns.model.Person;
import java.util.List;

public class CapitalLetterPersonInputStreamDecorator implements FilePersonInputStream {
    
    private final FilePersonInputStream personInputStream;

    public CapitalLetterPersonInputStreamDecorator(FilePersonInputStream personInputStream) {
         this.personInputStream = personInputStream;
    }

    @Override
    public List<Person> readPersons() {
        List<Person> persons = personInputStream.readPersons();
        persons.forEach(person -> capitalizeName(person));

        return persons;
    }

    private Person capitalizeName(Person person) {

        String name = person.getFirstName();
        String s1 = name.substring(0, 1).toUpperCase();
        String nameCapitalized = s1 + name.substring(1);
        person.setFirstName(nameCapitalized);

        return person;
    }

}
