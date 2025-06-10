package src.service;

import src.entity.Person;

public interface PersonService {
    void printAllData();

    void deleteData(String dataParams);

    void addData(String name, int age, String gender);

    Person findData(String data);

    void findAllData(String query);

    void editData(Person oldPerson, String newName, int age, String gender);
}
