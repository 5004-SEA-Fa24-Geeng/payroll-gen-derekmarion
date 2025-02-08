package student;

/**
 * Class representing an hourly employee.
 */
public class HourlyEmployee extends Employee {

    private static double OVERTIME_RATE = 1.5;

    /**
     * Constructs an HourlyEmployee object.
     *
     * @param name the name of the employee
     * @param ID the ID of the employee
     * @param payRate the pay rate of the employee
     * @param pretaxDeductions the pretax deductions of the employee
     * @param YTDEarnings the year-to-date earnings of the employee
     * @param YTDTaxesPaid the year-to-date taxes paid by the employee
     */
    public HourlyEmployee(String name, String ID, double payRate, double pretaxDeductions,
            double YTDEarnings, double YTDTaxesPaid) {
        super(EmployeeType.HOURLY, name, ID, payRate, pretaxDeductions, YTDEarnings, YTDTaxesPaid);
    }

    @Override
    protected double calculateGrossPay(double hoursWorked) {
        double regularPay = Math.min(hoursWorked, 40) * this.getPayRate();
        double overtimePay = Math.max(0, hoursWorked - 40) * this.getPayRate() * OVERTIME_RATE;
        return regularPay + overtimePay;
    }
}
