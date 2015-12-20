package com.epam.jamp.patterns.decorator;

import com.epam.jamp.patterns.model.Person;

public abstract class PersonOutputStreamDecorator implements PersonOutputStream {
    
    protected PersonOutputStream personOutputStream;
    
    public PersonOutputStreamDecorator(PersonOutputStream personOutputStream) {
        this.personOutputStream = personOutputStream;
    }
    
    @Override
    public void writePerson(Person person) {
        personOutputStream.writePerson(person);
    }    
}
