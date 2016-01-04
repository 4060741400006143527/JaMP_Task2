package com.epam.jamp.patterns.decorator;

import com.epam.jamp.patterns.factory.person.FilePersonService;
import com.epam.jamp.patterns.model.Person;
import java.io.FileInputStream;
import java.util.List;

public class BasePersonInputStream implements FilePersonInputStream {

    @Override
    public List<Person> readPersons() {
        return new FilePersonService("1.txt").readAll();
    }
}
