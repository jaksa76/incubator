package me.jaksa.objectmapper;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;

class ObjectMapperTest {
    static Connection conn;

    @BeforeAll static void connectToDb() throws Exception {
        Class.forName("org.h2.Driver");
        conn = DriverManager.getConnection("jdbc:h2:mem:test");

        try {
            conn.createStatement().execute("DROP TABLE EMPLOYEE");
        } catch (Exception e) {
            // could not be created yet
        }
        conn.createStatement().execute("CREATE TABLE EMPLOYEE (" +
                "Id INTEGER not NULL, " +
                "Name VARCHAR(255), " +
                "Surname VARCHAR(255), " +
                "PRIMARY KEY (id))");
    }

    @AfterAll static void closeConnection() throws Exception {
        conn.close();
    }

    @Test void gettingOneEmployee() throws Exception {
        conn.createStatement().execute("DELETE FROM EMPLOYEE");
        conn.createStatement().execute("INSERT INTO EMPLOYEE VALUES(1, 'John', 'Doe')");
        ResultSet rs = conn.createStatement().executeQuery("SELECT Id, Name, Surname FROM EMPLOYEE");

        ObjectMapper om = new ObjectMapper(conn);
        Employee employee = om.readOne(rs, Employee.class);

        assertEquals("John", employee.getName());
        assertEquals("Doe", employee.getSurname());
        assertEquals(1, employee.getId());
    }
}