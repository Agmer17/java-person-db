package src.service;

public interface PersonService {
    void printAllData();

    void deleteData(String dataParams);

    void addData(String name, int age, String gender);

    void findData();

    void findAllData();

    void editData();
}
