package student;

import java.math.BigDecimal;
import java.math.RoundingMode;

public abstract class Employee extends CSVStringFormatter implements IEmployee {

    protected EmployeeType employeeType;
    protected String name;
    protected String ID;
    protected double payRate;
    protected double pretaxDeductions;
    protected double YTDEarnings;
    protected double YTDTaxesPaid;
    protected static double TAX_RATE = 0.2265;

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

        double grossPay = calculateGrossPay(hoursWorked);
        double netPay = grossPay - this.getPretaxDeductions();
        double taxes = TAX_RATE * netPay;

        BigDecimal finalNetPay = new BigDecimal(netPay - taxes).setScale(2, RoundingMode.HALF_UP);
        BigDecimal roundedTaxes = new BigDecimal(taxes).setScale(2, RoundingMode.HALF_UP);

        System.out.println("Net Pay: " + finalNetPay + ", Taxes: " + roundedTaxes);

        this.addYTDEarnings(finalNetPay.doubleValue());
        this.addYTDTaxesPaid(roundedTaxes.doubleValue());

        return new PayStub(this.getName(), finalNetPay.doubleValue(), roundedTaxes.doubleValue(),
                this.getYTDEarnings(), this.getYTDTaxesPaid());
    }

    protected abstract double calculateGrossPay(double hoursWorked);

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
