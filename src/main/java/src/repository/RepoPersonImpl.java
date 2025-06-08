package src.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

        String sqlString = """
                DELETE FROM person where nama = ?
                """;
        try (
                Connection connection = this.dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(sqlString);) {

            statement.setString(1, personName);
            return statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public int remove(int id) {
        String sql = "DELETE FROM person where id = ?";
        try (
                Connection connection = this.dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, String.valueOf(id));
            return statement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public ArrayList<Person> getAll() {
        ArrayList<Person> result = null;
        try (
                Connection connection = this.dataSource.getConnection();
                Statement statement = connection.createStatement();) {

            String SQLquery = "SELECT * FROM PERSON";
            ResultSet resultData = statement.executeQuery(SQLquery);

            result = this.resultToArrayList(resultData);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public Person find(String name) {
        String sqlQuery = "SELECT * FROM PERSON WHERE nama = ?";
        try (
                Connection connection = this.dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            statement.setString(1, name);
            ResultSet resultData = statement.executeQuery();
            return this.dataToObject(resultData);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Person find(int id) {
        return null;
    }

    @Override
    public Person edit(Person newPerson) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'edit'");
    }

    @Override
    public ArrayList<Person> findAllByName(String query) {
        ArrayList<Person> result = null;
        String sqlQuery = "SELECT * FROM PERSON WHERE nama LIKE ?";

        try (
                Connection connection = this.dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(sqlQuery);) {
            statement.setString(1, String.format("%%%s%%", query));
            ResultSet queryResult = statement.executeQuery();
            result = this.resultToArrayList(queryResult);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private Person dataToObject(ResultSet result) {
        Person person = null;

        try {
            while (result.next()) {
                int id = result.getInt("id");
                String name = result.getString("nama");
                int umur = result.getInt("umur");
                String gendeString = result.getString("gender");
                person = new Person(id, name, umur, gendeString);
            }
            return person;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    private ArrayList<Person> resultToArrayList(ResultSet resultData) {
        ArrayList<Person> result = new ArrayList<>(50);
        try {
            while (resultData.next()) {
                int id = resultData.getInt("id");
                String name = resultData.getString("nama");
                int umur = resultData.getInt("umur");
                String gendeString = resultData.getString("gender");

                result.add(new Person(id, name, umur, gendeString));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

}
