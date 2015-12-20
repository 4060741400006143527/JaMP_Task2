package com.epam.jamp.patterns.factory.person;

import com.epam.jamp.patterns.model.Person;
import com.epam.jamp.patterns.file.FileItemReader;
import com.epam.jamp.patterns.file.PersonParser;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FilePersonService implements PersonService {

    private File file;

    public FilePersonService() {
        initFileStorage();
    }

    public FilePersonService(String fileName) {
        initFileStorageByName(fileName);
    }

    @Override
    public void write(Person person) {
        Validator.validatePerson(person);
        try {
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file, true)));
            out.println(person.toString());
            out.close();
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    @Override
    public void rewrite(Person person) {
        Validator.validatePerson(person);
        try {
            List<Person> persons = readAll();
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file, false)));
            for (Person currentPerson : persons) {
                if (person.equals(currentPerson)) {
                    currentPerson = person;
                    out.println(currentPerson.toString());
                } else {
                    out.println(currentPerson.toString());
                }
            }
            out.close();
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    @Override
    public List<Person> readAll() {
        List<Person> result = new ArrayList<Person>();
        List<Person> items;
        FileItemReader<Person> reader = null;
        try {
            reader = new FileItemReader<Person>(file, new PersonParser());
            while ((items = reader.readLines(5)).size() != 0) {
                result.addAll(items);
            }
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        return result;
    }

    @Override
    public List<Person> read(String name) {
        Validator.validateName(name);
        List<Person> result = new ArrayList<>();
        FileItemReader<Person> reader = null;
        try {
            reader = new FileItemReader<>(file, new PersonParser());
            List<Person> items;
            while ((items = reader.readLines(1)).size() != 0) {
                result.addAll(items.stream().filter(p -> name.equalsIgnoreCase(p.getFirstName())).collect(Collectors.toList()));
            }
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        return result;
    }

    private void initFileStorage() {
        try {
            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
                System.out.println("Input file name:");
                String fileName = bufferedReader.readLine();
                file = createFileIfNotExists(fileName);
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    private void initFileStorageByName(String fileName) {
        try {
            file = createFileIfNotExists(fileName);
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    private File createFileIfNotExists(String fileName) throws IOException {
        File file = new File(fileName);
        if (!file.exists()) {
            file.createNewFile();
        }
        return file;
    }
}
