package main.java.student;

import java.math.BigDecimal;
import java.math.RoundingMode;

import student.IEmployee;

public class Employee implements IEmployee {

    private String employeeType; /* TODO: change this to enum */
    private String name;
    private String ID;
    private double payRate;
    private double pretaxDeductions;
    private double YTDEarnings;
    private double YTDTaxesPaid;

    public Employee(String employeeType, String name, String ID, double payRate, double pretaxDeductions,
            double YTDEarnings, double YTDTaxesPaid) {
        this.employeeType = employeeType;
        this.name = name;
        this.ID = ID;
        this.payRate = payRate;
        this.pretaxDeductions = pretaxDeductions;
        this.YTDEarnings = YTDEarnings;
        this.YTDTaxesPaid = YTDTaxesPaid;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getID() {
        return this.ID;
    }

    @Override
    public double getPayRate() {
        return this.payRate;
    }

    @Override
    public String getEmployeeType() {
        return this.employeeType;
    }

    @Override
    public double getYTDEarnings() {
        return this.YTDEarnings;
    }

    @Override
    public double getYTDTaxesPaid() {
        return this.YTDTaxesPaid;
    }

    @Override
    public double getPretaxDeductions() {
        return this.pretaxDeductions;
    }

    /**
     * Runs the employee's payroll.
     * 
     * This will calculate the pay for the current pay, update the YTD earnings, and
     * update the
     * taxes paid YTD.
     * 
     * taxes are calculated as 1.45% for medicare, 6.2% for social security, and 15%
     * for
     * withholding. or 22.65% total. They are calculated on the net pay (after
     * pretax deductions).
     * 
     * For hourly employees, the pay is calculated as payRate * hoursWorked for the
     * first 40 hours,
     * then payRate * 1.5 * (hoursWorked - 40) for overtime.
     * 
     * For salary employees, it is pay rate divided by 24 for two payments every
     * month.
     * 
     * If either type of employee has < 0 hours, they are skipped this payroll
     * period.
     * (suggestion return null, and skip adding nulls to your paystub list)
     * 
     * Final net pay is calculated as pay - pretaxDeductions - taxes.
     * 
     * All numbers (across all methods) are rounded to the nearest cent. (2 decimal
     * places)
     * 
     * SUGGESTION: You may want to use BigDecimal for the calculations to avoid
     * floating point errors.
     * SUGGESTION: You may want to create an protected abstract
     * calculateGrossPay(double hoursWorked)
     * method to calculate the gross pay for the pay period, as runPayroll is
     * exactly
     * the same for both SalaryEmployee and HourlyEmployee, but calculateGrossPay is
     * different.
     * 
     * @param hoursWorked the hours worked for the pay period
     * 
     * @return the pay stub for the current pay period
     * 
     */
    @Override
    public PayStub runPayroll(double hoursWorked) {
        if (hoursWorked < 0) {
            System.out.println("Invalid hours worked: " + hoursWorked);
            return null;
        }
        // Calculate pay and net pay based on employee type
        double pay;
        if (this.getEmployeeType().equals("HOURLY")) {
            double regularPay = Math.min(hoursWorked, 40) * this.getPayRate();
            double overtime_pay = Math.max(0, hoursWorked - 40) * this.getPayRate() * 1.5; // TODO: remove magic number
            pay = regularPay + overtime_pay;
            System.out.println("Hourly Employee - Regular Pay: " + regularPay
                    + ", Total Pay: " + pay);
        } else if (this.getEmployeeType().equals("SALARY")) {
            pay = this.getPayRate() / 24.0; // TODO: get rid of magic number
            System.out.println("Salary Employee - Pay: " + pay);
        } else {
            System.out.println("Unknown employee type: " + this.getEmployeeType());
            return null; // Unknown employee type
        }

        // Calculate net pay
        double netPay = pay - this.getPretaxDeductions();

        // Calculate taxes
        double taxes = 0.2265 * netPay; // TODO: get rid of magic number

        // Round to the nearest cent
        BigDecimal finalNetPay = new BigDecimal(netPay - taxes).setScale(2, RoundingMode.HALF_UP);
        BigDecimal roundedTaxes = new BigDecimal(taxes).setScale(2, RoundingMode.HALF_UP);

        System.out.println("Net Pay: " + finalNetPay + ", Taxes: " + roundedTaxes);

        // Update YTD earnings and taxes paid YTD for the employee
        this.addYTDEarnings(finalNetPay.doubleValue());
        this.addYTDTaxesPaid(roundedTaxes.doubleValue());

        return new PayStub(this.getName(), finalNetPay.doubleValue(), roundedTaxes.doubleValue(),
                this.getYTDEarnings(), this.getYTDTaxesPaid());

    }

    public void addYTDEarnings(double finalNetPay) {
        this.YTDEarnings += finalNetPay;
    }

    public void addYTDTaxesPaid(double taxes) {
        this.YTDTaxesPaid += taxes;
    }

    @Override
    public String toCSV() {
        StringBuilder employeeString = new StringBuilder();
        employeeString.append(this.getEmployeeType()).append(",");
        employeeString.append(this.getName()).append(",");
        employeeString.append(this.getID()).append(",");
        employeeString.append(String.format("%.2f", this.getPayRate())).append(",");
        employeeString.append(String.format("%.2f", this.getPretaxDeductions())).append(",");
        employeeString.append(String.format("%.2f", this.getYTDEarnings())).append(",");
        employeeString.append(String.format("%.2f", this.getYTDTaxesPaid())).append(",");
        return employeeString.toString();
    }
}
