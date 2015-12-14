package com.epam.jamp.patterns.factory;

import com.epam.jamp.patterns.factory.person.DBPersonService;
import com.epam.jamp.patterns.factory.person.PersonService;

public class DBServiceFactory implements AbstractServiceFactory {

    @Override
    public PersonService createPeronService() {
        return new DBPersonService();
    }
}
