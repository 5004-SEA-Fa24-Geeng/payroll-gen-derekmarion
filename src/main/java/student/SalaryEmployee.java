package student;

/**
 * Class representing a salaried employee.
 */
public class SalaryEmployee extends Employee {

    private static double SALARIED_MONTHLY_PAYMENTS = 24.0;

    /**
     * Constructs a SalaryEmployee object.
     *
     * @param name the name of the employee
     * @param ID the ID of the employee
     * @param payRate the pay rate of the employee
     * @param pretaxDeductions the pretax deductions of the employee
     * @param YTDEarnings the year-to-date earnings of the employee
     * @param YTDTaxesPaid the year-to-date taxes paid by the employee
     */
    public SalaryEmployee(String name, String ID, double payRate, double pretaxDeductions,
            double YTDEarnings, double YTDTaxesPaid) {
        super(EmployeeType.SALARY, name, ID, payRate, pretaxDeductions, YTDEarnings, YTDTaxesPaid);
    }

    @Override
    protected double calculateGrossPay(double hoursWorked) {
        return this.getPayRate() / SALARIED_MONTHLY_PAYMENTS;
    }
}
