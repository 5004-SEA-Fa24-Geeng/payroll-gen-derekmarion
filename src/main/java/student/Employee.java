package main.java.student;

import student.IEmployee;

public class Employee implements IEmployee {

    private String name;
    private String ID;
    private double payRate;
    private String employeeType; /* TODO: change this to enum */
    private double YTDEarnings;
    private double YTDTaxesPaid;
    private double pretaxDeductions;
    private double hoursWorked;
    private TimeCard timeCard;

    public Employee(String name, String ID, double payRate, String employeeType, double YTDEarnings,
            double YTDTaxesPaid, double pretaxDeductions, double hoursWorked, TimeCard timeCard) {
        this.name = name;
        this.ID = ID; /* TODO: refactor to use timecard's getter */
        this.payRate = payRate;
        this.employeeType = employeeType;
        this.YTDEarnings = YTDEarnings;
        this.YTDTaxesPaid = YTDTaxesPaid;
        this.pretaxDeductions = pretaxDeductions;
        this.hoursWorked = hoursWorked; /* TODO: refactor to use timecard's getter */
        this.timeCard = timeCard;
    }

    public double getHoursWorked() {
        return this.hoursWorked;
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
