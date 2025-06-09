package src.service;

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
        repo.getAll().forEach(data -> System.out.printf("%d | %s | %d | %s |\n",
                data.getId(), data.getName(), data.getAge(), data.getGender()));
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
    public void findData() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findData'");
    }

    @Override
    public void findAllData() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAllData'");
    }

    @Override
    public void editData() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'editData'");
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
        return gender.equalsIgnoreCase("perempuan") || gender.equalsIgnoreCase("Laki-laki");
    }

}
