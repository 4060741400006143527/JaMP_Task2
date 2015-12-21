package com.epam.jamp.patterns.factory;

import com.epam.jamp.patterns.factory.person.PersonService;

/**
 * Abstract Factory
 */
public interface ServiceFactory {

    PersonService createPeronService();
}
