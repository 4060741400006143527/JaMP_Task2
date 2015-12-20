package com.epam.jamp.patterns.decorator;

import com.epam.jamp.patterns.factory.person.FilePersonService;
import com.epam.jamp.patterns.model.Person;
import java.util.List;

public class BasePersonInputStream implements PersonInputStream {

    @Override
    public List<Person> readPersons() {
        return new FilePersonService("1.txt").readAll();
    }
}
