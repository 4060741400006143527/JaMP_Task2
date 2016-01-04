package com.epam.jamp.patterns.proxy;

import com.epam.jamp.patterns.factory.person.DBPersonService;
import com.epam.jamp.patterns.factory.person.FilePersonService;
import com.epam.jamp.patterns.factory.person.PersonService;
import com.epam.jamp.patterns.model.Person;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PersonServiceProxy implements PersonService {

    private FilePersonService filePersonService;
    private DBPersonService dbPersonService;
    private final Map<String, List<Person>> cachedPersons;

    public PersonServiceProxy() {
        cachedPersons = new HashMap<>();
    }

    @Override
    public void write(Person person) {
        createFilePersonService();
        filePersonService.write(person);

        createDBPersonService();
        dbPersonService.write(person);
    }

    @Override
    public List<Person> readAll() {
        List<Person> allPersons = null;

        createFilePersonService();
        allPersons = filePersonService.readAll();
        if (addToCache(allPersons)) {
            return allPersons;
        }

        createDBPersonService();
        allPersons = dbPersonService.readAll();
        if (addToCache(allPersons)) {
            return allPersons;
        }

        return allPersons;
    }

    @Override
    public List<Person> read(String name) {
        List<Person> personsByName = null;

        createFilePersonService();
        personsByName = filePersonService.read(name);
        if (addToCache(personsByName)) {
            return personsByName;
        }

        createDBPersonService();
        personsByName = dbPersonService.read(name);
        if (addToCache(personsByName)) {
            return personsByName;
        }

        if (!cachedPersons.isEmpty()) {
            return cachedPersons.get(name);
        }

        return personsByName;
    }

    @Override
    public void rewrite(Person person) {
        createFilePersonService();
        filePersonService.rewrite(person);

        createDBPersonService();
        dbPersonService.rewrite(person);
    }

    private void createFilePersonService() {
        if (filePersonService == null) {
            filePersonService = new FilePersonService("D://3.txt");
        }
    }

    private void createDBPersonService() {
        if (dbPersonService == null) {
            dbPersonService = new DBPersonService();
        }
    }

    private boolean addToCache(List<Person> allPersons) {
        if (allPersons != null && !allPersons.isEmpty()) {
            allPersons.forEach(person -> {
                cachedPersons.putIfAbsent(person.getFirstName(), new ArrayList<>());
                cachedPersons.get(person.getFirstName()).add(person);
            });
            return true;
        }
        return false;
    }
}
