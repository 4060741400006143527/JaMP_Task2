package com.epam.jamp.patterns.factory.person;

import com.epam.jamp.patterns.model.Person;

public final class PersonValidator {

    private PersonValidator() {
        throw new UnsupportedOperationException("Private constructor call detected");
    }

    public static void validateName(String name) {
        if (name == null || name.length() == 0) {
            throw new RuntimeException("Incorrect parameter");
        }
    }

    public static void validatePerson(Person person) {
        if (person == null) {
            throw new RuntimeException("Incorrect parameter");
        }
    }
}
