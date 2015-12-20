package com.epam.jamp.patterns.decorator;

import com.epam.jamp.patterns.model.Person;
import java.util.List;

public interface PersonOutputStream {
    
   void writePerson(Person person);
}
