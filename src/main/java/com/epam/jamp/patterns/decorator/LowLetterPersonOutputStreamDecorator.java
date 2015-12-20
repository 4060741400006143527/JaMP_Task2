package com.epam.jamp.patterns.decorator;

import com.epam.jamp.patterns.model.Person;

public class LowLetterPersonOutputStreamDecorator extends PersonOutputStreamDecorator{

    public LowLetterPersonOutputStreamDecorator(PersonOutputStream personOutputStream) {
        super(personOutputStream);
    }

    @Override
    public void writePerson(Person person) {
        lowerCaseName(person);
        super.writePerson(person); 
    }

 private Person lowerCaseName(Person person) {

        String name = person.getFirstName();
        String s1 = name.substring(0, 1).toLowerCase();
        String nameLowercased = s1 + name.substring(1);
        person.setFirstName(nameLowercased);

        return person;
    }
    
    
    
}
