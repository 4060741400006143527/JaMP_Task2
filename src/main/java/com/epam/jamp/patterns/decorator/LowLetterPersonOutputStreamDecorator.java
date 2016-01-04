package com.epam.jamp.patterns.decorator;

import com.epam.jamp.patterns.model.Person;

public class LowLetterPersonOutputStreamDecorator implements FilePersonOutputStream {
    
    private final FilePersonOutputStream personOutputStream;
    
    public LowLetterPersonOutputStreamDecorator(FilePersonOutputStream personOutputStream) {
        this.personOutputStream = personOutputStream;
    }

    @Override
    public void writePerson(Person person) {
        lowerCaseName(person);
        personOutputStream.writePerson(person); 
    }

 private Person lowerCaseName(Person person) {

        String name = person.getFirstName();
        String s1 = name.substring(0, 1).toLowerCase();
        String nameLowercased = s1 + name.substring(1);
        person.setFirstName(nameLowercased);

        return person;
    }
    
    
    
}
