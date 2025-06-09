package src;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.zaxxer.hikari.HikariDataSource;

import src.repository.RepoPersonImpl;
import src.service.PersonServiceImpl;
import src.util.DatabaseUtil;

public class ServiceTest {

    private HikariDataSource dataSource;
    private RepoPersonImpl personRepo;
    private PersonServiceImpl service;

    @BeforeEach
    void setUp() {
        dataSource = DatabaseUtil.getDataSource();
        personRepo = new RepoPersonImpl(dataSource);
        service = new PersonServiceImpl(personRepo);
    }

    @Test
    void printAllData() {
        service.printAllData();
    }

    @Test
    void deleteDataNumber() {
        service.deleteData("57");
        service.printAllData();
    }

    @Test
    void deleteWithName() {
        service.deleteData("layla");
        service.printAllData();
    }

    @Test
    void testVerifiedString() {
        service.addData("Alice", 17, "Perempuan");
        service.printAllData();
    }

}
