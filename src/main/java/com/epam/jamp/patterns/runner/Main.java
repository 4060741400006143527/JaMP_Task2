package com.epam.jamp.patterns.runner;

import com.epam.jamp.patterns.adapter.LinkedListAdapter;
import com.epam.jamp.patterns.adapter.Stack;
import com.epam.jamp.patterns.bridge.PersistentAccount;
import com.epam.jamp.patterns.bridge.PersistentAccountImpl;
import com.epam.jamp.patterns.composite.Directory;
import com.epam.jamp.patterns.composite.File;
import com.epam.jamp.patterns.db.AccountDaoImpl;
import com.epam.jamp.patterns.db.BillDaoImpl;
import com.epam.jamp.patterns.decorator.*;
import com.epam.jamp.patterns.factory.ServiceFactory;
import com.epam.jamp.patterns.factory.ServiceType;
import com.epam.jamp.patterns.factory.person.PersonService;
import com.epam.jamp.patterns.model.Person;
import com.epam.jamp.patterns.observer.*;
import com.epam.jamp.patterns.proxy.PersonServiceProxy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] arg) throws SQLException, ClassNotFoundException {

        // Adapter

        Stack<Long> arrayListAdapter = new LinkedListAdapter<>();
        arrayListAdapter.push(1534L);
        arrayListAdapter.push(644L);
        arrayListAdapter.push(555L);

        System.out.println(arrayListAdapter.pop());
        System.out.println(arrayListAdapter.pop());

        // Observer

        String fileName = "observer.txt";

        TextData textData = new TextData();

        WordCounter wordCounter = new WordCounter();
        textData.registerObserver(wordCounter);

        NumberCounter numberCounter = new NumberCounter();
        textData.registerObserver(numberCounter);

        LongestWordKeeper longestWordKeeper = new LongestWordKeeper();
        textData.registerObserver(longestWordKeeper);

        WordReverser wordReverser = new WordReverser();
        textData.registerObserver(wordReverser);

        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            stream.forEach(line -> {
                String[] wordsInLine = line.split(" ");
                for (String word : wordsInLine) {
                    textData.setWord(word);
                }
            });

        } catch (IOException e) {
            System.err.println(e);
        }

        System.out.println(wordCounter.getWordCount());
        System.out.println(numberCounter.getNumberCount());
        System.out.println(longestWordKeeper.getLongestWord());
        System.out.println(wordReverser.getReversedWord());


        // Abstract Factory & Facade & Template Method

        try {
            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
                System.out.println("Choice person manager type or print 'exit' for exit:");
                for (ServiceType serviceType : ServiceType.values()) {
                    System.out.println(serviceType.getServiceNumber());
                }
                String userInput = bufferedReader.readLine();
                if ("exit".equalsIgnoreCase(userInput)) {
                    System.exit(0);
                }
                ServiceType serviceType = ServiceType.getByServiceNumber(userInput);
                if (serviceType != null) {
                    ServiceFactory serviceFactory = serviceType.getServiceFactory();
                    PersonService personService = serviceFactory.createPeronService();
                    PersonOperator operator = new PersonOperator(personService);
                    operator.imitateOperations();
                    operator.imitateIqOperations();
                } else {
                    System.out.println("Incorrect parameter");
                }
            }
        } catch (IOException e) {
            System.err.println(e);
        }

        // Composite

        File file1 = new File();
        file1.setName("File1");
        file1.setSize(1);
        File file2 = new File();
        file2.setName("File2");
        file2.setSize(2);
        File file3 = new File();
        file3.setName("File3");
        file3.setSize(3);

        Directory directory1 = new Directory();
        directory1.setName("My Folder");
        directory1.add(file1);
        directory1.add(file2);
        directory1.add(file3);
        System.out.println(directory1.getSize());

        directory1.remove(file2);
        System.out.println(directory1.getSize());

        Directory directory2 = new Directory();
        directory2.add(directory1);
        directory2.add(file2);

        System.out.println(directory2.getSize());
        
        // Decorator
        
        FilePersonInputStream inputStreamDecorator = new CapitalLetterPersonInputStreamDecorator(new BasePersonInputStream());
        List<Person> persons = inputStreamDecorator.readPersons();
        persons.forEach(p -> System.out.println(p.getFirstName()));

        FilePersonOutputStream outputStreamDecorator = new LowLetterPersonOutputStreamDecorator(new BasePersonOutputStream());
        Person person = new Person("Kate", "Kim", 23, 23);
        outputStreamDecorator.writePerson(person);
       
        // Proxy
        
        PersonService personService = new PersonServiceProxy();
        System.out.println(personService.read("Ann").get(0).getSecondName());

        // Bridge

        PersistentAccount persistentAccount = new PersistentAccountImpl(new AccountDaoImpl(), new BillDaoImpl());
        persistentAccount.open("ANNA ACCOUNT");
    }
}
