package student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The following expected values for PayStub generation are used
 * to statically assert against output of runPayRoll method, instead of being
 * imported
 * as test fixtures:
 * 
 * employee_name,net_pay,taxes,ytd_earnings,ytd_taxes_paid
 * Luffy,1102.24,322.76,21102.24,4852.76
 * Nami,5672.33,1661.00,22689.33,6644.00
 */
public class EmployeeTest {

    private String hourlyEmployeeString;
    private Employee hourlyEmployee;
    private PayStub hourlyEmployeePayStub;
    private String salaryEmployeeString;
    private Employee salaryEmployee;
    private PayStub salaryEmployeePayStub;

    @BeforeEach
    public void setUp() {
        hourlyEmployeeString = "HOURLY,Luffy,s192,30.00,0,20000,4530";
        hourlyEmployee = Builder.buildEmployeeFromCSV(hourlyEmployeeString);
        salaryEmployeeString = "SALARY,Nami,s193,200000,1000,17017,4983";
        salaryEmployee = Builder.buildEmployeeFromCSV(salaryEmployeeString);

    }

    @Test
    public void testRunPayrollHourlyEmployee() {
        hourlyEmployeePayStub = hourlyEmployee.runPayroll(45);
        assertEquals(1102.24, hourlyEmployeePayStub.getPay(), 0.01);
        assertEquals(322.76, hourlyEmployeePayStub.getTaxesPaid(), 0.01);
        assertEquals(21102.24, hourlyEmployeePayStub.getYTDEarnings(), 0.01);
        assertEquals(4852.76, hourlyEmployeePayStub.getYTDTaxesPaid(), 0.01);
    }

    @Test
    public void testRunPayrollSalaryEmployee() {
        salaryEmployeePayStub = salaryEmployee.runPayroll(45);
        assertEquals(5672.33, salaryEmployeePayStub.getPay(), 0.01);
        assertEquals(1661.00, salaryEmployeePayStub.getTaxesPaid(), 0.01);
        assertEquals(22689.33, salaryEmployeePayStub.getYTDEarnings(), 0.01);
        assertEquals(6644.00, salaryEmployeePayStub.getYTDTaxesPaid(), 0.01);
    }

    @Test
    public void testRunPayrollNegativeHours() {
        PayStub payStub = hourlyEmployee.runPayroll(-5);
        assertNull(payStub);
    }

    @Test
    public void testGetName() {
        assertEquals("Luffy", hourlyEmployee.getName());
        assertEquals("Nami", salaryEmployee.getName());
    }

    @Test
    public void testGetID() {
        assertEquals("s192", hourlyEmployee.getID());
        assertEquals("s193", salaryEmployee.getID());
    }

    @Test
    public void testGetPayRate() {
        assertEquals(30.00, hourlyEmployee.getPayRate(), 0.01);
        assertEquals(200000.00, salaryEmployee.getPayRate(), 0.01);
    }

    @Test
    public void testGetEmployeeType() {
        assertEquals(EmployeeType.HOURLY, hourlyEmployee.getEmployeeType());
        assertEquals(EmployeeType.SALARY, salaryEmployee.getEmployeeType());
    }

    @Test
    public void testGetYTDEarnings() {
        assertEquals(20000.00, hourlyEmployee.getYTDEarnings(), 0.01);
        assertEquals(17017.00, salaryEmployee.getYTDEarnings(), 0.01);
    }

    @Test
    public void testGetYTDTaxesPaid() {
        assertEquals(4530.00, hourlyEmployee.getYTDTaxesPaid(), 0.01);
        assertEquals(4983.00, salaryEmployee.getYTDTaxesPaid(), 0.01);
    }

    @Test
    public void testGetPretaxDeductions() {
        assertEquals(0.00, hourlyEmployee.getPretaxDeductions(), 0.01);
        assertEquals(1000.00, salaryEmployee.getPretaxDeductions(), 0.01);
    }

    @Test
    public void testAddYTDEarnings() {
        hourlyEmployee.addYTDEarnings(1000.00);
        assertEquals(21000.00, hourlyEmployee.getYTDEarnings(), 0.01);
        salaryEmployee.addYTDEarnings(2000.00);
        assertEquals(19017.00, salaryEmployee.getYTDEarnings(), 0.01);
    }

    @Test
    public void testAddYTDTaxesPaid() {
        hourlyEmployee.addYTDTaxesPaid(500.00);
        assertEquals(5030.00, hourlyEmployee.getYTDTaxesPaid(), 0.01);
        salaryEmployee.addYTDTaxesPaid(1000.00);
        assertEquals(5983.00, salaryEmployee.getYTDTaxesPaid(), 0.01);
    }

    @Test
    public void testToCSV() {
        String expectedHourlyCSV = "HOURLY,Luffy,s192,30.0,0.0,20000.0,4530.0";
        String expectedSalaryCSV = "SALARY,Nami,s193,200000.0,1000.0,17017.0,4983.0";
        assertEquals(expectedHourlyCSV, hourlyEmployee.toCSV());
        assertEquals(expectedSalaryCSV, salaryEmployee.toCSV());
    }
}
