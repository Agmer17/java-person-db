package src;

import static org.junit.jupiter.api.Assertions.assertTrue;

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
        Person newPerson = new Person("agmer", 17, Gender.genderFromLabel("Laki-laki"));
        personRepo.add(newPerson);

    }

    @AfterEach
    void Teardown() {
        dataSource.close();
    }

}
