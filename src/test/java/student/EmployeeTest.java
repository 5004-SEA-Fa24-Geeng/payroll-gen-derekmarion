package test.java.student;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.beans.Transient;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.java.student.Employee;
import main.java.student.TimeCard;

public class EmployeeTest {

    private Employee employee;

    @BeforeEach
    public void setUp() {
        employee = new Employee("HOURLY", "John Doe", "12345", 25.0, 2000.0, 50000.0, 10000.0);
    }

    @Test
    public void testGetName() {
        assertEquals("John Doe", employee.getName());
    }

    @Test
    public void testGetID() {
        assertEquals("12345", employee.getID());
    }

    @Test
    public void testGetPayRate() {
        assertEquals(25.0, employee.getPayRate());
    }

    @Test
    public void testGetEmployeeType() {
        assertEquals("HOURLY", employee.getEmployeeType());
    }

    @Test
    public void testGetYTDEarnings() {
        assertEquals(50000.0, employee.getYTDEarnings());
    }

    @Test
    public void testGetYTDTaxesPaid() {
        assertEquals(10000.0, employee.getYTDTaxesPaid());
    }

    @Test
    public void testGetPretaxDeductions() {
        assertEquals(2000.0, employee.getPretaxDeductions());
    }
}
