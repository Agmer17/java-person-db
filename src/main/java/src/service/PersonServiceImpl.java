package src.service;

import java.util.ArrayList;

import src.entity.Gender;
import src.entity.Person;
import src.repository.RepoPersonImpl;

public class PersonServiceImpl implements PersonService {
    private RepoPersonImpl repo;

    public PersonServiceImpl(RepoPersonImpl repo) {
        this.repo = repo;
    }

    @Override
    public void printAllData() {
        repo.getAll().forEach(System.out::println);
    }

    @Override
    public void deleteData(String dataParams) {

        if (!this.verifiedStringAndNumber(dataParams)) {
            System.out.println("INPUT TIDAK VALID!");
            return;
        }
        try {
            int personId = Integer.valueOf(dataParams);
            repo.remove(personId);
        } catch (NumberFormatException e) {
            repo.remove(dataParams);
        }
        System.out.println("Data berhasil dihapus!");
    }

    @Override
    public void addData(String name, int age, String gender) {

        if (!this.verifiedStringAndNumber(name, age, gender)) {
            System.out.println("DATA YANG DIMASUKKAN TIDAK VALID!");
            return;
        }

        if (!this.genderVerify(gender)) {
            System.out.println("GENDER GAK VALID!");
            return;
        }
        Person newPerson = new Person(name, age, Gender.genderFromLabel(gender));

        repo.add(newPerson);
        System.out.println("Berhasil menambahkan data");

    }

    @Override
    public Person findData(String data) {
        if (!this.verifiedStringAndNumber(data)) {
            System.out.println("data yang dimasukkan gak valid");
            return null;
        }

        try {
            int personId = Integer.valueOf(data);
            return this.repo.find(personId);
        } catch (NumberFormatException e) {
            return this.repo.find(data);
        }
    }

    @Override
    public void findAllData(String query) {
        ArrayList<Person> resultData = this.repo.findAllByName(query);
        resultData.forEach(System.out::println);
    }

    @Override
    public void editData(Person oldPerson, String newName, int age, String gender) {
        if (!this.verifiedStringAndNumber(newName, age, gender)) {
            System.out.println("Data baru yang dimasukkan gak valid! operasi gagal");
            return;
        }

        if (!this.genderVerify(gender)) {
            System.out.println("Gender hanya ada laki-laki dan perempuan");
            return;
        }
        Person newPersonData = new Person(oldPerson.getId(), newName, age, gender);
        this.repo.edit(newPersonData);
        System.out.println("Data berhasil dirubah!");
    }

    @SafeVarargs
    private <T> boolean verifiedStringAndNumber(T... data) {
        // ini kalo valid dia return true. kalo gak false

        for (T item : data) {
            if (item instanceof String) {
                String str = String.valueOf(item);
                if (str.trim().isEmpty() || str.isBlank()) {
                    return false;
                }
            } else if (item instanceof Integer) {
                if ((Integer) item == 0) {
                    return false;
                }
            } else {
                return false;
            }
        }
        return true;

    }

    private boolean genderVerify(String gender) {
        // klo gendernya bener return true
        return gender.equalsIgnoreCase("perempuan") || gender.equalsIgnoreCase("Laki-laki");
    }

}
