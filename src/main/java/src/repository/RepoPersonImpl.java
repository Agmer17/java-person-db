package src.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import com.zaxxer.hikari.HikariDataSource;

import src.entity.Person;

public class RepoPersonImpl implements RepoInterface {
    private HikariDataSource dataSource;

    public RepoPersonImpl(HikariDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public int add(Person newPerson) {

        String sql = """
                INSERT INTO PERSON(nama, umur, gender) VALUES (?,?,?)
                """;

        try (
                Connection connection = this.dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);) {

            statement.setString(1, newPerson.getName());
            statement.setString(2, String.valueOf(newPerson.getAge()));
            statement.setString(3, newPerson.getGender());

            return statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int remove(String personName) {
        return 0;
    }

    @Override
    public int remove(int id) {
        return 0;
    }

    @Override
    public ArrayList<Person> getAll() {
        return null;
    }

}
