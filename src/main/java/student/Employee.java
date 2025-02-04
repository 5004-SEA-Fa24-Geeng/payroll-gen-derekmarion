package main.java.student;

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

    @Override
    public PayStub runPayroll(double hoursWorked) {
        // Temporary stub implementation
        return null;
    }

    @Override
    public String toCSV() {
        // Temporary stub implementation
        return "";
    }
}
