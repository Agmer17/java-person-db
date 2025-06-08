package src;

import java.util.ArrayList;

// import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.zaxxer.hikari.HikariDataSource;

import src.entity.Gender;
import src.entity.Person;
import src.repository.RepoPersonImpl;
import src.util.DatabaseUtil;

public class RepoTest {

    private HikariDataSource dataSource;
    private RepoPersonImpl personRepo;

    @BeforeEach
    void setUp() {
        dataSource = DatabaseUtil.getDataSource();
        personRepo = new RepoPersonImpl(dataSource);
    }

    @Test
    void addPersonTest() {
        Person newPerson = new Person("hapus", 19, Gender.genderFromLabel("perempuan"));
        personRepo.add(newPerson);
    }

    @Test
    void printAllData() {
        var personList = personRepo.getAll();
        personList.forEach(System.out::println);
    }

    @Test
    void deleteDataId() {
        int rowAffected = personRepo.remove(58);
        System.out.println(rowAffected);
    }

    @Test
    void deleteData() {
        var numberEffected = personRepo.remove("hapus");
        System.out.println(numberEffected);
    }

    @Test
    void findByName() {
        var person = this.personRepo.find("elaina");
        System.out.println(person);
    }

    @Test
    void findByAllByName() {
        ArrayList<Person> person = this.personRepo.findAllByName("ri");
        person.forEach(System.out::println);
    }

    // @AfterEach
    // void Teardown() {
    // dataSource.close();
    // }

}
