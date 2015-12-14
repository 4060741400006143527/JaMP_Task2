package com.epam.jamp.patterns.factory;

import com.epam.jamp.patterns.factory.person.FilePersonService;
import com.epam.jamp.patterns.factory.person.PersonService;

public class FileServiceFactory implements AbstractServiceFactory {

    @Override
    public PersonService createPeronService() {
        return new FilePersonService();
    }
}
