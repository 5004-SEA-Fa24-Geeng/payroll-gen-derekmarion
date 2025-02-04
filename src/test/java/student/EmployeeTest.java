package test.java.student;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.beans.Transient;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.java.student.Employee;
import main.java.student.TimeCard;

public class EmployeeTest {

    private Employee employee;
    private TimeCard timeCard;

    @BeforeEach
    public void setUp() {
        timeCard = new TimeCard("1234", 25);
        employee = new Employee("John Doe", "12345", 25.0, "FullTime", 50000.0, 10000.0, 2000.0, 40.0, timeCard);
    }

    @Test
    public void testGetName() {
        assertEquals("John Doe", employee.getName());
    }
}
