package com.epam.jamp.patterns.factory;

import com.epam.jamp.patterns.factory.person.PersonService;

public interface AbstractServiceFactory {

    PersonService createPeronService();
}
