package com.epam.jamp.patterns.runner;

import com.epam.jamp.patterns.adapter.ArrayListAdapter;
import com.epam.jamp.patterns.adapter.CollectionAdapter;
import com.epam.jamp.patterns.factory.AbstractServiceFactory;
import com.epam.jamp.patterns.factory.ServiceType;
import com.epam.jamp.patterns.factory.person.PersonService;
import com.epam.jamp.patterns.observer.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] arg) throws SQLException {

        // Adapter

        CollectionAdapter<Long> arrayListAdapter = new ArrayListAdapter<>(new ArrayList<>());
        arrayListAdapter.push(new Long(1534));
        arrayListAdapter.push(new Long(644));
        arrayListAdapter.push(new Long(555));

        System.out.println(arrayListAdapter.pop());

        // Observer

        String fileName = "observer.txt";

        TextData textData = new TextData();
        WordCounter wordCounter = new WordCounter(textData);
        NumberCounter numberCounter = new NumberCounter(textData);
        LongestWordKeeper longestWordKeeper = new LongestWordKeeper(textData);
        WordReverser wordReverser = new WordReverser(textData);

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
                    AbstractServiceFactory abstractServiceFactory = serviceType.getServiceFactory();
                    PersonService personService = abstractServiceFactory.createPeronService();
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
    }
}
