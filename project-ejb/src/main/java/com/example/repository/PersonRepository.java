package com.example.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.sql.DataSource;

import com.example.mapper.RowMapper;

@Named
@ApplicationScoped
public class PersonRepository {

    @Resource(lookup = "java:/PostgresDS")
    private DataSource dataSource;

    private final RowMapper<Person> personRowMapper = (rs) -> new Person.Builder(rs.getString("id"))
            .name(rs.getString("name")).age(rs.getInt("age")).email(rs.getString("email")).build();

    public List<Person> findAllPerson() {
        List<Person> persons = new ArrayList<>();

        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT * FROM example_table";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        persons.add(personRowMapper.mapRow(resultSet));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return persons;
    }

    public void storePerson(Person person) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "INSERT INTO public.example_table (id, name, age, email) VALUES (?, ?, ?, ?)";

            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1, person.getId());
            stmt.setString(2, person.getName());
            stmt.setInt(3, person.getAge());
            stmt.setString(4, person.getEmail());

            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("New person inserted: " + person.toString());
    }

}
