package student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BuilderTest {

    private String hourlyEmployeeCSV;
    private String salaryEmployeeCSV;
    private String invalidEmployeeCSV;
    private String negativeValuesEmployeeCSV;
    private String negativeHoursTimeCardCSV;

    @BeforeEach
    public void setUp() {
        hourlyEmployeeCSV = "HOURLY,John Doe,12345,25.00,2000.00,50000.00,10000.00";
        salaryEmployeeCSV = "SALARY,Jane Smith,67890,50000.00,3000.00,100000.00,20000.00";
        invalidEmployeeCSV = "INVALID,John Doe,12345,25.00,2000.00,50000.00,10000.00";
        negativeValuesEmployeeCSV = "HOURLY,John Doe,12345,-25.00,-2000.00,-50000.00,-10000.00";
        negativeHoursTimeCardCSV = "E123,-40.0";
    }

    @Test
    public void testBuildHourlyEmployeeFromCSV() {
        Employee employee = Builder.buildEmployeeFromCSV(hourlyEmployeeCSV);
        assertEquals("John Doe", employee.getName());
        assertEquals("12345", employee.getID());
        assertEquals(25.00, employee.getPayRate());
        assertEquals(2000.00, employee.getPretaxDeductions());
        assertEquals(50000.00, employee.getYTDEarnings());
        assertEquals(10000.00, employee.getYTDTaxesPaid());
        assertEquals(HourlyEmployee.class, employee.getClass());
    }

    @Test
    public void testBuildSalaryEmployeeFromCSV() {
        Employee employee = Builder.buildEmployeeFromCSV(salaryEmployeeCSV);
        assertEquals("Jane Smith", employee.getName());
        assertEquals("67890", employee.getID());
        assertEquals(50000.00, employee.getPayRate());
        assertEquals(3000.00, employee.getPretaxDeductions());
        assertEquals(100000.00, employee.getYTDEarnings());
        assertEquals(20000.00, employee.getYTDTaxesPaid());
        assertEquals(SalaryEmployee.class, employee.getClass());
    }

    @Test
    public void testBuildEmployeeFromCSVWithInvalidType() {
        assertThrows(IllegalArgumentException.class, () -> {
            Builder.buildEmployeeFromCSV(invalidEmployeeCSV);
        });
    }

    @Test
    public void testBuildEmployeeFromCSVWithNegativeValues() {
        assertThrows(IllegalArgumentException.class, () -> {
            Builder.buildEmployeeFromCSV(negativeValuesEmployeeCSV);
        });
    }

    @Test
    public void testBuildTimeCardFromCSVWithNegativeHours() {
        assertThrows(IllegalArgumentException.class, () -> {
            Builder.buildTimeCardFromCSV(negativeHoursTimeCardCSV);
        });
    }
}
