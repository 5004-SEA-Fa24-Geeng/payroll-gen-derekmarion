package student;

/**
 * Class representing an hourly employee.
 */
public class HourlyEmployee extends Employee {

    private static double overtimeRate = 1.5;

    /**
     * Constructs an HourlyEmployee object.
     *
     * @param name             the name of the employee
     * @param id               the ID of the employee
     * @param payRate          the pay rate of the employee
     * @param ytdEarnings      the year-to-date earnings of the employee
     * @param ytdTaxesPaid     the year-to-date taxes paid by the employee
     * @param pretaxDeductions the pretax deductions of the employee
     */
    public HourlyEmployee(String name, String id, double payRate, double ytdEarnings, double ytdTaxesPaid,
            double pretaxDeductions) {
        super(name, id, payRate, ytdEarnings, ytdTaxesPaid, pretaxDeductions, EmployeeType.HOURLY);
    }

    /**
     * Calculates the gross pay for the hourly employee.
     *
     * @param hoursWorked the hours worked by the employee
     * @return the gross pay for the employee
     */
    @Override
    protected double calculateGrossPay(double hoursWorked) {
        double regularPay = Math.min(hoursWorked, 40) * this.getPayRate();
        double overtimePay = Math.max(0, hoursWorked - 40) * this.getPayRate() * overtimeRate;
        return regularPay + overtimePay;
    }
}
