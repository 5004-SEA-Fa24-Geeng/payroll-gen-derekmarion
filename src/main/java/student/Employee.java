package student;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Abstract class representing an employee.
 */
public abstract class Employee extends CSVStringFormatter implements IEmployee {

    /**
     * The name of the employee.
     */
    private String name;

    /**
     * The ID of the employee.
     */
    private String id;

    /**
     * The pay rate of the employee.
     */
    private double payRate;

    /**
     * The year-to-date earnings of the employee.
     */
    private double ytdEarnings;

    /**
     * The year-to-date taxes paid by the employee.
     */
    private double ytdTaxesPaid;

    /**
     * The pretax deductions of the employee.
     */
    private double pretaxDeductions;

    /**
     * The type of the employee.
     */
    private EmployeeType employeeType;

    /**
     * The tax rate applied to the employee's earnings.
     */
    private static double taxRate = 0.2265;

    /**
     * Constructs an Employee object.
     * 
     * @param name
     * @param id
     * @param payRate
     * @param ytdEarnings
     * @param ytdTaxesPaid
     * @param pretaxDeductions
     * @param employeeType
     */
    public Employee(String name, String id, double payRate, double ytdEarnings, double ytdTaxesPaid,
            double pretaxDeductions, EmployeeType employeeType) {
        this.name = name;
        this.id = id;
        this.payRate = payRate;
        this.pretaxDeductions = pretaxDeductions;
        this.ytdEarnings = ytdEarnings;
        this.ytdTaxesPaid = ytdTaxesPaid;
        this.employeeType = employeeType;
    }

    /**
     * Gets the type of the employee.
     *
     * @return the type of the employee
     */
    @Override
    public EmployeeType getEmployeeType() {
        return this.employeeType;
    }

    /**
     * Gets the name of the employee.
     *
     * @return the name of the employee
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Gets the ID of the employee.
     *
     * @return the ID of the employee
     */
    @Override
    public String getID() {
        return this.id;
    }

    /**
     * Gets the pay rate of the employee.
     *
     * @return the pay rate of the employee
     */
    @Override
    public double getPayRate() {
        return this.payRate;
    }

    /**
     * Gets the year-to-date earnings of the employee.
     *
     * @return the year-to-date earnings of the employee
     */
    @Override
    public double getYTDEarnings() {
        return this.ytdEarnings;
    }

    /**
     * Gets the year-to-date taxes paid by the employee.
     *
     * @return the year-to-date taxes paid by the employee
     */
    @Override
    public double getYTDTaxesPaid() {
        return this.ytdTaxesPaid;
    }

    /**
     * Gets the pretax deductions of the employee.
     *
     * @return the pretax deductions of the employee
     */
    @Override
    public double getPretaxDeductions() {
        return this.pretaxDeductions;
    }

    /**
     * Runs the payroll for the employee.
     *
     * @param hoursWorked the hours worked by the employee
     * @return the pay stub for the current pay period
     */
    @Override
    public PayStub runPayroll(double hoursWorked) {
        if (hoursWorked < 0) {
            return null;
        }

        // Calculate the gross pay, net pay, and taxes
        double grossPay = calculateGrossPay(hoursWorked);
        double netPay = grossPay - this.getPretaxDeductions();
        double taxes = taxRate * netPay;

        // Round the final net pay and taxes to two decimal places
        BigDecimal finalNetPay = new BigDecimal(netPay - taxes).setScale(2, RoundingMode.HALF_UP);
        BigDecimal roundedTaxes = new BigDecimal(taxes).setScale(2, RoundingMode.HALF_UP);

        // Add the net pay and taxes to the year-to-date earnings and taxes paid for the
        // employee
        this.addYTDEarnings(finalNetPay.doubleValue());
        this.addYTDTaxesPaid(roundedTaxes.doubleValue());

        return new PayStub(this.getName(), finalNetPay.doubleValue(), roundedTaxes.doubleValue(),
                this.getYTDEarnings(), this.getYTDTaxesPaid());
    }

    /**
     * Calculates the gross pay for the employee.
     *
     * @param hoursWorked the hours worked by the employee
     * @return the gross pay for the employee
     */
    protected abstract double calculateGrossPay(double hoursWorked);

    /**
     * Adds to the year-to-date earnings of the employee.
     *
     * @param finalNetPay the net pay to add to the year-to-date earnings
     */
    public void addYTDEarnings(double finalNetPay) {
        this.ytdEarnings += finalNetPay;
    }

    /**
     * Adds to the year-to-date taxes paid by the employee.
     *
     * @param taxes the taxes to add to the year-to-date taxes paid
     */
    public void addYTDTaxesPaid(double taxes) {
        this.ytdTaxesPaid += taxes;
    }

    /**
     * Converts the employee object to a CSV string.
     *
     * @return the CSV string representation of the employee
     */
    @Override
    public String toCSV() {
        StringBuilder employeeString = new StringBuilder();
        employeeString.append(this.getEmployeeType().name()).append(",");
        employeeString.append(this.getName()).append(",");
        employeeString.append(this.getID()).append(",");
        employeeString.append(formatDouble(this.getPayRate())).append(",");
        employeeString.append(formatDouble(this.getPretaxDeductions())).append(",");
        employeeString.append(formatDouble(this.getYTDEarnings())).append(",");
        employeeString.append(formatDouble(this.getYTDTaxesPaid()));
        return employeeString.toString();
    }
}
