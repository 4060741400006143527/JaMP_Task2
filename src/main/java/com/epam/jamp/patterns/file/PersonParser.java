package com.epam.jamp.patterns.file;

import com.epam.jamp.patterns.factory.person.model.Person;

public class PersonParser implements Parser<Person> {

    @Override
    public Person parse(String string) {
        return new PersonFileItemParser().parseItem(string);
    }
}
