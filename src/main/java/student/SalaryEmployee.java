package student;

/**
 * Class representing a salaried employee.
 */
public class SalaryEmployee extends Employee {

    private static double SALARIED_MONTHLY_PAYMENTS = 24.0;

    /**
     * Constructs a SalaryEmployee object.
     *
     * @param name             the name of the employee
     * @param id               the ID of the employee
     * @param payRate          the pay rate of the employee
     * @param ytdEarnings      the year-to-date earnings of the employee
     * @param ytdTaxesPaid     the year-to-date taxes paid by the employee
     * @param pretaxDeductions the pretax deductions of the employee
     * @param employeeType     the type of employee
     */
    public SalaryEmployee(String name, String id, double payRate, double ytdEarnings, double ytdTaxesPaid,
            double pretaxDeductions) {
        super(name, id, payRate, ytdEarnings, ytdTaxesPaid, pretaxDeductions, EmployeeType.SALARY);
    }

    @Override
    protected double calculateGrossPay(double hoursWorked) {
        return this.getPayRate() / SALARIED_MONTHLY_PAYMENTS;
    }
}
