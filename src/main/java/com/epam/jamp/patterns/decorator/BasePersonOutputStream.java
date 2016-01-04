package com.epam.jamp.patterns.decorator;

import com.epam.jamp.patterns.factory.person.FilePersonService;
import com.epam.jamp.patterns.model.Person;

public class BasePersonOutputStream implements FilePersonOutputStream{

    @Override
    public void writePerson(Person person) {
        new FilePersonService("1.txt").write(person);
    }    
}
