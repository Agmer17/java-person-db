package src.repository;

import java.util.ArrayList;

import src.entity.Person;

public interface RepoInterface {
    int add(Person newPerson);

    int remove(String personName);

    int remove(int id);

    ArrayList<Person> getAll();

    Person find(String name);

    Person find(int id);

    Person edit(Person newObject);

    ArrayList<Person> findAllByName(String query);

}
