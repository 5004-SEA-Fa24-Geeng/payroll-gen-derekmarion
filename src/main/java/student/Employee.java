package student;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Employee extends CSVStringFormatter implements IEmployee {

    private EmployeeType employeeType;
    private String name;
    private String ID;
    private double payRate;
    private double pretaxDeductions;
    private double YTDEarnings;
    private double YTDTaxesPaid;
    private static double OVERTIME_RATE = 1.5;
    private static double TAX_RATE = 0.2265;
    private static double SALARIED_MONTHLY_PAYMENTS = 24.0;

    public Employee(EmployeeType employeeType, String name, String ID, double payRate, double pretaxDeductions,
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
    public EmployeeType getEmployeeType() {
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

    @Override
    public PayStub runPayroll(double hoursWorked) {
        if (hoursWorked < 0) {
            System.out.println("Invalid hours worked: " + hoursWorked);
            return null;
        }
        // Calculate pay and net pay based on employee type
        double pay;
        if (this.employeeType == EmployeeType.HOURLY) {
            double regularPay = Math.min(hoursWorked, 40) * this.getPayRate();
            double overtime_pay = Math.max(0, hoursWorked - 40) * this.getPayRate() * OVERTIME_RATE;
            pay = regularPay + overtime_pay;
            System.out.println("Hourly Employee - Regular Pay: " + regularPay
                    + ", Total Pay: " + pay);
        } else if (this.employeeType == EmployeeType.SALARY) {
            pay = this.getPayRate() / SALARIED_MONTHLY_PAYMENTS;
            System.out.println("Salary Employee - Pay: " + pay);
        } else {
            System.out.println("Unknown employee type: " + this.getEmployeeType());
            return null; // Unknown employee type
        }

        // Calculate net pay
        double netPay = pay - this.getPretaxDeductions();

        // Calculate taxes
        double taxes = TAX_RATE * netPay;

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
        employeeString.append(this.getEmployeeType().name()).append(",");
        employeeString.append(this.getName()).append(",");
        employeeString.append(this.getID()).append(",");
        employeeString.append(formatDouble(this.getPayRate())).append(",");
        employeeString.append(formatDouble(this.getPretaxDeductions())).append(",");
        employeeString.append(formatDouble(this.getYTDEarnings())).append(",");
        employeeString.append(formatDouble(this.getYTDTaxesPaid()));
        System.out.println("Employee CSV: " + employeeString.toString());
        return employeeString.toString();
    }
}
