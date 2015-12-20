package com.epam.jamp.patterns.decorator;

import com.epam.jamp.patterns.model.Person;
import java.util.List;

public abstract class PersonInputStreamDecorator implements PersonInputStream {

    protected PersonInputStream personInputStream;

    public PersonInputStreamDecorator(PersonInputStream personInputStream) {
        this.personInputStream = personInputStream;
    }

    @Override
    public List<Person> readPersons() {
        return personInputStream.readPersons();
    }
}
